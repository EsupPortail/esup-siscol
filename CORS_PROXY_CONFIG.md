# Configuration CORS avec HTTP Proxy

## 📋 Vue d'ensemble

Quand l'application est derrière un proxy HTTP (nginx, Apache, HAProxy, etc.), le problème CORS survient car :

1. **Le navigateur envoie l'`Origin` du proxy** (ex: `http://proxy.interne:8080`)
2. **Spring reçoit cette origine**, pas celle du client réel
3. **Avec `allowCredentials=true`**, les headers `*` wildcard sont interdits
4. **L'origine du proxy** ne correspond pas à l'origine attendue

## ✅ Solution implémentée

### 1. Configuration CORS dans WebSecurityConfig

La configuration a été mise à jour pour :
- ✓ Utiliser `allowedOriginPattern(".*")` au lieu de wildcard `"*"`
- ✓ Accepter tous les domaines quand derrière un proxy
- ✓ Supporter les headers proxy `X-Forwarded-*`

```java
config.setAllowCredentials(true);
config.addAllowedOriginPattern(".*");  // Pattern regex pour accepter tous les domaines
config.addAllowedHeader("X-Forwarded-For");
config.addAllowedHeader("X-Forwarded-Proto");
config.addAllowedHeader("X-Forwarded-Host");
```

### 2. ForwardedHeaderFilter

Une nouvelle classe `ForwardedHeaderConfig` a été ajoutée pour :
- Traiter les headers `X-Forwarded-*` du proxy
- Reconstruire l'URL originale du client
- Utiliser l'origine correcte dans les vérifications CORS

## 🔧 Configuration du proxy HTTP

### Pour Nginx

```nginx
server {
    listen 80;
    server_name proxy.interne;

    location / {
        proxy_pass http://localhost:8080;
        
        # Headers proxy pour que Spring connaisse l'URL réelle
        proxy_set_header X-Forwarded-For $remote_addr;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_set_header X-Forwarded-Host $host;
        proxy_set_header X-Forwarded-Port $server_port;
        
        # Headers importants pour CORS
        proxy_set_header Host $host;
        proxy_set_header Origin $http_origin;
        
        # Autorise les credentials
        proxy_pass_request_headers on;
    }
}
```

### Pour Apache

```apache
<VirtualHost *:80>
    ServerName proxy.interne
    
    <Location />
        ProxyPass http://localhost:8080/
        ProxyPassReverse http://localhost:8080/
        
        # Headers proxy
        RequestHeader set X-Forwarded-For "%{REMOTE_ADDR}s"
        RequestHeader set X-Forwarded-Proto "%{REQUEST_SCHEME}s"
        RequestHeader set X-Forwarded-Host "%{HTTP_HOST}s"
        RequestHeader set X-Forwarded-Port "%{SERVER_PORT}s"
    </Location>
</VirtualHost>
```

### Pour HAProxy

```haproxy
frontend http_proxy
    bind 0.0.0.0:80
    default_backend backend_siscol

backend backend_siscol
    balance roundrobin
    option forwardfor
    http-request set-header X-Forwarded-Proto %[req.scheme]
    http-request set-header X-Forwarded-Host %[req.hdr(host)]
    http-request set-header X-Forwarded-Port %[dst_port]
    server srv1 localhost:8080
```

## 📍 Configuration Spring Boot (application.yml)

Vous pouvez optionnellement configurer Spring pour forcer le traitement des headers proxy :

```yaml
server:
  forward-headers-strategy: framework
```

Ou dans les propriétés Java :
```properties
server.forward-headers-strategy=framework
```

## 🧪 Test de la configuration

### Test 1: Vérifier les headers reçus
```bash
curl -v -X OPTIONS http://proxy.interne:8080/ldap/tuteur \
  -H "Origin: http://example.com" \
  -H "Access-Control-Request-Method: POST"
```

Vérifiez que la réponse contient :
```
Access-Control-Allow-Origin: http://example.com
Access-Control-Allow-Credentials: true
```

### Test 2: Vérifier les headers du proxy
```bash
curl -v http://proxy.interne:8080/health \
  -H "X-Forwarded-For: 192.168.1.99" \
  -H "X-Forwarded-Proto: https" \
  -H "X-Forwarded-Host: app.example.com"
```

## ⚠️ Notes de sécurité

### Important pour la production :

1. **Restreindre le pattern d'origine** (au lieu de `".*"`) :
   ```java
   config.addAllowedOriginPattern("https://app\\.example\\.com");
   config.addAllowedOriginPattern("https://secure\\.example\\.com");
   ```

2. **Configurer le proxy pour valider les headers** :
   - N'acceptez que les headers proxy de sources fiables
   - Filtrez les headers `X-Forwarded-*` des clients externes

3. **Utiliser HTTPS en production** :
   ```nginx
   proxy_set_header X-Forwarded-Proto https;
   proxy_set_header X-Forwarded-Host secure.example.com;
   ```

## 🐛 Dépannage

### Erreur : "When allowCredentials is true, allowedOrigins cannot contain..."
- ✓ Fixé : utiliser `allowedOriginPattern()` au lieu de `addAllowedOrigin("*")`

### Les headers CORS ne sont pas transmis
- Vérifiez que le proxy HTTP envoie les headers `X-Forwarded-*`
- Vérifiez que Spring reçoit ces headers (logs)

### L'origine est toujours celle du proxy
- Vérifiez que `ForwardedHeaderFilter` est activé
- Vérifiez la configuration `server.forward-headers-strategy`

## 📚 Références

- [Spring CORS Documentation](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-cors.html)
- [Spring ForwardedHeaderFilter](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/filter/ForwardedHeaderFilter.html)
- [RFC 7239 - Forwarded HTTP Extension](https://tools.ietf.org/html/rfc7239)

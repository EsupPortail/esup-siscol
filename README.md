Referentiel overlay Template
=======================

Generic  Referentiel WAR overlay to expose apogee and LDAP data . This programm  could be freely used.

# Versions

- APOGEE `6.4.x`
- JDK `11`, `8`
- APOGEE WS `62031` ...`62070`

# Installation du JAR client WS-APOGEE
Avant de commencer l'installation il faut bien installer le jar client apo-webservices-client, chaque établissement doit s'assurer que la version installée correspond au web-service qui va être interroger:

```bash
mvn install:install-file -Dfile=apo-webservices-client{mettre la version}.jar -DgroupId=gouv.education.apogee -DartifactId=apo-webservices-client -Dversion={mettre la version} -Dpackaging=jar
```


# Clonage et installation
 
``` 
git clone https://github.com/EsupPortail/esup-siscol.git esup-siscol
cd esup-siscol/src/main/resources/
cp application.yml.sample application.yml

vim application.yml

```
La première partie LDAP : renseigner les paramtères LDAP 

	ldap:
    	urls: 
    		- ldap://ldap-paris.fr:389
       username: uid=xxxx,ou=admins,dc=u-paris10,dc=fr
       password: xxxx
       base: dc=u-paris10,dc=fr


La deuxiéme  partie  concerne les urls APOGEE

	  administratifMetier: http://ws.uni.fr:8080/aws/services/AdministratifMetier
      tudiantMetier: http://ws.uni.fr:8080/aws/services/EtudiantMetier
      pedagogiqueMetier: http://ws.uni.fr:8080/aws/services/PedagogiqueMetier
      geographieMetier: http://ws.uni.fr:8080/aws/services/GeographieMetier
      referentielMetier: http://ws.uni.fr:8080/aws/services/ReferentielMetier
      offreFormationMetier: http://ws.uni.fr:8080/aws/services/OffreFormationMetier

La troisième Partie

	credential:
		userscredential:
    		root:
      			username: root
      			password: {password}



 il faut ajouter  la bonne dépendance ({mettre la version}) dans le pom.xml	 
			
				<dependency>
						<groupId>gouv.education.apogee</groupId>
						<artifactId>apo-webservices-client</artifactId>
						<version>{mettre la version}</version>
					</dependency>
					
Pour tester le bonne compilation.
					
```bash
mvn clean compile
mvn install
```

# Overview

Pour éxecuter le projet en mode développement il suffit de le lancer  avec la commande : 

```bash
cp application.yml.sample application.yml
##configurer la partie LDAP, apogee (urls de services) et userscredential
mvn  spring-boot:run
```


# Configuration

## en mode Tomcat:
```
cp target/esup-siscol-0.1.11.jar {path}/tomcat/webapps
unzip esup-siscol-0.1.11.jar -d esup-siscol
cd esup-siscol/WEB-INF/classes/
cp application.yml.sample application.yml
```
dans le fichier application.yml configurer la partie LDAP, apogee (urls de services) et userscredential



## en mode java (alternative)
 ***Attention***  à adapter selon le fonctionnement de chaque établissement,
- The `etc` directory contains the configuration files and directories that need to be copied to `/etc/apogee/config`. --example usage : 

```bash
cd path/esup-siscol
./cp -r etc/esup-siscol/ /etc/esup-siscol/
java -server  -noverify -Xmx2048M -jar target/esup-siscol{version}.jar  --spring.config.location=/etc/esup-siscol/
```

### Clear Maven Cache

If you need to, on Linux/Unix systems, you can delete all the existing artifacts (artifacts and metadata) Maven has downloaded using:

```bash
# Only do this when absolutely necessary
rm -rf $HOME/.m2/
mvn dependency:purge-local-repository -DactTransitively=false -DreResolve=false
```

Same strategy applies to Windows too, provided you switch `$HOME` to its equivalent in the above command.

# Deployment

* TODO

## Executable WAR

```bash
java -server  -noverify -Xmx2048M -jar target/esup-siscol{version}.jar --spring.config.location=etc/esup-siscol/
```

## External

Deploy the binary web application file `esup-siscol.jar` after a successful build to a servlet container of choice.

## Docker

The following strategies outline how to build and deploy APOGEE Docker images.


### Dockerfile

You can also use the native Docker tooling and the provided `Dockerfile` to build and run CAS.
All configurations must be in {path_to_project}/etc/apogee/conf, Dockerfile must copy this directory on docker image

```bash
chmod +x *.sh
./docker-build.sh
./docker-start.sh
./docker-stop.sh
```


APOGEE WS à partir de la version 62031 ...Referentiel overlay Template
=======================

Generic  Referentiel WAR overlay to expose apogee and LDAP data . This programm  could be freely used.

# Versions

- APOGEE `6.4.x` `6.5.x`
- JDK `17`, `21`
- APOGEE WS à partir de la version `6.xxx`...

# Installation du JAR client WS-APOGEE
Avant de commencer l'installation il faut bien installer le jar client apo-webservices-client, chaque établissement doit s'assurer que la version installée correspond au web-service qui va être interroger:

```bash
mvn install:install-file -Dfile=apo-webservices-client{mettre la version}.jar -DgroupId=gouv.education.apogee -DartifactId=apo-webservices-client -Dversion={mettre la version} -Dpackaging=jar
```


# Clonage et installation
 
``` 
git clone https://github.com/EsupPortail/esup-siscol.git esup-siscol
cd esup-siscol/etc 
cp esup-siscol /etc/

cp application.yml.sample application.yml

vim application.yml
vim logback.xml 

```

# Les branches
Pour visualiser la branche de travail

```
git status
```

Pour visualiser toute les branches (locales et distantes)

```
git branch -a 
```

Pour changer de banche et se mettre sur la branche esup-siscol-dev-pgase par exemple

```
git checkout esup-siscol-dev-pgase
mvn clean install
```
le war est généré dans le répertoire target/

# Paramétrage :
Le fichier esup-siscol/etc/esup-siscol/application.yml.sample est à copier dans /etc/esup-siscol/application.yml et paramétrer

## Mode de fonctionnement :
pour activer ou désactiver un apogee/pegase mettre la valeur à true ou à false,
 
	app:
		mode_apogee: true
		mode_pegase: true
 

## La partie LDAP : renseigner les paramtères LDAP 

	ldap:
    	urls: 
    		- ldap://ldap-paris.fr:389
       username: uid=xxxx,ou=admins,dc=u-paris10,dc=fr
       password: xxxx
       base: dc=u-paris10,dc=fr
       ldap:
       stringFilterTeacher: (|(eduPersonAffiliation=teacher)(eduPersonAffiliation=faculty)(uid=sample))
       stringFilterStudent: (eduPersonAffiliation=student)
       stringFilterStaff: (eduPersonAffiliation=staff)
       attributes:
                objectClass: Person
                supannAliasLogin: supannAliasLogin
                baseDn: ou=people
                dn: dn
                etc ...


## La partie  APOGEE

	  administratifMetier: http://ws.uni.fr:8080/aws/services/AdministratifMetier
      tudiantMetier: http://ws.uni.fr:8080/aws/services/EtudiantMetier
      pedagogiqueMetier: http://ws.uni.fr:8080/aws/services/PedagogiqueMetier
      geographieMetier: http://ws.uni.fr:8080/aws/services/GeographieMetier
      referentielMetier: http://ws.uni.fr:8080/aws/services/ReferentielMetier
      offreFormationMetier: http://ws.uni.fr:8080/aws/services/OffreFormationMetier


## La Partie PcScol

	pcscol:
           codeStructure: ETAB00
           codePeriode: PER-2020
           codesPeriodesChargementFormations: PER-2021, PER-2022, PER-2023, PER-2024, PER-2018, ..
           accesstoken:
                      casUrl: https://authn-app.xxxxxxxx.pc-scol.fr/cas/v1/tickets
                      # Username et password pour s'authentifier auprs du serveur OAuth Pgase en tant qu'applicatif MDW
                      svcAcountLogin: svc-api
                      svcAcountPassword: ******************
                      # Dure en heure de conservation de l'access-token
                      duration: 6
           # Code de l'tablissement dans Pgase
           etablissement: ETAB00
           # Base Url de l'API du module INS de Pgase (attention  conserver la structure de l'url d'exemple)
           api:
               # Base Url de l'API CHC de Pgase
               chc:
                   url: https://chc.xxxxxxxxx.pc-scol.fr/api/chc/v6
               # Base Url de l'API ODF de Pgase
               odf:
                   url: https://odf.xxxxxxxxx.pc-scol.fr/api/odf/v1
               # Base Url de l'API COF de Pgase
               ref:
                   url: https://ref.xxxxxxxxx.pc-scol.fr/api/v1/ref
               ins:
                   url: https://ins.xxxxxxxxx.pc-scol.fr/api/v5/ins
           # Permet de cibler un dossier par dfaut. /!\ Attention /!\ A renseigner uniquement pour une dmonstration ou en phase de test/dveloppement.
           demo:
                codeapprenant: 000000001
                 # Liste des statuts des inscriptions  afficher dans la vue "Parcours" spars par des virgules
           inscription:
                      statut: valide
	

## Définition du mot de passe d'accés au service

	credential:
		userscredential:
    		root: # ici le login est root
      			username: {le nom qui va être affiché}
      			password: {password}
      			roles:
        				- ADMIN
      			


## Version Apogee
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
cp etc/esup-siscol/application.yml.sample /etc/esup-siscol/application.yml
##configurer la partie LDAP, apogee (urls de services) et userscredential
mvn  spring-boot:run
```


# Configuration

## en mode Tomcat:
```
cp target/esup-siscol-0.1.11.war {path}/tomcat/webapps/esup-siscol.war
```
dans le fichier application.yml configurer la partie LDAP, apogee (urls de services) et userscredential




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
java -server  -noverify -Xmx2048M -jar target/esup-siscol{version}.jar
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


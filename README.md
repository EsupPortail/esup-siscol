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

une fois le jar installer, il faut ajouter  la bonne dépendance dans le pom.xml	
			
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




Attetion ici c'est une option, à adapter selon le fonctionnement de chaque 
- The `etc` directory contains the configuration files and directories that need to be copied to `/etc/apogee/config`. --example usage : 

```bash
cd path/apogee
./cp -r etc/conf /etc/apogee/conf/
java -server  -noverify -Xmx2048M -jar target/apogee-0.0.1-SNAPSHOT.jar --spring.config.location=/etc/apogee/conf/
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
java -server  -noverify -Xmx2048M -jar target/apogee-0.0.1-SNAPSHOT.jar --spring.config.location=etc/apogee/conf/
f/
```

## External

Deploy the binary web application file `apogee.jar` after a successful build to a servlet container of choice.

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


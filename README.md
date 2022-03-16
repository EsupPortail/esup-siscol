Referentiel overlay Template
=======================

Generic  Referentiel WAR overlay to expose apogee and LDAP data . This programm  could be freely used.

# Versions

- APOGEE `6.4.x`
- JDK `11`, `8`
- APOGEE WS `6.xxxxxxx`
- mvn install:install-file -Dfile=apo-webservices-client62031.jar -DgroupId=gouv.education.apogee -DartifactId=apo-webservices-client -Dversion=62031 -Dpackaging=jar

# Overview

To build the project, use:

```bash
# Use --refresh-dependencies to force-update SNAPSHOT versions
./mvnw[.bat] clean build
./mvnw spring-boot:run
```

To see what commands are available to the build script, run:

```bash
./mvnw[.bat] tasks
```
# Apogee WS client
Pour le génération du jar apo-wsl lien .....

# Configuration

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


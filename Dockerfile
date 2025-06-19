# Utiliser une image Alpine Linux comme base
FROM eclipse-temurin:21-jdk-alpine
# Install base packages
RUN apk update
RUN apk upgrade
#RUN apk add ca-certificates && update-ca-certificates
# Change TimeZone
RUN apk add --update tzdata
ENV TZ=Europe/Paris
# Clean APK cache
RUN rm -rf /var/cache/apk/*
# Installer les dépendances nécessaires


RUN rm -rf /var/cache/apk/*

RUN mkdir -p esup-siscol 
RUN mkdir -p /etc/esup-siscol

#COPY etc/resolv.conf /etc/resolv.conf
COPY  target/esup-siscol-2.0.2-RC.war /esup-siscol/esup-siscol.jar
#apres configuration de  application.yml
COPY  etc/esup-siscol /etc/esup-siscol
EXPOSE 8080

ENV PATH $PATH:$JAVA_HOME/bin:.

WORKDIR esup-siscol
ENTRYPOINT ["java", "-server", "-noverify", "-Xmx2048M", "-jar", "esup-siscol.jar"]

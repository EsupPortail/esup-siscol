FROM adoptopenjdk/openjdk11:alpine-slim

# Install base packages
RUN apk update
RUN apk upgrade
RUN apk add ca-certificates && update-ca-certificates
# Change TimeZone
RUN apk add --update tzdata
ENV TZ=Europe/Paris
# Clean APK cache
RUN rm -rf /var/cache/apk/*

RUN mkdir -p esup-siscol 
RUN mkdir -p /etc/esup-siscol

#COPY etc/resolv.conf /etc/resolv.conf
COPY  target/esup-siscol-0.1.14.war  /esup-siscol/esup-siscol.jar
COPY  etc/esup-siscol /etc/esup-siscol
EXPOSE 8080

ENV PATH $PATH:$JAVA_HOME/bin:.

WORKDIR esup-siscol
ENTRYPOINT ["java", "-server", "-noverify", "-Xmx2048M", "-jar", "esup-siscol.jar"]

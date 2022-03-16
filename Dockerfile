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

RUN mkdir -p apogee
RUN mkdir -p /etc/apogee/config

COPY etc/resolv.conf /etc/resolv.conf
COPY  target/apogee-0.0.1-SNAPSHOT.jar  /apogee/apogee.jar
COPY  etc/apogee/conf /etc/apogee/conf
EXPOSE 8080

ENV PATH $PATH:$JAVA_HOME/bin:.

WORKDIR apogee
ENTRYPOINT ["java", "-server", "-noverify", "-Xmx2048M", "-jar", "apogee.jar", "--spring.config.location=/etc/apogee/conf/"]

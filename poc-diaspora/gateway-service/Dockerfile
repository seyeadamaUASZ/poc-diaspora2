FROM openjdk:11
VOLUME /tmp
ADD target/gateway-service-0.0.1-SNAPSHOT.jar gateway.jar
EXPOSE 9999
ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar /gateway.jar
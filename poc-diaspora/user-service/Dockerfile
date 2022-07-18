FROM openjdk:11
VOLUME /tmp
ADD target/user-service-0.0.1-SNAPSHOT.jar userservice.jar
EXPOSE 8085
ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar /userservice.jar
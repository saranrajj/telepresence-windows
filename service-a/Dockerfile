FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/service-a-0.0.1-SNAPSHOT.jar 
ADD ${JAR_FILE} service-a.jar 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/service-a.jar"]
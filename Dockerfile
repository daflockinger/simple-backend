FROM openjdk:17-alpine3.14
MAINTAINER flo
COPY target/simple-backend-0.0.1-SNAPSHOT.jar simple-backend-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/simple-backend-0.0.1-SNAPSHOT.jar"]

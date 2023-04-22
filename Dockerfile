FROM openjdk:19-jdk
MAINTAINER Joran Van Belle
COPY /target/EDA-webshop-0.0.1-SNAPSHOT.jar EDA-webshop-0.0.1.jar
ENTRYPOINT ["java","-jar","/EDA-webshop-0.0.1.jar"]
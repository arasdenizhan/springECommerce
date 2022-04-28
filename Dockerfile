#FROM --platform=linux/x86_64 eclipse-temurin:11-jre-alpine
#FROM eclipse-temurin:11-jre-alpine
FROM openjdk:11.0.15-jre
COPY target/*.jar app.jar
ENTRYPOINT ["java","-Xmx512M","-jar","app.jar"]

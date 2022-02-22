FROM --platform=linux/x86_64 eclipse-temurin:11-jre-alpine
COPY /target/*.jar /app.jar
ENTRYPOINT ["java","-jar","app.jar"]

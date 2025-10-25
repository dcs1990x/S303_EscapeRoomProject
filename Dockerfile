FROM openjdk:21-slim

WORKDIR /app

COPY target/*-jar-with-dependencies.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
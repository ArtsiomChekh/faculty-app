FROM openjdk:17-jdk-slim-buster AS build
WORKDIR /app
COPY target/faculty-app-1.0.2-SNAPSHOT.jar /app/faculty-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "faculty-app.jar"]
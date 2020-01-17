FROM openjdk:11-jre-slim

COPY target/*.jar /usr/app/app.jar
WORKDIR /usr/app

CMD ["java", "-jar", "app.jar"]

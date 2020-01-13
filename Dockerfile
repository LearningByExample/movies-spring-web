FROM openjdk:8-jdk

COPY target/*.jar /usr/app/app.jar
WORKDIR /usr/app

CMD ["java", "-jar", "app.jar"]

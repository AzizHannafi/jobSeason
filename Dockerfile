# Build stage
FROM maven:3-amazoncorretto-17 AS build
COPY src /home/injob-application/src
COPY pom.xml /home/injob-application
RUN mvn -f /home/injob-application/pom.xml clean install

# Package stage
FROM openjdk:17-alpine
COPY --from=build /home/injob-application/target/*.jar /injob-back.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/injob-back.jar"]
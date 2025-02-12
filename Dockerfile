FROM maven:3.8-openjdk-11-slim AS builder

FROM openjdk:25-jdk-slim

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["mvn", "spring-boot:run"]

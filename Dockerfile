FROM openjdk:11-jdk-slim

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN chmod +x ./mvnw
RUN ./mvnw package -DskipTests

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "target/lianjiascraper-0.0.1-SNAPSHOT.jar"]

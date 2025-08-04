FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-alpine
LABEL authors="chas"

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

ARG JAR_FILE=target/*.jar
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
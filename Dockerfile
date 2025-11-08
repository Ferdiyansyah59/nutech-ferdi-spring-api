FROM maven:3.9-eclipse-temurin-25 AS builder

WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
RUN ./mvnw package -DskipTests

FROM eclipse-temurin:25-jre-jammy

WORKDIR /app
COPY --from=builder /app/target/nutech-ferdi-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 18011
ENTRYPOINT ["java", "-jar", "app.jar"]
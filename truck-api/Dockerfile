FROM maven:3.6.3-openjdk-11-slim AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ /build/src
RUN mvn -f /build/pom.xml clean package

FROM openjdk:11.0.8-jre-slim
COPY --from=build /build/target/truck-api-0.0.1.jar /app/truck-api-0.0.1.jar
EXPOSE 8080
CMD exec java $JAVA_OPTS -jar /app/truck-api-0.0.1.jar

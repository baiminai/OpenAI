FROM maven:3.8.1-jdk-8-slim as builder

MAINTAINER white

# Copy local code to the container image.
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build a release artifact.
RUN mvn package -DskipTests

# Run the web service on container startup.
ENTRYPOINT ["java","-jar","/app/target/OpenAI-0.0.1-SNAPSHOT.jar"]
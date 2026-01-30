# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim as build

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw mvnw.cmd pom.xml ./
COPY .mvn .mvn

# Make Maven wrapper executable
RUN chmod +x ./mvnw

# Copy source code
COPY src src

# Build the application
RUN ./mvnw clean package -DskipTests

# Runtime stage
FROM openjdk:17-jre-slim

WORKDIR /app

# Copy the built JAR file
COPY --from=build /app/target/workflow-0.0.1.jar app.jar

# Expose port
EXPOSE 8080

# Set environment variables
ENV SPRING_PROFILES_ACTIVE=prod

# Run the application
CMD ["java", "-Dserver.port=${PORT:-8080}", "-jar", "app.jar"]
FROM openjdk:17-jre-slim

# Set working directory
WORKDIR /app

# Copy the JAR file
COPY target/workflow-0.0.1.jar app.jar

# Expose port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Run the application
CMD ["java", "-jar", "app.jar"]
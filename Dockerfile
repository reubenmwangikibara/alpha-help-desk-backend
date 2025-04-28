# Use an official Java image
FROM openjdk:17-jdk-slim
  
  # Set the working directory
WORKDIR /app
  
  # Copy the jar to the container
COPY target/alpha-help-desk-backend-0.0.1-SNAPSHOT.jar alpha-help-desk-backend.jar
  
  # Run the jar file
ENTRYPOINT ["java", "-jar", "alpha-help-desk-backend.jar"]

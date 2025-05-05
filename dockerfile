# Use official OpenJDK base image
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/productserv-0.0.1-SNAPSHOT.jar productserv.jar

# Expose the port your app runs on (default 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "productserv.jar"]

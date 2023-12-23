FROM eclipse-temurin:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR file
COPY target/adventure-book-0.0.1-SNAPSHOT.jar app.jar

# Copy the database file
COPY src/main/resources/data/h2_testDB.mv.db /app/src/main/resources/data/h2_testDB.mv.db

# Expose port 8080
EXPOSE 8080

# Set the entry point
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
# Multi-stage Docker build for Jikan Calendar

# Stage 1: Build the application
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM tomcat:10.1-jre17-temurin-jammy

# Remove default Tomcat applications
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy WAR file from build stage
# Deploy as ROOT.war to make it accessible at root path /
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Set environment variables
ENV CATALINA_OPTS="-Xmx512m -Xms256m -XX:+UseG1GC"
ENV JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom"

# Expose port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/calendar/ || exit 1

# Start Tomcat
CMD ["catalina.sh", "run"]

# Use official Tomcat 8.5 image
FROM tomcat:8.5-jdk8-temurin

# Remove default apps (optional but cleaner)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your WAR file
COPY ./app/GadgetHub-Website.war /usr/local/tomcat/webapps/ROOT.war

# Expose Tomcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
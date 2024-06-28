# Use the official Tomcat image from the Docker Hub
FROM tomcat:9.0-jdk17

# Remove the default ROOT webapp
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Add your WAR file to the webapps directory
COPY target/curd-app.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080 to the outside world
EXPOSE 8080

# Docker use a given image with its tag as build-base. If this image is not in the local library, an online-search on DockerHub
FROM openjdk:8
# Make port 8081 available to the world outside this container
EXPOSE 8081
# An argument that can be set when the container is started by command, we must add --build-arg and then name of argument and new value
ARG JAR_FILE=target/sample-0.0.1-SNAPSHOT.jar
# Add the application's jar to the container as nwe layer
ADD  ${JAR_FILE} sample.jar
# This is where you configure how the application is executed inside the container.
ENTRYPOINT ["java","-jar","/sample.jar"]
# Alternative solution for ENTRYPOINT, the difference is that the CMD sets default command and/or parameters,
# which can be overwritten from command line when docker container runs.
#CMD java -jar /sample.jar
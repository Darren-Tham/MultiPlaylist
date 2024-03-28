FROM openjdk:23
WORKDIR /app
RUN rm -f /app/*.war
COPY ./target/multiplaylist.jar /app
EXPOSE 8443
CMD ["java", "-jar", "-Dspring.profiles.active=docker", "multiplaylist.jar"]
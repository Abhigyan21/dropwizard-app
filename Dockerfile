FROM openjdk:11
WORKDIR /app
COPY target/dropwizard-app-1.0-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "/app/dropwizard-app-1.0-SNAPSHOT.jar", "server", "config.yml"]
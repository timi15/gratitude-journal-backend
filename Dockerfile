FROM openjdk:21-jdk-slim
COPY target/gratitude-journal-0.0.1-SNAPSHOT.jar gratitude-journal.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/gratitude-journal.jar"]
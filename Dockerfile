FROM eclipse-temurin:17
WORKDIR /home
COPY ./target/backend-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]
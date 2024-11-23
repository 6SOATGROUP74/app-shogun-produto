FROM openjdk:17-alpine


COPY ./target/produto-0.0.1.jar app.jar

ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java", "-jar", "/app.jar"]

EXPOSE 8080
FROM openjdk:8-jre-slim
WORKDIR /home
COPY /target/shape-service.jar shape-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "shape-service.jar"]

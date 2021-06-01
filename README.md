# Spring-boot-data-H2-embedded

This is an example to create a new square shape if there is no other overlapped square shape exist.

I have used the H2 in-memory database for demo purpose
After starting the app, DB avail at http://localhost:8080/h2-console 

**To Run just the jar file**

java -jar target/shape-service.jar

**To Run as spring boot**
mvn spring-boot:run

**How to test**

1.
/post http://localhost:8080/shape
Headers 
Content-Type application/json

{"name":"FOUR","bottomLeft":{"xCoordinate":6,"yCoordinate":6},"topRight":{"xCoordinate":7,"yCoordinate":7}}

2.
/get http://localhost:8080/shapes


**To Run without Docker**
```
> mvn clean install
> java -jar target/shape-service.jar
```

**To Run with Docker**
//NOTE: In the future, we can consider a maven plugin for docker.
```
> mvn clean install
> docker build -f Dockerfile -t shape-service-for-pupil:latest .
> docker run -d -p 8080:8080 shape-service-for-pupil:latest

> docker stop <container-id>
```

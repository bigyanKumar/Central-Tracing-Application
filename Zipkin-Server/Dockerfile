FROM openjdk:8
WORKDIR /app
COPY target/Zipkin-Server-0.0.1-SNAPSHOT.jar /app/
VOLUME /app/mydata
CMD ["java", "-jar", "Zipkin-Server-0.0.1-SNAPSHOT.jar"]

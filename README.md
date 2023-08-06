# Central-Tracing-Application
Steps: -
1. Build the zipkin server in your local 
      mvn clean install -DskipTests
2. Build the Docker image in Zipkin-server
    docker build -t zipkin:latest .
    Go inside each services and build the docker.
    {
        1. docker build -t desktop_service:latest .
        2. docker build -t notification_service:latest .
        3. docker build -t payment_service:latest .
        4. docker build -t restaurant_service:latest .
        5. docker build -t user_service:latest .
    }
3. docker compose -f docker-compose.yml up -d

4. docker ps

5. Run all applications after that.

Please download the Offset Explorer for kafka clustor configuration.
https://www.kafkatool.com/download.html

<img width="1276" alt="Screenshot 2023-07-29 at 2 31 41 PM" src="https://github.com/bigyanKumar/Central-Tracing-Application/assets/97912572/c48da482-4eb7-4806-aee1-004ef8058371">

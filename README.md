# Central-Tracing-Application
Steps: -
1. Build the zipkin server in your local 
      mvn clean install -DskipTests
2. Build the Docker image in Zipkin-server
    docker build -t zipkin:latest .
3. docker compose -f docker-compose.yml up -d

4. docker ps

5. Run all applications after that.

Please download the Offset Explorer for kafka clustor configuration.
https://www.kafkatool.com/download.html

<img width="1276" alt="Screenshot 2023-07-29 at 2 31 41 PM" src="https://github.com/bigyanKumar/Central-Tracing-Application/assets/97912572/c48da482-4eb7-4806-aee1-004ef8058371">


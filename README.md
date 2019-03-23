# Ai-application
Back-end application part

# How to run application
1
  * Run postgres database on port 5434
    - docker command : `docker run -e POSTGRES_PASSWORD=docker -d -p 5434:5432 postgres:9.3`
  * Run activemq
    - docker command: `docker run -p 61616:61616 -p 8161:8161 -d -t webcenter/activemq`
  * Run the application, application by default will be available on port 8081

2
  * In terminal run command : `docker-compose up`
  * Run the application, application by default will be available on port 8081

# Jms
1. `http://localhost:8161/admin/queues.jsp` - here you can see more information about jms queue, for example how many messages are enqueued

# Swagger
1. Swagger documentation will be available on endpoint `*/swagger-ui.html`

# ai-application
Back-end application part

# How to run application
1. Run postgres database on port 5434
    - docker command : `docker run -e POSTGRES_PASSWORD=docker -d -p 5434:5432 postgres:9.3`
2. Run activemq
    - docker command: `docker run -p 61616:61616 -p 8161:8161 -t webcenter/activemq`
3. Run the application, application by default will be available on port 8081


#Jms
1. `http://localhost:8161/admin/queues.jsp` - here you can see more information about jms queue, for example how many messages are enqueued



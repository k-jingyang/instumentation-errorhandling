## Instrumentation & Error Handling 
This project is to explore the following:
- [x] Error handling for REST calls
- [x] Error tracing (A unique id for each request that can be used to filter logs), a concept similar to distributed tracing but different 
- [ ] Capturing relevant technical metrics
- [x] Exploration of sending syslog logs 

## Logging Configuration
Look at `src/main/resources/logback-spring.xml` to look at how our logging should be configured

## Syslog Exploration
1. `$ docker-compose up` to start the syslog server and client. Client is used only for debugging (it's not necessary)
1. `$ docker inspect <SYSLOG_SERVER_CONTAINER>` to check the IP address of the container running the syslog server. 
The syslog server container can be identified by looking at the mapped ports. Only the server container has their ports mapped.
1. At `src/main/resources/application.properties`, place the IP address in the property `syslog.remote_server=<IP_ADDRESS_OF_SYSLOG_SERVER>`
1. Start your application, any logs of INFO level and above will be sent to the syslog server. You can test this out by running the gradle task `bootRun` to start the server, and navigating to [localhost:8080]()
1. You should be able to view the logs in `syslog/logs`
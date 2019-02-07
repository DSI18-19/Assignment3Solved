# Assignment 3: microservices 

## Base Code
We are going to use the example of Users that publish Notes. You have a base code in a Intellij project with two modules.
One containing a REST API that deals with Users (running on port 8090) and another dealing with Notes (running on port 8091).

See that both services has its own database.

## 1.- A reverse proxy
A reverse proxy implemented with the Zuul routing service created by Netflix within its OSS program. 
In order to develop this service you can follow this tutorial: https://spring.io/guides/gs/routing-and-filtering/

Basically you'll need to create a new module with the reverse proxy running at port 8080 where /users points to port 8090
and /notes points to port 8091

## 2.- Service discovery
Once the reverse proxy works, add a new discovery service so that all services register to it. You can follow the example: 
https://spring.io/guides/gs/service-registration-and-discovery/

Note that you may need to modify the configuration of the zuul reverse proxy

## 3.- Synchronously and safely connect the note with the user services
We are going to connect the two services when a new note from a user is stored

**Notes microservice:**

When a new note is saved in its database the notes microservice needs to ask whether the user exists and save the note only 
in case she exists. It will ask to the user microservice

**User microservice:**

It needs to provide a query (userExists) where it is given the username and it returns true or false. This query is provided as a 
GET REST call

**Robustness:**

You'll need to implement: 
* Circuit breaker with the NetFlix Hystrix library: You'll need to consider what to do when the circuit is open and the 
notes service is not sure whether the user exists
* Client load balancer with the Ribbon NetFlix library 
* Activate the monitor to check the service health with dependency Actuator

You can follow the examples:
* Circuit breaker: https://spring.io/guides/gs/circuit-breaker/
* Client load balancer: https://spring.io/guides/gs/client-side-load-balancing/
* Monitoring with Actuator: https://spring.io/guides/gs/actuator-service/


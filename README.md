# Simple Backend App

Small application with POST endpoint protected by Basic Auth.
The post endpoint takes a payload in form of JSON and stores the information into a PostgreSQL database.
Written in Kotlin (Java 17 base) with Spring Boot.


## Build application

As a prequisite you need to have Java 17, docker and docker-compose installed on your local system.
Just execute the start script with:
```sh
sh start.sh
```
to compile, test, build and start the application together with the PostgreSQL database.


## Sample CURLs for running application

To insert sample sensor data just execute:
```sh
curl -v -u admin:admin -X POST -H "Content-Type: application/json" -d '{"sensorName":"Cryo dynamics flux sensor 44","value":-662.2,"unit":"DEGREE_KELVIN"}' http://localhost:8080/telemetry
```

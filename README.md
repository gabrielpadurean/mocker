# Name
mocker :heart: :rainbow:

# Description
Web application that behaves as a mock server.

## Package structure
Application consists of four main packages:
+ `api` used for the API exposed by the application
+ `web` used for the web layer of the application
+ `service` used for the service layer of the application

## Running from command line
Follow these steps to start the application:
+ run `mvn clean install`
+ run `mvn spring-boot:run` or `java -jar target/mocker-1.0.0-SNAPSHOT.jar` to start the application

## API
The definition of the **REST API** can be found in the `mocker-v1.yaml` file.  
It is defined based on the Open API specifications that can be found here: https://github.com/OAI/OpenAPI-Specification  

The **REST API** can have multiple versions and each new version of the API should have a separate definition file.  
For version 1 there should be the `mocker-v1.yaml` file.  
For version 2 there should be the `mocker-v2.yaml` file.  

## Exception handling
TBA

## Validation
TBA

## Storage
TBA

## Logs
There are two main files for logging, the `server_log.log` and the `access_log.log`.  
The `server_log.log` is used as the main file for logging, all details are logged here.  
The `access_log.log` is used for logging all requests accessing the application.  

## UI
TBA
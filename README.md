# Name
mocker :heart: :rainbow:

# Description
Web application that behaves as a mock server.  
The application is built using the 3 layer architecture, with the major layers being presentation - business - dao.  
While having an UI the clients can also interaction programmatically with the application using the exposed **REST API**.  

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

The JSON structure of a mapping:  
```json
{
    "id": "",
    "name": "test",
    "description": "Test description",
    "request": {
        "endpoint": "/test",
        "method": "GET"
    },
    "response": {
        "status": "200",
        "body": "Something",
        "headers": [
            {
                "key": "HeaderOne",
                "value": "HeaderOneValue"
            },
            {
                "key": "HeaderTwo",
                "value": "HeaderTwoValue"
            }
        ]
    }
}
```

## Exception handling
All exceptions that are thrown from the application have a **HTTP** status code corespondent.  
For application exceptions that are not specifically handled by the code a **500 Internal Server Error** response is returned.   
Check the `exception` package for examples of possible exception thrown upon request validation or during other actions in the application.  

## Validation
Each **API** request is validated using a series of validators that can be found in the `validation` package.  
The validators are applied in a certain order and it is important to keep know this in case of changes or refactoring.  

## Postman
The collection from **Postman** contains example requests that can be used to make calls to the **API**.  
The collection requests also contain basic integration tests that can be used to make a basic validation of the **API**.  

## Storage
**MySQL** is used to store the **Mapping** entities.  
To start the application a running MySQL instance is required.  
Check the `application.properties` file for details related to the MySQL connection.  

## Logs
There are two main files for logging, the `server_log.log` and the `access_log.log`.  
The `server_log.log` is used as the main file for logging, all details are logged here.  
The `access_log.log` is used for logging all requests accessing the application.  

## UI
The application web layer (consisting of html/css/js) interacts with the REST API using ajax calls from Javascript.  
The html pages are loaded as static resources and the actual data and actions are performed from the Javascript files.  
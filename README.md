# Name
mocker

# Description
Web application that behaves as a mock server.

## Package structure
Application consists of four main packages:
+ `api` used for the API exposed by the application
+ `web` used for the web layer of the application

## Running from command line
Follow these steps to start the application:
+ run `mvn clean install`
+ run `mvn spring-boot:run` or `java -jar target/mocker-0.0.1-SNAPSHOT.jar` to start the application
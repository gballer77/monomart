# Monomart

Monomart is a sample application written with Spring Boot that is intended to serve as a medium in which to teach and exercise the decomposition of a monolith into separate services.   

## Components

The makeup of Monomart is a Spring Boot web application, with a react frontend being served out from the embedded Tomcat.  This means that when the application is run, the UI is accessible from your browser on https://localhost:8080 (by default).

## To run the application

Ensure that you are running the application with Java 17.  Execute `./gradlew bootrun` from the root of the project.  This will:
* Build the frontend
* Copy frontend to backend static folder
* Build the backend
* Run the application


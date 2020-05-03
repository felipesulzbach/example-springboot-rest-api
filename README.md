# API REST

Simple example of a REST API with Swagger UI.

___

## Pré-requisitos

- Eclipse;
- PostgreSQL;
- Maven;
- Postman.

## About Springboot

Springboot is a project that is part of the Spring ecosystem. It helps in creating Standalone applications. It offers us a structure with the initial settings, necessary to start the implementation.

Here are some benefits:

- allows you to have a more organized project;
- help with infrastructure tasks;
- helps to take care of the project's infrastructure settings;
- increases productivity;
- facilitates the installation of the project in production.

## Project information

- **Documentation link:** http://localhost:8001/swagger-ui.html
- **Endpoints domain:** http://localhost:8001/api

The project is using the following dependencies:

- DevTools
- Web
- JPA
- PostgreSQL
- H2 Database (...for unit test, not yet implemented)
- Flyway
- Swagger UI

## Start Application

Remembering that Springboot uses tomcat as a Servelet Container, it will only be necessary to start the application.

### Create database

With PostgreSQL previously installed:
- open the PgAdmin program (which is part of the installation package);
- create a new database with the name **springboot_db**;
- ready, the bank structure will be created by *Flyway*, the moment the application is started.

### Start

- open the `example-springboot-rest-api/src/main/java/ExampleSpringbootRestApiApplication` file;
- right-click and select `Run As > 2 Java Application`;
- open *Postman* and import the `example-springboot-rest-api/resources/java.postman_collection.json` file;

## Future goals

- Implement unit tests;
- Add the *Docker*;

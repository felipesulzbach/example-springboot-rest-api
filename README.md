# API REST

Simple example of a REST API with Swagger UI.

___

## Prerequisites

- Eclipse IDE;
- Maven;
- Docker;
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

- **Documentation link:** `http://localhost:8001/api/swagger-ui.html`
- **Endpoints domain:** `http://localhost:8001/api`

The project is using the following dependencies:

- DevTools
- Web
- JPA
- PostgreSQL
- H2 Database (...for unit test, not yet implemented)
- Flyway
- Swagger UI

## Up the environments

Remembering that Springboot uses tomcat as a Servelet Container, it will only be necessary to start the application.

### Start Docker

To create the environment with the database:
- open the api directory in CMD;
- execute the command: `docker-compose -f docker-compose.local.yml up --build`;
- keep the CMD window open;
- Other commands:
    - To undo the environment, execute the command: `docker-compose -f docker-compose.local.yml down`;
    - To delete all images from the docker, run the `mvnw.cmd file`.

## Start Aplication on Eclipse IDE

- open the `example-springboot-rest-api/src/main/java/ExampleSpringbootRestApiApplication` file;
- right-click and select `Run As > 2 Java Application`;
- open *Postman* and import the `example-springboot-rest-api/resources/java.postman_collection.json` file;

## Access PgAdmin:
- open your browser end access the link `http://localhost:9090`;
- enter the access data:
    - user: `admin@admin`;
    - password: `admin`;
- right click on **Servers**, and **Create > Server...**;
- In the **General** tab inform:
    - Name: `local`;
- In the **Connection** tab inform:
    - Host name/address: `db_postgres`;
    - Port: `5432`;
    - Maintenance database: `postgres`;
    - Username: `postgres`;
    - Password: `postgres`;
- Click the **Save** button;

## Future goals

- Implement unit tests;

# API REST

Example of a REST API with Swagger UI.

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
- Flyway
- Swagger UI


### Up the environments

Remembering that Springboot uses tomcat as a Servelet Container, it will only be necessary to start the application.


### Start Docker

To create the environment with the database:
- open the api directory in CMD;
- execute the command: `docker-compose -f docker-compose.local.yml up --build`;
- keep the CMD window open;
- Other commands:
    - To undo the environment, execute the command: `docker-compose -f docker-compose.local.yml down`;
    - To delete all images from the docker, run the `mvnw.cmd file`.


### Start Aplication on Eclipse IDE

- open the `example-springboot-rest-api/src/main/java/ExampleSpringbootRestApiApplication` file;
- right-click and select `Run As > 2 Java Application`;


### Execute endpoints in Postman

- open *Postman* and import the `example-springboot-rest-api/resources/java.postman_collection.json` file;
- just select the endpoints and execute by clicking the **Send** button;


### Access PgAdmin:
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


### Application Structure

The endpoints are defined in the Controllers, the logic of each endpoint was implemented in the Services.

The business rules were separated and concentrated in only one class (by context) in order to be reunited and easily located by the developer. The classes are in the package "restapi.service.businessRules", highlighted in blue in the image.

[link image business_rules.png]


#### Transformers (Model x Response)

By default, all endpoints use a specific class to return the response. In this example, the name defined in the model was used plus the word *Resp* (short for *Response*).
To facilitate the transition of data from Model to Response and vice versa, the Transformer solution was implemented. The transformation is done from class to class or from list to list.

[link image trasformer.png]


#### Exception errors

The ExceptionController.java class concentrates all exceptions handled in the system. There is a method for each exception caught.

[link image exception.png]

The standard error response is defined in the ErrorResponse.java class.

[link image default_error_response_class.png]

The exception response handled will be as in the image below.

[link image default_error_response.png]


#### Unit tests

**TODO**


#### Build Phatterns

**TODO**


## Future goals

- An example of a Repository using Criteria and native queries for more complex queries;
- Spring Security OAuth;

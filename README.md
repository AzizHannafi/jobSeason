# InJob Project
InJob is a Java project that requires Java 17, Docker, and Keycloak to be set up and ready to accept connections. Follow the steps below to set up the project and run it locally.

## Prerequisites
Before running the InJob project, ensure you have the following prerequisites installed on your local machine:

Java 17: Make sure you have Java Development Kit (JDK) 17 or a compatible version installed.

Docker: You'll need Docker to run necessary containers for the project.

Keycloak: Install and configure Keycloak for authentication. The project assumes that Keycloak is available at http://localhost:8080. Make sure Keycloak is set up with a realm named in_job for authentication.

## Environment Variables
Before running the InJob project, you need to set the following environment variables. You can do this in your local project's environment or by using a .env file:

- API_URL: The URL of the API, typically http://localhost:8081/back/api.

- KEYCLOAK_AUTH_URL: The URL for Keycloak authentication, usually http://localhost:8080/realms/in_job.

- KEYCLOAK_BASE_URL: The base URL for Keycloak, typically http://localhost:8080.

- REALM: The Keycloak realm name, which should be set to in_job.

- SERVER_PORT: The port on which the project server should run, often set to 8081.

Make sure to update these variables with the correct values according to your Keycloak and project configuration.

## Running the Project
Once you have Java 17, Docker, Keycloak, and the environment variables set up, you can run the InJob project by following these steps:

Open a terminal and navigate to your project directory.

Make sure Docker is running and Keycloak is configured properly.

Run the following command to start the InJob project:

```bash
./gradlew bootRun
```

This will start the project on the specified port (by default, 8081).

You can access the project at the specified API_URL, e.g., http://localhost:8081/back/api.

That's it! Your InJob project should now be up and running locally. 
# Project Name

This project is built with Java 11 and utilizes Maven, JUnit 5, Cucumber, Selenium, and Docker.

## Execution

To execute the tests from the root directory, run the following command:

```bash
mvn clean test -e -Dtest=testRunner.ParallelTestRunner
```

To change the browser, use the `-Dbrowser` flag. For example, to specify Firefox as the browser:

```bash
mvn clean test -e -Dtest=testRunner.ParallelTestRunner -Dbrowser=firefox
```

Supported browser values are `chrome`, `firefox`, and `firefox-headless`. If no browser is specified, the default browser is Firefox.

## JUnit Platform Properties

The `src/test/resources/junit-platform.properties` file contains additional configurations, including the report output path and format, as well as the thread min-max count.

## Docker

The project includes a Dockerfile in the root directory. If you are using Apple Silicon M1 chips, make sure to add `--platform=linux/amd64` to the first line of the Dockerfile, as shown below:

```dockerfile
FROM --platform=linux/amd64 maven:3.8.6-jdk-11
```

To build the Docker image with the name "javamvn," use the following command:

```bash
docker build -t javamvn .
```

To start the Docker container and execute the tests, use the command:

```bash
docker run --name java-mvn --rm -it javamvn mvn test -e -Dtest=testRunner.ParallelTestRunner
```

Alternatively, you can start the Docker container and access the shell:

```bash
docker run --name java-mvn --rm -it javamvn /bin/bash
```

The container remains active until you exit. Inside the container, you can execute the standard test execution command `mvn clean test -e -Dtest=testRunner.ParallelTestRunner` multiple times or run other commands as needed.
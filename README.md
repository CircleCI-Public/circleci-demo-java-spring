# CircleCI 2.0 Java Demo Application using Gradle and Spring [![CircleCI status](https://circleci.com/gh/CircleCI-Public/circleci-demo-java-spring.svg "CircleCI status")](https://circleci.com/gh/CircleCI-Public/circleci-demo-java-spring)

This is an example application showcasing how to run a Java app on CircleCI 2.0.

This application uses the following tools: 

* Gradle
* Java 11
* PostgreSQL
* Spring Boot
* Thymeleaf

You can follow along with this project by reading the [documentation](https://circleci.com/docs/2.0/language-java/).
An older version of this sample project that uses Maven is accessible [here](https://github.com/CircleCI-Public/circleci-demo-java-spring/tree/maven) though it is no longer being actively maintained.

## Local Development

### Starting the application

Start up a PostgreSQL Database Docker container:

```
docker run -e POSTGRES_USER=postgres -e POSTGRES_DB=circle_test -p 5432:5432 -itd circleci/postgres:12-alpine
```

Start up the Java application:

```
./gradlew bootRun
```

Navigate to http://localhost:8080

![Screenshot of index page](assets/index.png?raw=true "Screenshot of index page")

## License

Copyright © 2019 CircleCI

Distributed under the MIT license, see the file LICENSE.

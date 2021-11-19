# CircleCI 2.0 Java Demo Application using Gradle and Spring [![CircleCI status](https://circleci.com/gh/CircleCI-Public/circleci-demo-java-spring.svg "CircleCI status")](https://circleci.com/gh/CircleCI-Public/circleci-demo-java-spring)

New CHANGE!

**Note:** This project is currently under substantial development to include additional use cases and show off more features of CircleCI.

If you are coming here form the [Java Language Guide](https://circleci.com/docs/2.0/language-java/#config-walkthrough) please follow along at [this revision](https://github.com/CircleCI-Public/circleci-demo-java-spring/tree/9dcdae5e2988b207e0ac9b6bb9cf8ed711fba4ad) before major changes began to take place.

This message will be removed once the CircleCI documentation matches this repository again.

---

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
```
./gradlew bootRunDev
```

Navigate to http://localhost:8080

![Screenshot of index page](assets/index.png?raw=true "Screenshot of index page")

We use the [H2 Database](https://www.h2database.com/html/main.html) in memory for
local development. You can access the datbase UI at [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
with the following credentials.

```
username: `sa`
password: `password`
JDBC URL: jdbc:h2:mem:testdb
```

## License

Copyright © 2019 CircleCI

Distributed under the MIT license, see the file LICENSE.

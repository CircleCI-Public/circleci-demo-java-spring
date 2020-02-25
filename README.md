
# Spring PetClinic Sample Application
 
[![CircleCI](https://circleci.com/gh/annapamma/spring-petclinic/tree/2.1-orbs-config.svg?style=svg)](https://circleci.com/gh/annapamma/spring-petclinic/tree/2.1-orbs-config)

This is an example application showcasing how to run a Java app on CircleCI 2.1. This application uses the [Spring PetClinic sample project](https://projects.spring.io/spring-petclinic/).
This readme includes pared down sample configs for different CircleCI features, including workspace, dependency caching, and parallelism.

To see how these steps would be configured in a 2.0 config, please check out the [master branch](https://github.com/annapamma/spring-petclinic/tree/master).

## Sample configurations: version 2.1
- [A basic build with an orb](#a-basic-build-with-an-orb)


### A basic build with an orb
```yaml
version: 2.1

orbs:
  maven: circleci/maven@0.0.12

workflows:
  maven_test:
    jobs:
      - maven/test # checkout, build, test, and upload test results
```
This config uses the language-specific orb to replace any executors, build tools, and commands available. 
Here we are using the [maven orb](https://circleci.com/orbs/registry/orb/circleci/maven), which simplifies building and testing Java projects using Maven. 
The `maven/test` command checks out the code, builds, tests, and uploads the test result. 
The parameters of this command can be customized. See the [maven orb docs](https://circleci.com/orbs/registry/orb/circleci/maven) for more information.


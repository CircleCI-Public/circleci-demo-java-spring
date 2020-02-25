
# Spring PetClinic Sample Application
 
[![CircleCI](https://circleci.com/gh/annapamma/spring-petclinic.svg?style=svg)](https://circleci.com/gh/annapamma/spring-petclinic)

This is an example application showcasing how to run a Java app on CircleCI 2.0. This application uses the [Spring PetClinic sample project](https://projects.spring.io/spring-petclinic/).

This readme includes pared down sample configs for different CircleCI features, including workspace, dependency caching, and parallelism.

To see how we would use orbs to checkout, build, and test in one line, please see the [2.1-orbs-config branch](https://github.com/annapamma/spring-petclinic/tree/2.1-orbs-config).

To see these features in a version 2.1 config with reusable commands and running on different executors, please see the [2.1-simplified-config branch](https://github.com/annapamma/spring-petclinic/tree/2.1-simplified-config).
 
## Sample configurations: version 2.0
- [A basic build](#a-basic-build)
- [Using a workflow to build then test](#using-a-workflow-to-build-then-test)
- [Caching dependencies](#caching-dependencies)
- [Persisting build artifacts to workspace](#persisting-build-artifacts-to-workspace)
- [Splitting tests across parallel containers](#splitting-tests-across-parallel-containers)
- [Storing code coverage artifacts](#storing-code-coverage-artifacts)
- [All together](#all-together)

### A basic build
```yaml
version: 2.0

jobs:
  build:
    docker:
      - image: circleci/openjdk:stretch
    steps:
      - checkout
      - run: ./mvnw package
```

Version 2.0 configs without workflows will look for a job entitled `build`.
A job is a essentially a series of commands run in a clean execution environment. Notice the two primary parts of a job: the executor and steps. In this case, we are using the `docker` executor and passing in a [CCI convenience image](https://circleci.com/docs/2.0/circleci-images/). 

### Using a workflow to build then test
```yaml
version: 2.0

jobs:
  test:
    docker:
      - image: circleci/openjdk:stretch
    steps:
      - checkout
      - run: ./mvnw test

  build:
    docker:
      - image: circleci/openjdk:stretch
    steps:
      - checkout
      - run: ./mvnw -Dmaven.test.skip=true package

workflows:
  version: 2

  build-then-test:
    jobs:
      - build
      - test:
          requires:
            - build
```
A workflow is a dependency graph of jobs. This basic workflow runs a `build` job followed by a `test` job. 
The `test` job will not run unless the `build` job exits successfully. 

### Caching dependencies
```yaml
version: 2.0

jobs:
  build:
    docker:
      - image: circleci/openjdk:stretch
    steps:
      - checkout
      - restore_cache:
          keys:
            - v1-dependencies-{{ checksum "pom.xml" }} # appends cache key with a hash of pom.xml file
            - v1-dependencies- # fallback in case previous cache key is not found
      - run: ./mvnw -Dmaven.test.skip=true package
      - save_cache:
            paths:
              - ~/.m2
            key: v1-dependencies-{{ checksum "pom.xml" }}
```
The first time I ran this build [without any dependencies cached](https://circleci.com/gh/annapamma/spring-petclinic/45), it took 2m14s. Once I was able to just restore my dependencies, the [build took 39 seconds](https://circleci.com/gh/annapamma/spring-petclinic/46). 

Note that the `restore_cache` step will restore whichever cache it first matches. I add a restore key here as a fallback. In this case, even if pom.xml changes, I can still restore the previous cache. This means my job will only have to fetch the dependencies that have changed between the new pom.xml and the previous cache. 

### Persisting build artifacts to workspace
```yaml
version: 2.0

jobs:
  build:
    docker:
      - image: circleci/openjdk:stretch
    steps:
      - checkout
      - run: ./mvnw -Dmaven.test.skip=true package
      - persist_to_workspace:
         root: ./
         paths:
           - target/

  test:
    docker:
      - image: circleci/openjdk:stretch
    steps:
      - checkout
      - attach_workspace:
          at: ./target
      - run: ./mvnw test

workflows:
  version: 2

  build-then-test:
    jobs:
      - build
      - test:
          requires:
            - build
```
This special step allows you to persist files or directories to be used by downstream jobs in the workflow. In this case, the `target` directory produced by the `build` step is persisted for use by the test step. 

### Splitting tests across parallel containers
```yaml
version: 2.0

jobs:
  test:
    parallelism: 2 # parallel containers to split the tests among
    docker:
      - image: circleci/openjdk:stretch
    steps:
      - checkout
      - run: |
          ./mvnw \
          -Dtest=$(for file in $(circleci tests glob "src/test/**/**.java" \
          | circleci tests split --split-by=timings); \
          do basename $file \
          | sed -e "s/.java/,/"; \
          done | tr -d '\r\n') \
          -e test
      - store_test_results: # We use this timing data to optimize the future runs
          path: target/surefire-reports

  build:
    docker:
      - image: circleci/openjdk:stretch
    steps:
      - checkout
      - run: ./mvnw -Dmaven.test.skip=true package

workflows:
  version: 2

  build-then-test:
    jobs:
      - build
      - test:
          requires:
            - build
```
Splitting tests by timings is a great way to divide time-consuming tests across multiple parallel containers. 
I think of splitting by timings as requiring 4 parts:
1) a list of tests to split
2) the command: circleci tests split --split-by=timings
3) containers to run the tests
4) historical data to intelligently decide how to split tests

To collect the list of tests to split, I simply pulled out all of the Java test files with this command: `circleci tests glob "src/test/**/**.java"`. 
Then I used `sed` and `tr` to translate this newline-separated list of test files into a comma-separated list of test classes. 

Adding `store_test_results` enables CircleCI to access the historical timing data for previous executions of these tests, so the platform knows how to split tests to achieve the fastest overall runtime. 

### Storing code coverage artifacts
```yaml
version: 2.0

jobs:
  test:
    docker:
      - image: circleci/openjdk:stretch
    steps:
      - checkout
      - run: ./mvnw test verify
      - store_artifacts:
          path: target/site/jacoco/index.html

workflows:
  version: 2

  test-with-store-artifacts:
    jobs:
      - test
```
The Maven test runner with the JaCoCo plugin generates a code coverage report during the build. To save that report as a build artifact, use the `store_artifacts` step.

### All together
```yaml
version: 2.0

jobs:
  test:
    parallelism: 2
    docker:
      - image: circleci/openjdk:stretch
    steps:
      - checkout
      - restore_cache:
          keys:
            - v1-dependencies-{{ checksum "pom.xml" }}
            - v1-dependencies-
      - attach_workspace:
          at: ./target
      - run: |
            ./mvnw \
            -Dtest=$(for file in $(circleci tests glob "src/test/**/**.java" \
            | circleci tests split --split-by=timings); \
            do basename $file \
            | sed -e "s/.java/,/"; \
            done | tr -d '\r\n') \
            -e test verify
      - store_test_results:
          path: target/surefire-reports
      - store_artifacts:
          path: target/site/jacoco/index.html

  build:
    docker:
      - image: circleci/openjdk:stretch
    steps:
      - checkout
      - restore_cache:
          keys:
            - v1-dependencies-{{ checksum "pom.xml" }}
            - v1-dependencies-
      - run: ./mvnw -Dmaven.test.skip=true package
      - save_cache:
          paths:
            - ~/.m2
          key: v1-dependencies-{{ checksum "pom.xml" }}
      - persist_to_workspace:
         root: ./
         paths:
           - target/

workflows:
  version: 2

  build-then-test:
    jobs:
      - build
      - test:
          requires:
            - build
```
All of these features can be used in a single config file to optimize your build. 


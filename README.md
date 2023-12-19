# Sample Cassandra

[![CI with Gradle](https://github.com/sombriks/sample-cassandra/actions/workflows/gradle.yml/badge.svg)](https://github.com/sombriks/sample-cassandra/actions/workflows/gradle.yml)

Sampling minimal configuration to work with Apache Cassandra

## Requirements

- java 17
- gradle 8.5
- docker 20 or newer
- a modern ide (intellij ultimate)

## How to run

First spin up a cassandra server with docker compose:

```bash
docker compose -f src/infrastructure/docker-compose.yml up
```

Then start the project either using the ide or using gradle wrapper

```bash
./gradlew bootRun
```

Note: if this error occurs:

```bash 
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'cassandraSession' defined in class path resource [org/springframework/boot/autoconfigure/cassandra/CassandraAutoConfiguration.class]: Failed to instantiate [com.datastax.oss.driver.api.core.CqlSession]: Factory method 'cassandraSession' threw exception with message: Invalid keyspace spring_cassandra
```

You will need to manually create the cassandra keyspace. there is a [test script](src/test/resources/initial.cql)
that you can use to prepare cassandra database.

## Testing

There are a few test samples using testcontainers, so you don't need to manually
create and run cassandra:

```bash
./gradlew test
```

It also allows CI to run with no external dependencies, which is the correct way
to do CI runs. See [gradle.yml](.github/workflows/gradle.yml) for details.

## AWS Keyspaces

One thing is regular cassandra. Another beast is [AWS Keyspaces](https://console.aws.amazon.com/keyspaces).

Using the [sample project](https://github.com/aws-samples/amazon-keyspaces-examples/tree/main/java/datastax-v4/spring)
as reference, [this configuration](src/main/resources/application-aws.yml) was
made for better reuse in future.

In order to proper get this working, you must provide the following environment
variables:

```bash
AWS_ACCESS_KEY_ID=<aws account id>
AWS_SECRET_ACCESS_KEY=<aws account key>
CASSANDRA_KEYSPACE_NAME=<cassandra keyspace>
CASSANDRA_USERNAME=<service account user>
CASSANDRA_PASSWORD=<service account password>
```

Then you can configure the IDE to run with the aws profile. Or try this command
line:

```bash
./gradlew bootRun --args="--spring.profiles.active=aws"
```

## Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.0/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.0/gradle-plugin/reference/html/#build-image)
* [Spring Boot Testcontainers support](https://docs.spring.io/spring-boot/docs/3.2.0/reference/html/features.html#features.testing.testcontainers)
* [Testcontainers Cassandra Module Reference Guide](https://java.testcontainers.org/modules/databases/cassandra/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.0/reference/htmlsingle/index.html#web)
* [Spring Data for Apache Cassandra](https://docs.spring.io/spring-boot/docs/3.2.0/reference/htmlsingle/index.html#data.nosql.cassandra)
* [Testcontainers](https://java.testcontainers.org/)

## Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Spring Data for Apache Cassandra](https://spring.io/guides/gs/accessing-data-cassandra/)

## Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

## Testcontainers support

This project
uses [Testcontainers at development time](https://docs.spring.io/spring-boot/docs/3.2.0/reference/html/features.html#features.testing.testcontainers.at-development-time).

Testcontainers has been configured to use the following Docker images:

* [`cassandra:latest`](https://hub.docker.com/_/cassandra)

Please review the tags of the used images and set them to the same as you're running in production.


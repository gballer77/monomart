# Monomart

Monomart is a sample application written with Spring Boot that is intended to serve as a medium in which to teach and exercise the decomposition of a monolith into separate services.   

## Components

The makeup of Monomart is a Spring Boot web application, with a react frontend being served out from the embedded Tomcat.  This means that when the application is run, the UI is accessible from your browser on https://localhost:8080 (by default).

## To run the application

Ensure that you are running the application with Java 17.  Execute `./gradlew bootrun` from the root of the project.  This will:
* Build the frontend
* Copy frontend to backend static folder
* Build the backend
* Run the application

## Workshop Scenario

You have been called into Monomart inc to help their software team modernize and re-platform the application onto a container-based platform, Kubernetes for example.  Although the application is currently limited in scope, they anticipate a heavy influx of users in the coming months, potentially even reaching millions of purchases a second.   Throughout the modernization effort the application will need to remain online.  Additionally, depending on the duration of the refactor, additional features may need to be added to the Monomart application.

## Workshop Tasks

### Planning 
- [x] Analyze the application
- [ ] Determine decomposed architecture 
  - [ ] Weigh pros/cons of the architecture
  - [ ] Iterate
- [ ] Determine decomposition approach
 
### Execution
- [ ] Refactor
- [ ] Regression Test
- [ ] Repeat

## Things to think about

### Testing

The current testing on Monomart is lackluster at best.  Some work, some don't.  Many haven't been updated in many commits.  A special emphasis should be put on tests of various types to ensure that this refactor, and future refactors and feature updates do not lead to regressions. For the sake of the workshop, create a few tests that cover the span of the 

### Refactoring approach

We cant throw away our agile and XP tenants when doing a modernization, if anything, its more important that we uphold them during a modernization.  This means that agile, TDD, pairing, frequent releases, should all be core to what we do during a modernization.  This means that we should steer clear of a "big bang" refactor, making all the updates in a vacuum and releasing the software at some far future date.  Rather, we should devise a plan that allows us to iteratively refactor,  ensuring to deploy our application frequently to staging then production, during the refactor.

### CI/CD

When doing this for real, getting a pipeline running for the application(s) is just as important as tests in order to shorten the feedback and deployment loop.  For the purposes of this workshop, we will not build out about the CI/CD pipeline, but we should be thinking about it as we progress.

### Create a Config Server

#### 1. Dependencies
```groovy
implementation 'org.springframework.cloud:spring-cloud-config-server'
```

#### 4. Code
Declare `@EnableConfigServer`.

```java
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
```

#### Define Configuration Properties

1. Define label that specifies branch to use from git repository
2. Specify uri to point config server at git repository

```yaml
spring:
  cloud:
    config:
      server:
        git:
          default-label: main
          uri: file:/${user.home}/config-repo
          skipSslValidation: true
```

#### Create Config Server Git Repository

```
mkdir config-repo
git init
echo info.foo: bar > application.properties
git add .
git commit -m"<message>"
```

#### Test it out

View [/application/default](http://localhost:8080/application/default)

```json
{
  "name": "application",
  "profiles": [
    "default"
  ],
  "label": null,
  "version": "9608b939c2a3694722fb37546d4e00e8e2df2fa2",
  "state": null,
  "propertySources": [
    {
      "name": "file:/<path>/config-repo/application.properties",
      "source": {
        "info.foo": "bar"
      }
    }
  ]
}
```

### Create a Config Client

#### 1. Dependencies
```groovy
	implementation 'org.springframework.boot:spring-boot-starter-actuator'
	implementation 'org.springframework.cloud:spring-cloud-starter-config'
```

#### Define Configuration Properties

```yaml
spring:
  config:
    import: configserver:http://localhost:8080

management:
  endpoints:
    enabled-by-default: true
    web:
      exposure:
        include: '*'
```

#### Test it out
View `/actuator/env`

```json
{
  "name": "configserver:file:/<path>/config-repo/application.properties",
  "properties": {
    "info.foo": {
      "value": "******",
      "origin": "Config Server file:/<path>/config-repo/application.properties:1:11"
    }
  }
}
```
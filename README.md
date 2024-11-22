# TradeWars

A web-based homage to [TradeWars](https://en.wikipedia.org/wiki/Trade_Wars), running on Heroku as a
Java-Redis-HTMX application.

Try it out online: https://tradewars-c2d3d73aea9e.herokuapp.com

## Requirements

- [JDK v17](https://openjdk.org/projects/jdk/17/)
- [Git](https://www.atlassian.com/git/tutorials/install-git)
- [Apache Maven v3.9](https://maven.apache.org/install.html)
- [Redis](https://redis.io/try-free/)
- [Optional] [Heroku CLI client](https://devcenter.heroku.com/articles/heroku-cli)

## Installation

1. Download the [source code](https://github.com/jasonhendriks/tradewars) to your workstation
    ```
    git clone https://github.com/jasonhendriks/tradewars
    ```
2. Populate the `REDIS_URL` environment variable
    ```
    export REDIS_URL=
    ```

3. [Optional] If you installed the Heroku CLI, connect your local repository to Heroku:
    ```
    heroku git:remote -a tradewars
    ```

## Running Locally

### Build the application and run with the Maven Spring Boot plug-in

```
$ mvn install
$ mvn spring-boot:run
```

Then access the application in your web browser: http://localhost:8080

### Build the application and run with the Heroku CLI

```
$ mvn install
$ heroku local
```

Then access the application in your web browser: http://localhost:5001

## Deployment to Production

### Continuous Integration

After committing and pushing any changes, GitHub will run the tests. If successful, Heroku will automatically retrieve
the changes, build and deploy.

### Manual Deploy with Heroku CLI

Push to the Heroku GIT remote to manually trigger a deployment:

```
git push heroku
```

## Production Support

### Debugging

View the production logs:

```
heroku logs --tail
```

## Development Resources

General

- https://www.baeldung.com/maven-webjars
- https://www.baeldung.com/spring-session
- https://htmx.org/examples/value-select/
- https://www.baeldung.com/thymeleaf-select-option
- https://www.thymeleaf.org/doc/tutorials/3.1/usingthymeleaf.html#including-template-fragments
- https://testcontainers.com/guides/getting-started-with-testcontainers-for-java/
- https://www.reddit.com/r/htmx/comments/196gwsa/you_can_do_so_much_with_hxboost_hxindicator_and/

Heroku

- https://devcenter.heroku.com/articles/java-session-handling-on-heroku
- https://devcenter.heroku.com/articles/heroku-redis

Redis

- https://www.baeldung.com/spring-data-redis-tutorial
- [Password is not used from spring.data.redis.url property without username #35850](https://github.com/spring-projects/spring-boot/issues/35850)
- [Embedded Redis Server with Spring Boot Test](https://www.baeldung.com/spring-embedded-redis)
- [EmbeddedRedis Support For Spring Framework](http://www.kenansevindik.com/en/embeddedredis-support-for-spring-framework/)
- [Integration Testing for Redis using Testcontainers in Spring Boot](https://medium.com/@gourav2824/integration-testing-for-redis-using-testcontainers-in-spring-boot-3ea2ceb5cad7)

Gaming

- [Trade Wars @ Wikipedia](https://en.wikipedia.org/wiki/Trade_Wars)
- http://docs.classictw.com/index.php/Big_Bang
- http://wiki.classictw.com/index.php/Gypsy%27s_Big_Dummy%27s_Guide_to_TradeWars_Text
- https://www.reddit.com/r/Tradewars/comments/ztyejd/universe_generation/
- https://jgrapht.org/guide/UserOverview
- https://jgrapht.org/javadoc/org.jgrapht.core/org/jgrapht/generate/WattsStrogatzGraphGenerator.html

Authentication

- [Passkeys: Goodbye Passwords, Hello Security! (SpringOne) - YouTube](https://www.youtube.com/watch?v=nsofIV11xXY)
- https://webauthn.io
- https://passkeys.dev

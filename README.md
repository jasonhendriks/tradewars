# TradeWars

A modern web-based homage to [TradeWars](https://en.wikipedia.org/wiki/Trade_Wars), one of the most popular turn-based,
role-playing,
online, multiplayer games from the BBS era.

Try it out online: https://floating-peak-86981-45f021e67352.herokuapp.com

## Requirements

- [JDK v17](https://openjdk.org/projects/jdk/17/)
- [Git](https://www.atlassian.com/git/tutorials/install-git)
- [Apache Maven v3.9](https://maven.apache.org/install.html)
- [Optional] [Heroku CLI client](https://devcenter.heroku.com/articles/heroku-cli)

## Installation

1. Download the [source code](https://github.com/jasonhendriks/tradewars) to your workstation
    ```
    git clone https://github.com/jasonhendriks/tradewars
    ```
2. [Optional] If you installed the Heroku CLI, connect your local repository to Heroku:
    ```
    heroku git:remote -a tradewars
    ```

### Keeping the dependencies up-to-date

```
mvn versions:display-plugin-updates
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

- https://www.baeldung.com/maven-webjars
- https://spring.io/guides/gs/uploading-files/
- https://www.baeldung.com/spring-session
- https://htmx.org/examples/value-select/
- https://www.baeldung.com/thymeleaf-select-option
- https://www.thymeleaf.org/doc/tutorials/3.1/usingthymeleaf.html#including-template-fragments

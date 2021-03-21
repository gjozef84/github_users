# GitHub Users application
Simple REST application which returning data about GitHub users

## How to run

#### From JAR:
- Compile: `mvn clean install`
- Run (from target): `java -jar github_users-1.0.0-SNAPSHOT.jar --spring.datasource.url='<URL_TO_DATABASE>' --spring.datasource.username='<DB_USER_NAME>' --spring.datasource.password='<DB_PASSWORD>'`

#### From Maven runner
- Execute runner: `mvn spring-boot:run "-Dspring-boot.run.arguments=--spring.datasource.url=<URL_TO_DATABASE> --spring.datasource.username=<DB_USER_NAME> --spring.datasource.password=<DB_PASSWORD>"`

(Application defaults to a value in these parameters for the for docker `postgresql:latest` container. I you want to run container to test application use commands `docker-compose pull` and `docker-compose up`):


## How to use application
At http://localhost:8080/github-users/api/users application provides REST endpoints:

 `GET /{login}` - get GitHub user data based on the given login
 ```
    {
      "id": 39644658,
      "login": "gjozef84",
      "name": "Grzegorz Józefowicz",
      "type": "User",
      "avatarUrl": "https://avatars.githubusercontent.com/u/39644658?v=4",
      "createdAt": "2018-05-26T07:51:39",
      "followers": 0,
      "publicRepos": 16,
      "calculations": 0       //calculations = 6 / followers * (2 + publicRepos)
    }
```
 
 `GET /{login}/statistics` - return requests statistics about GitHub user 
```
    {
      "login": "gjozef84",
      "requestCount": 2,
      "createdAt": "2021-03-23T16:32:42.515",
      "updatedAt": "2021-03-23T16:34:50.59"
    }
```
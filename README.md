# device-engine :computer:

![Alt text](design.png?raw=true "Title")

## Create docker mysql container
```docker run -p 3306:3306 --name=mysql57 -e MYSQL_ROOT_PASSWORD=pwd -d mysql:latest```

## Running the app
```mvn spring-boot:run -Dspring-boot.run.profiles=<profile>```

## Spotless apply
```mvn spotless:apply```

## Security
```
curl <client-id>:<client-secret>@<url>/oauth/token -dgrant_type=client_credentials -dscope=any
curl <url>/oauth/check_token/?token=<TOKEN>
```

## Built With
* [Maven](https://maven.apache.org/)
* [Jenkins](http://jenkins.kuebikoit.com:8080/)

## Authors
* **Karya Chhetri** - *Initial setup*

# device-engine :computer:

![Alt text](design.png?raw=true "Title")

## Running the app
```mvn spring-boot:run -Dspring-boot.run.profiles=<profile>```

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

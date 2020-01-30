package com.kuebikoit.deviceengine;

import java.time.ZoneOffset;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
public class DeviceEngineApplication {

  public static void main(String[] args) {
    SpringApplication.run(DeviceEngineApplication.class, args);
  }

  @PostConstruct
  public void init() {
    TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));
  }

}

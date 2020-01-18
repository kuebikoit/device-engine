package com.kuebikoit.deviceengine.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ExecutorServiceConfig {

  public static final String BASE_EXECUTOR_SERVICE = "de-exec-base";
  public static final String MAX_EXECUTOR_SERVICE = "de-exec-max";

  @Bean(name = BASE_EXECUTOR_SERVICE)
  @Primary
  public ExecutorService executorService(@Value("${executor-service.thread.base}") int num) {
    return Executors.newFixedThreadPool(3, threadFactory(BASE_EXECUTOR_SERVICE));
  }

  @Bean(name = MAX_EXECUTOR_SERVICE)
  public ExecutorService executorServiceMax(@Value("${executor-service.thread.max}") int num) {
    return Executors.newFixedThreadPool(5, threadFactory(MAX_EXECUTOR_SERVICE));
  }

  private ThreadFactory threadFactory(String name) {
    return new ThreadFactoryBuilder().setNameFormat(name + "-%d").build();
  }
}

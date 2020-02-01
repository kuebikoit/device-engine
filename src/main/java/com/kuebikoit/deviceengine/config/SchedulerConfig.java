package com.kuebikoit.deviceengine.config;

import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SchedulerConfig {

  private final DataSource dataSource;

  @Autowired
  public SchedulerConfig(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Bean
  public SchedulerFactoryBeanCustomizer schedulerFactoryBeanCustomizer() {
    return bean -> bean.setDataSource(dataSource);
  }
}

package com.kuebikoit.deviceengine.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ExecutorServiceConfig {

    @Bean(name = "loadExService")
    @Primary
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(3);
    }

    @Bean(name = "maxLoadExService")
    public ExecutorService executorServiceMax() {
        return Executors.newFixedThreadPool(5);
    }
}

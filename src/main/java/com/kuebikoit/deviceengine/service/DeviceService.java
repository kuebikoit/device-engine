package com.kuebikoit.deviceengine.service;

import static com.kuebikoit.deviceengine.config.ExecutorServiceConfig.MAX_EXECUTOR_SERVICE;

import com.kuebikoit.deviceengine.controller.model.BatchLoad;
import com.kuebikoit.deviceengine.persistence.repository.DeviceRepository;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeviceService {

  private final DeviceRepository deviceRepository;
  private final ExecutorService maxLoadExecutorService;

  @Autowired
  public DeviceService(
      DeviceRepository deviceRepository,
      @Qualifier(MAX_EXECUTOR_SERVICE) ExecutorService maxLoadExecutorService) {
    this.maxLoadExecutorService = maxLoadExecutorService;
    this.deviceRepository = deviceRepository;
  }

  public void load(BatchLoad batchLoad) {
    batchLoad
        .getDevices()
        .forEach(
            d ->
                CompletableFuture.runAsync(() -> deviceRepository.save(d), maxLoadExecutorService));
  }
}

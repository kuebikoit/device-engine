package com.kuebikoit.deviceengine.controller;

import static com.kuebikoit.deviceengine.config.ExecutorServiceConfig.MAX_EXECUTOR_SERVICE;

import com.kuebikoit.deviceengine.controller.model.BatchLoad;
import com.kuebikoit.deviceengine.exception.DeviceNotFoundException;
import com.kuebikoit.deviceengine.persistence.model.Device;
import com.kuebikoit.deviceengine.persistence.repository.DeviceRepository;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devices")
@Slf4j
public class DeviceController {

  private final DeviceRepository deviceRepository;
  private final ExecutorService maxLoadExecutorService;

  @Autowired
  public DeviceController(
      DeviceRepository deviceRepository,
      @Qualifier(MAX_EXECUTOR_SERVICE) ExecutorService maxLoadExecutorService) {
    this.maxLoadExecutorService = maxLoadExecutorService;
    log.debug("{} constructor invoked by Spring", this.getClass().getName());
    this.deviceRepository = deviceRepository;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void load(@RequestBody @Valid BatchLoad batchLoad) {
    log.info("Post endpoint invoked");

    batchLoad
        .getDevices()
        .forEach(
            d ->
                CompletableFuture.runAsync(() -> deviceRepository.save(d), maxLoadExecutorService));
  }

  @GetMapping
  public List<Device> getDevices() {
    log.info("Get endpoint invoked");

    return (List<Device>) deviceRepository.findAll();
  }

  @GetMapping("/{id}")
  public Device getDevice(@PathVariable Long id) {
    log.info("Get by id endpoint invoked id={}", id);
    String exceptionMessage = String.format("Device not found for id=%s", id);

    return deviceRepository
        .findById(id)
        .orElseThrow(() -> new DeviceNotFoundException(exceptionMessage));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void removeDevice(@PathVariable Long id) {
    log.info("Delete by id endpoint invoked id={}", id);
    String exceptionMessage = String.format("Device not found for id=%s", id);

    Device deviceToRemove =
        deviceRepository
            .findById(id)
            .orElseThrow(() -> new DeviceNotFoundException(exceptionMessage));

    deviceRepository.delete(deviceToRemove);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Device updateDevice(@RequestBody Device device, @PathVariable Long id) {
    log.info("Delete by id endpoint invoked id={}", id);
    String exceptionMessage = String.format("Device not found for id=%s", id);

    Device deviceToUpdate =
        deviceRepository
            .findById(id)
            .orElseThrow(() -> new DeviceNotFoundException(exceptionMessage));

    return deviceRepository.save(deviceToUpdate);
  }
}

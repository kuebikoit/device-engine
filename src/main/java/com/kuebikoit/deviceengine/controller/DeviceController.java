package com.kuebikoit.deviceengine.controller;

import com.kuebikoit.deviceengine.controller.model.BatchLoad;
import com.kuebikoit.deviceengine.exception.DeviceNotFoundException;
import com.kuebikoit.deviceengine.persistence.model.Device;
import com.kuebikoit.deviceengine.persistence.repository.DeviceRepository;
import com.kuebikoit.deviceengine.service.DeviceService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devices")
@Slf4j
public class DeviceController {

  private final DeviceService deviceService;
  private final DeviceRepository deviceRepository;

  public DeviceController(DeviceService deviceService, DeviceRepository deviceRepository) {
    this.deviceService = deviceService;
    this.deviceRepository = deviceRepository;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void load(@RequestBody @Valid BatchLoad batchLoad) {
    log.info("Post endpoint invoked");

    this.deviceService.load(batchLoad);
  }

  @GetMapping
  public List<Device> getDevices(@RequestParam(value = "hostname", required = false) String hostname) {
    log.info("Get endpoint invoked");

    if (!StringUtils.isEmpty(hostname)) {
      Device d = deviceRepository.findByHostname(hostname)
          .orElse(null);

      return List.of(d);
    } else {
      return (List<Device>) deviceRepository.findAll();
    }
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

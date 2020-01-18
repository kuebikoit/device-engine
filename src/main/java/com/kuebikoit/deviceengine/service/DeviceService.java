package com.kuebikoit.deviceengine.service;

import com.kuebikoit.deviceengine.persistence.repository.DeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeviceService {

  private final DeviceRepository deviceRepository;

  @Autowired
  public DeviceService(DeviceRepository deviceRepository) {
    log.debug("********Service constructor invoked by Spring******");
    this.deviceRepository = deviceRepository;
  }
}

package com.kuebikoit.deviceengine.controller;

import com.kuebikoit.deviceengine.persistence.model.Device;
import com.kuebikoit.deviceengine.persistence.repository.DeviceRepository;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
@Slf4j
public class DeviceController {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceController(DeviceRepository deviceRepository) {
        log.debug("{} constructor invoked by Spring", this.getClass().getName());
        this.deviceRepository = deviceRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void load(@RequestBody @Valid Device device) {
        log.info("Post endpoint invoked");

        deviceRepository.save(device);
    }

    @GetMapping
    public List<Device> getDevices() {
        log.info("Get endpoint invoked");

        return (List<Device>) deviceRepository.findAll();
    }

}

package com.kuebikoit.deviceengine.persistence.repository;

import com.kuebikoit.deviceengine.persistence.model.Device;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {
    Optional<Device> findByHostname(String hostname);
}

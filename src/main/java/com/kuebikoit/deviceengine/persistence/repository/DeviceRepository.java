package com.kuebikoit.deviceengine.persistence.repository;

import com.kuebikoit.deviceengine.persistence.model.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {

}

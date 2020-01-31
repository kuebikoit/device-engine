package com.kuebikoit.deviceengine.persistence.repository;

import com.kuebikoit.deviceengine.persistence.model.Device;
import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<Device> findByHostname(String hostname);
}

package com.kuebikoit.deviceengine.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.kuebikoit.deviceengine.controller.model.BatchLoad;
import com.kuebikoit.deviceengine.persistence.model.Device;
import com.kuebikoit.deviceengine.persistence.repository.DeviceRepository;
import com.kuebikoit.deviceengine.service.DeviceService;
import java.util.List;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DeviceControllerTest {

  @Mock private DeviceRepository deviceRepositoryMock;
  @Mock private DeviceService deviceServiceMock;
  @Captor private ArgumentCaptor<BatchLoad> batchLoadArgumentCaptor;
  @InjectMocks private DeviceController controllerUnderTest;

  @Test
  public void verifySaveOnload() {
    // AAA -> Arrange Act Assert -> Given When Then
    Device d = Device.builder().ip("ip").build();
    var batchLoad = new BatchLoad().setDevices(List.of(d));

    controllerUnderTest.load(batchLoad);

    verify(deviceServiceMock, only()).load(batchLoad);
  }

  @Test
  public void verifyDevicePropOnload() {
    // AAA -> Arrange Act Assert -> Given When Then
    String hostname = UUID.randomUUID().toString();
    Device d = Device.builder().hostname(hostname).build();

    controllerUnderTest.load(new BatchLoad().setDevices(List.of(d)));

    verify(deviceServiceMock, only()).load(batchLoadArgumentCaptor.capture());

    assertThat(batchLoadArgumentCaptor.getValue().getDevices().get(0).getHostname())
        .as("verify hostname matches")
        .isEqualTo(hostname);
  }
}

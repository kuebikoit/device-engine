package com.kuebikoit.deviceengine.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.kuebikoit.deviceengine.controller.model.BatchLoad;
import com.kuebikoit.deviceengine.persistence.model.Device;
import com.kuebikoit.deviceengine.persistence.repository.DeviceRepository;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class DeviceControllerTest {

  @Mock private DeviceRepository deviceRepositoryMock;
  @Mock private ExecutorService executorServiceMock;
  @Captor private ArgumentCaptor<Device> deviceArgumentCaptor;
  @InjectMocks private DeviceController controllerUnderTest;

  @Test
  public void verifySaveOnload() {
    // AAA -> Arrange Act Assert -> Given When Then
    Device d = Device.builder().ip("ip").build();

    controllerUnderTest.load(new BatchLoad().setDevices(Arrays.asList(d)));

    verify(deviceRepositoryMock, only()).save(d);
  }

  @Test
  public void verifyDevicePropOnload() {
    // AAA -> Arrange Act Assert -> Given When Then
    String hostname = UUID.randomUUID().toString();
    Device d = Device.builder().hostname(hostname).build();

    controllerUnderTest.load(new BatchLoad().setDevices(Arrays.asList(d)));

    verify(deviceRepositoryMock, only()).save(deviceArgumentCaptor.capture());

    assertThat(deviceArgumentCaptor.getValue().getHostname())
        .as("verify hostname matches")
        .isEqualTo(hostname);
  }
}

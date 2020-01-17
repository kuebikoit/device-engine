package com.kuebikoit.deviceengine.controller;

import com.kuebikoit.deviceengine.controller.model.BatchLoad;
import com.kuebikoit.deviceengine.persistence.model.Device;
import com.kuebikoit.deviceengine.persistence.repository.DeviceRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DeviceControllerTest {

    @Mock
    private DeviceRepository deviceRepositoryMock;
    @Captor
    private ArgumentCaptor<Device> deviceArgumentCaptor;
    @InjectMocks
    private DeviceController controllerUnderTest;

    @Test
    public void verifySaveOnload() {
        //AAA -> Arrange Act Assert -> Given When Then
        Device d = Device.builder().ip("ip").build();

        controllerUnderTest.load(new BatchLoad().setDevices(Arrays.asList(d)));

        verify(deviceRepositoryMock, only()).save(d);
    }

    @Test
    public void verifyDevicePropOnload() {
        //AAA -> Arrange Act Assert -> Given When Then
        String hostname = UUID.randomUUID().toString();
        Device d = Device.builder().hostname(hostname).build();

        controllerUnderTest.load(new BatchLoad().setDevices(Arrays.asList(d)));

        verify(deviceRepositoryMock, only()).save(deviceArgumentCaptor.capture());

        assertThat(deviceArgumentCaptor.getValue().getHostname())
                .as("verify hostname matches")
                .isEqualTo(hostname);
    }
}
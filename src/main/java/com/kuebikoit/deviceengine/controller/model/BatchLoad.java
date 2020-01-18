package com.kuebikoit.deviceengine.controller.model;

import com.kuebikoit.deviceengine.persistence.model.Device;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class BatchLoad {

  private List<Device> devices = new ArrayList<>();
}

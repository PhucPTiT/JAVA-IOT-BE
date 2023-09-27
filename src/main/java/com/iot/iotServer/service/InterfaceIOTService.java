package com.iot.iotServer.service;

import com.iot.iotServer.dto.ControlLogDTO;
import com.iot.iotServer.dto.DataSensorDTO;
import com.iot.iotServer.models.DataSensor;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InterfaceIOTService {
    ControlLogDTO saveControl (ControlLogDTO controlLogDTO);
    List<ControlLogDTO> getAllControlLog();
    ControlLogDTO getFirstControlLog();
    DataSensorDTO getFirstDataSensor();

    Page<DataSensor> getDataPana(int page, int size);
}

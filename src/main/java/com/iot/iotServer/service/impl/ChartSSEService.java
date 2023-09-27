package com.iot.iotServer.service.impl;

import com.iot.iotServer.controller.ChartController;
import com.iot.iotServer.converter.DataSensorConverter;
import com.iot.iotServer.dto.DataSensorDTO;
import com.iot.iotServer.models.DataSensor;
import com.iot.iotServer.repository.DataSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChartSSEService {
    @Autowired
    private DataSensorRepository dataSensorRepository;

    @Autowired
    private DataSensorConverter dataSensorConverter;

    private final List<ChartController> chartControllers = new ArrayList<>();

    public void register(ChartController chartController) {
        chartControllers.add(chartController);
    }
    public List<DataSensorDTO> getListDataDTO() {
        List<DataSensor> dataSensors = dataSensorRepository.GetTenDataSensor();
        List<DataSensorDTO> dataSensorDTOS = new ArrayList<>();
        if(!dataSensors.isEmpty()) {
            for (DataSensor dataSensor : dataSensors) {
                dataSensorDTOS.add(dataSensorConverter.toDTO(dataSensor));
            }
        } else {
        }
        return dataSensorDTOS;
    }

    public void sendToAll() {
        for (ChartController chartController : chartControllers) {
            chartController.sendSSEMessage();
        }
    }


}

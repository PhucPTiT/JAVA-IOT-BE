package com.iot.iotServer.converter;

import com.iot.iotServer.dto.ControlLogDTO;
import com.iot.iotServer.dto.DataSensorDTO;
import com.iot.iotServer.models.ControlLog;
import com.iot.iotServer.models.DataSensor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DataSensorConverter {

    public DataSensor toEntity(DataSensorDTO dto) {
        DataSensor dataSensor = new DataSensor();
        dataSensor.setBrightness(dto.getBrightness());
        dataSensor.setTemp(dto.getTemp());
        dataSensor.setTime(LocalDateTime.now());
        dataSensor.setHumidity(dto.getHumidity());
        return dataSensor;
    }

    public DataSensorDTO toDTO(DataSensor entity) {
        DataSensorDTO dto = new DataSensorDTO();
        dto.setId(entity.getId());
        dto.setBrightness(entity.getBrightness());
        dto.setTemp(entity.getTemp());
        dto.setHumidity(entity.getHumidity());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timeAsString = entity.getTime().format(formatter);
        dto.setTime(timeAsString);
        return dto;
    }

//    public DataSensorDTO toDTO2(DataSensor entity) {
//        DataSensorDTO dto = new DataSensorDTO();
//        dto.setBrightness(entity.getBrightness());
//        dto.setTemp(entity.getTemp());
//        dto.setHumidity(entity.getHumidity());
////        dto.setTime(entity.getTime());
//        return dto;
//    }

}

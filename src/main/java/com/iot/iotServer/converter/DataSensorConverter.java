package com.iot.iotServer.converter;

import com.iot.iotServer.dto.ControlLogDTO;
import com.iot.iotServer.dto.DataSensorDTO;
import com.iot.iotServer.models.ControlLog;
import com.iot.iotServer.models.DataSensor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DataSensorConverter {

    private static final String HANOI_TIMEZONE = "Asia/Ho_Chi_Minh";

    public DataSensor toEntity(DataSensorDTO dto) {
        DataSensor dataSensor = new DataSensor();
        dataSensor.setBrightness(dto.getBrightness());
        dataSensor.setTemp(dto.getTemp());
        dataSensor.setTime(getCurrentTimeInHanoiZone());
        dataSensor.setHumidity(dto.getHumidity());
        dataSensor.setDust(dto.getDust());
        return dataSensor;
    }

    public DataSensor toEntity(DataSensorDTO dto, DataSensor dataSensor) {
        dataSensor.setBrightness(dto.getBrightness());
        dataSensor.setTemp(dto.getTemp());
        dataSensor.setHumidity(dto.getHumidity());
        dataSensor.setDust(dto.getDust());
        return dataSensor;
    }

    public DataSensorDTO toDTO(DataSensor entity) {
        DataSensorDTO dto = new DataSensorDTO();
        dto.setId(entity.getId());
        dto.setBrightness(entity.getBrightness());
        dto.setTemp(entity.getTemp());
        dto.setHumidity(entity.getHumidity());
        dto.setDust(entity.getDust());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime hanoiTime = entity.getTime().atZone(ZoneId.of(HANOI_TIMEZONE));
        String timeAsString = hanoiTime.format(formatter);
        dto.setTime(timeAsString);
        return dto;
    }

    private LocalDateTime getCurrentTimeInHanoiZone() {
        ZoneId hanoiZone = ZoneId.of(HANOI_TIMEZONE);
        ZonedDateTime hanoiTime = ZonedDateTime.now(hanoiZone);
        return hanoiTime.toLocalDateTime();
    }
}

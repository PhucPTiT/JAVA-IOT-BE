package com.iot.iotServer.converter;

import com.iot.iotServer.dto.ControlLogDTO;
import com.iot.iotServer.models.ControlLog;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ControlLogConverter {
    public ControlLog toEntity(ControlLogDTO dto) {
        ControlLog controlLog = new ControlLog();
        controlLog.setFanStatus(dto.getFanStatus());
        controlLog.setLightStatus(dto.getLightStatus());
        controlLog.setTime(LocalDateTime.now());

        return controlLog;
    }

    public ControlLogDTO toDTO(ControlLog entity) {
        ControlLogDTO dto = new ControlLogDTO();
        dto.setId(entity.getId());
        dto.setFanStatus(entity.getFanStatus());
        dto.setLightStatus(entity.getLightStatus());
        dto.setTime(entity.getTime());

        return dto;
    }

}

package com.iot.iotServer.converter;

import com.iot.iotServer.dto.ControlLogDTO;
import com.iot.iotServer.models.ControlLog;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class ControlLogConverter {
    private static final String LOCAL_TIMEZONE = "Asia/Ho_Chi_Minh"; // Thay thế bằng múi giờ của địa phương bạn muốn sử dụng

    public ControlLog toEntity(ControlLogDTO dto) {
        ControlLog controlLog = new ControlLog();
        controlLog.setDevice(dto.getDevice());
        controlLog.setStatus(dto.getStatus());
        controlLog.setTime(getCurrentTimeInLocalZone());

        return controlLog;
    }

    public ControlLogDTO toDTO(ControlLog entity) {
        ControlLogDTO dto = new ControlLogDTO();
        dto.setId(entity.getId());
        dto.setDevice(entity.getDevice());
        dto.setStatus(entity.getStatus());
        dto.setTime(entity.getTime());

        return dto;
    }

    private LocalDateTime getCurrentTimeInLocalZone() {
        ZoneId localZone = ZoneId.of(LOCAL_TIMEZONE);
        ZonedDateTime localTime = ZonedDateTime.now(localZone);
        return localTime.toLocalDateTime();
    }
}

package com.iot.iotServer.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ControlLogDTO {
    private Long id;;
    private String device;
    private Boolean status;
    private LocalDateTime time;
}

package com.iot.iotServer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DataSensorDTO {
    private Long id;

    private String temp;
    private String humidity;
    private String brightness;
    private String dust;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String time;
}

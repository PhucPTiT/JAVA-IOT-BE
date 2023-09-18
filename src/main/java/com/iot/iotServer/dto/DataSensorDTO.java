package com.iot.iotServer.dto;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class DataSensorDTO {
    private Long id;

    private String temp;
    private String humidity;
    private String brightness;

    private LocalDateTime time;
}

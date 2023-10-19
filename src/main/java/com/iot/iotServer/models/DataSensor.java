package com.iot.iotServer.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class DataSensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String temp;
    private String humidity;
    private String brightness;
    private String dust;

    @Column(name = "record_time")
    private LocalDateTime time;
}

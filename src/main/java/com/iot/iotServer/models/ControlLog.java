package com.iot.iotServer.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class ControlLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean lightStatus;
    private Boolean FanStatus;

    @Column(name = "record_time")
    private LocalDateTime time;
}

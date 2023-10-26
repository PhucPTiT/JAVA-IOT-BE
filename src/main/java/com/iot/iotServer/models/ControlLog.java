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

    private String device;
    private Boolean status;


    @Column(name = "record_time")
    private LocalDateTime time;
}

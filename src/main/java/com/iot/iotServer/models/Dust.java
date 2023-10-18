package com.iot.iotServer.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Dust {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long dust;

    @Column(name = "record_time")
    private LocalDateTime time;
}

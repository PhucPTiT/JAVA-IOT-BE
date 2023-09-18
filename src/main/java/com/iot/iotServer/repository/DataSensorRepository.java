package com.iot.iotServer.repository;

import com.iot.iotServer.models.DataSensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataSensorRepository extends JpaRepository<DataSensor, Long> {
}

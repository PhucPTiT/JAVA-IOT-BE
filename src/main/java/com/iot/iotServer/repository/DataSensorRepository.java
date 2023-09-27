package com.iot.iotServer.repository;

import com.iot.iotServer.models.DataSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DataSensorRepository extends JpaRepository<DataSensor, Long> {
    @Query("SELECT d FROM DataSensor d ORDER BY d.time DESC LIMIT 1")
    DataSensor findLastDataSensor();

    @Query("SELECT d FROM DataSensor d ORDER BY d.time DESC LIMIT 1")
    DataSensor findDataSensor();

    @Query("SELECT d FROM DataSensor d ORDER BY d.time DESC LIMIT 10")
    List<DataSensor> GetTenDataSensor();
}

package com.iot.iotServer.repository;

import com.iot.iotServer.models.DataSensor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface DataSensorRepository extends JpaRepository<DataSensor, Long> {
    @Query("SELECT d FROM DataSensor d ORDER BY d.time DESC LIMIT 1")
    DataSensor findLastDataSensor();

    @Query("SELECT d FROM DataSensor d ORDER BY d.time DESC LIMIT 1")
    DataSensor findDataSensor();

    @Query("SELECT d FROM DataSensor d ORDER BY d.time DESC LIMIT 10")
    List<DataSensor> GetTenDataSensor();

    @Query("SELECT d FROM DataSensor d " +
            "WHERE " +
            "(d.brightness LIKE CONCAT('%', :key, '%') " +
            "OR d.temp LIKE CONCAT('%', :key, '%') " +
            "OR d.humidity LIKE CONCAT('%', :key, '%')) " +
            "AND (COALESCE(:sd, '') = '' OR (d.time BETWEEN :sd AND :ed)) " +
            "ORDER BY d.time DESC")
    Page<DataSensor> filterData(@Param("sd") LocalDateTime sd, @Param("ed") LocalDateTime ed,@Param("key") String key, Pageable pageable);
}

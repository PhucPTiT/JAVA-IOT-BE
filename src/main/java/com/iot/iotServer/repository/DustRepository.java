package com.iot.iotServer.repository;

import com.iot.iotServer.models.DataSensor;
import com.iot.iotServer.models.Dust;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DustRepository extends JpaRepository<Dust, Long> {
    @Query("SELECT d FROM Dust d ORDER BY d.time DESC LIMIT 10")
    List<Dust> getTenDust();
}

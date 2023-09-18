package com.iot.iotServer.repository;

import com.iot.iotServer.models.ControlLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ControlLogRepository extends JpaRepository<ControlLog, Long> {
    
}

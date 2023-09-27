package com.iot.iotServer.repository;

import com.iot.iotServer.models.ControlLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ControlLogRepository extends JpaRepository<ControlLog, Long> {
    @Query("SELECT c FROM ControlLog c ORDER BY c.time DESC LIMIT 1")
    ControlLog findLastControlLog();
}

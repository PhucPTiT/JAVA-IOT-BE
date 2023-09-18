package com.iot.iotServer.service;

import com.iot.iotServer.dto.ControlLogDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface InterfaceIOTService {
    ControlLogDTO saveControl (ControlLogDTO controlLogDTO);
    List<ControlLogDTO> getAllControlLog();
}

package com.iot.iotServer.service.impl;

import com.iot.iotServer.converter.ControlLogConverter;
import com.iot.iotServer.dto.ControlLogDTO;
import com.iot.iotServer.models.ControlLog;
import com.iot.iotServer.repository.ControlLogRepository;
import com.iot.iotServer.service.InterfaceIOTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IOTService implements InterfaceIOTService {
    @Autowired
    private ControlLogRepository controlLogRepository;

    @Autowired
    private ControlLogConverter controlLogConverter;
    @Override
    public ControlLogDTO saveControl(ControlLogDTO controlLogDTO) {
        ControlLog controlLogEntity = new ControlLog();
        controlLogEntity = controlLogRepository.save(controlLogConverter.toEntity(controlLogDTO));
        return controlLogConverter.toDTO(controlLogEntity);
    }

    @Override
    public List<ControlLogDTO> getAllControlLog() {
        List<ControlLog> controlLogs = controlLogRepository.findAll();
        List<ControlLogDTO> controlLogDTOs = new ArrayList<>();
        for(ControlLog controlLog : controlLogs) {
            ControlLogDTO controlLogDTO = controlLogConverter.toDTO(controlLog);
            controlLogDTOs.add(controlLogDTO);
        }
        return controlLogDTOs;
    }
}

package com.iot.iotServer.controller;

import com.iot.iotServer.dto.ControlLogDTO;
import com.iot.iotServer.service.impl.IOTService;
import com.iot.iotServer.service.impl.MqttControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/controllog")
@CrossOrigin
public class ControlLogAPI {
    @Autowired
    private IOTService iotService;

    @Autowired
    private MqttControlService mqttControlService;
    @PostMapping
    public ResponseEntity<ControlLogDTO> addControlLog(@RequestBody ControlLogDTO model) {
            try {
                ControlLogDTO controlLogDTO = iotService.saveControl(model);
                mqttControlService.controlLightAndFan(model);
                return ResponseEntity.ok(controlLogDTO);
            } catch (RuntimeException ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
    }

    @GetMapping
    public List<ControlLogDTO> getAllControlLog() {
        return iotService.getAllControlLog();
    }

    @GetMapping("/first")
    public ControlLogDTO getFirstControlLog() {
        return iotService.getFirstControlLog();
    }

}

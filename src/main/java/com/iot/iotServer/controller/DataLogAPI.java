package com.iot.iotServer.controller;

import com.iot.iotServer.dto.ControlLogDTO;
import com.iot.iotServer.dto.DataSensorDTO;
import com.iot.iotServer.models.DataSensor;
import com.iot.iotServer.service.impl.IOTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data")
@CrossOrigin
public class DataLogAPI {

    @Autowired
    private IOTService iotService;
    @GetMapping("/first")
    public DataSensorDTO getFirstDataSensor() {
        return iotService.getFirstDataSensor();
    }

    @GetMapping
    public Page<DataSensor> getPangation(@RequestParam(name = "page", defaultValue = "1") int page,
                                         @RequestParam(name = "size", defaultValue = "30") int size,
                                         @RequestParam(name = "sd", defaultValue = "") String sd,
                                         @RequestParam(name = "ed", defaultValue = "") String ed,
                                         @RequestParam(name = "key", defaultValue = "") String key) {
        return iotService.getDataPana(page, size, sd, ed, key);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DataSensorDTO> updateDataSensor(@RequestBody DataSensorDTO model, @PathVariable("id") long id) {
        try {
            model.setId(id);
            return ResponseEntity.ok(iotService.updateData(model));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCategory(@PathVariable("id") long id) {
        iotService.deleteByDataId(id);
    }

}

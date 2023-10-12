package com.iot.iotServer.controller;

import com.iot.iotServer.dto.DataSensorDTO;
import com.iot.iotServer.models.DataSensor;
import com.iot.iotServer.service.impl.IOTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
                                         @RequestParam(name = "ed", defaultValue = "") String ed) {
        return iotService.getDataPana(page, size, sd, ed);
    }

}

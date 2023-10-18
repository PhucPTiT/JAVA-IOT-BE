package com.iot.iotServer.controller;

import com.iot.iotServer.dto.DustDTO;
import com.iot.iotServer.service.impl.DustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dust")
public class DustController {
    @Autowired
    private DustService dustService;
    @PostMapping
    public ResponseEntity<DustDTO> addDust(@RequestBody DustDTO model) {
        try {
            DustDTO dustDTO = dustService.saveDust(model);
            return ResponseEntity.ok(dustDTO);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    public List<DustDTO> getListDust() {
        return dustService.getDustDTOs();
    }
}

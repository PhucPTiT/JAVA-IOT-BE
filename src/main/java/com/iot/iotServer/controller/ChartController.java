package com.iot.iotServer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iot.iotServer.dto.DataSensorDTO;
import com.iot.iotServer.service.impl.ChartSSEService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/chartSSE")
public class ChartController {



    private final EmitterProcessor<String> emitterProcessor = EmitterProcessor.create();
    @Autowired
    private ChartSSEService chartSSEService;

    @PostConstruct
    public void initialize() {
        chartSSEService.register(this);
    }

    private void sendReconnectMessageToClient() {
        if (emitterProcessor != null) {
            synchronized (emitterProcessor) {
                String reconnectMessage = "RECONNECT";
                emitterProcessor.onNext(reconnectMessage);
            }
        }
    }
    public void sendSSEMessage() {
        if (emitterProcessor != null) {
            synchronized (emitterProcessor) {
                if (!emitterProcessor.isCancelled()) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        String jsonData = objectMapper.writeValueAsString(chartSSEService.getListDataDTO());
                        emitterProcessor.onNext(jsonData);
                    } catch (JsonProcessingException e) {
                        emitterProcessor.error(e);
                    }
                } else {
                    sendReconnectMessageToClient();
                }
            }
        }
    }
    @GetMapping(value = "/data", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> connect() {
        return Flux.from(emitterProcessor);
    }

    @GetMapping("/first")
    public List<DataSensorDTO> getFirst() {
        return chartSSEService.getListDataDTO();
    }
}

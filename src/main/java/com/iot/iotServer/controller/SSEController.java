package com.iot.iotServer.controller;

import com.iot.iotServer.service.impl.SSEService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/sse")
public class SSEController {
    private final EmitterProcessor<String> emitterProcessor = EmitterProcessor.create();

    @Autowired
    private SSEService sseService ;

    @PostConstruct
    public void initialize() {
        sseService.register(this);
    }

    private void sendReconnectMessageToClient() {
        if (emitterProcessor != null) {
            synchronized (emitterProcessor) {
                String reconnectMessage = "RECONNECT";
                emitterProcessor.onNext(reconnectMessage);
            }
        }
    }

    public void sendSSEMessage(String message) {
        if (emitterProcessor != null) {
            synchronized (emitterProcessor) {
                if (!emitterProcessor.isCancelled()) {
                    emitterProcessor.onNext(message);
                } else {
                    sendReconnectMessageToClient();
                }
            }
        }
    }

    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> connect() {
        return Flux.from(emitterProcessor);
    }
}

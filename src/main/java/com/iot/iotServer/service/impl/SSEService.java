package com.iot.iotServer.service.impl;

import com.iot.iotServer.controller.SSEController;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SSEService {
    private final List<SSEController> sseControllers = new ArrayList<>();

    public void register(SSEController sseController) {
        sseControllers.add(sseController);
    }

    public void sendToAll(String data) {
        for (SSEController controller : sseControllers) {
            controller.sendSSEMessage(data);
        }
    }
}

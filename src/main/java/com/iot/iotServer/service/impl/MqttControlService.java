package com.iot.iotServer.service.impl;
import com.iot.iotServer.config.MqttGateway;
import com.iot.iotServer.dto.ControlLogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MqttControlService {
//    public void controlLightAndFan(boolean lightStatus, boolean fanStatus) {
//        String topic = "control";
//        String payload = String.format("Light: %s, Fan: %s", lightStatus, fanStatus);
//        try {
//            ProcessBuilder processBuilder = new ProcessBuilder(
//                    "mosquitto_pub",
//                    "-t", topic,
//                    "-m", payload,
//                    "-u", "admin",
//                    "-P", "12345678",
//                    "-h", "localhost",
//                    "-p", "1883"
//            );
//            Process process = processBuilder.start();
//            process.waitFor();
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
    @Autowired
    private MqttGateway mqttGateway;

    public void controlLightAndFan(ControlLogDTO model) {
        String topic = "control";
        try {
            mqttGateway.senToMqtt(model.getDevice() + " " + model.getStatus().toString() , topic);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

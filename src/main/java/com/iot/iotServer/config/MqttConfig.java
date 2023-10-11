package com.iot.iotServer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iot.iotServer.converter.DataSensorConverter;
import com.iot.iotServer.dto.DataSensorDTO;
import com.iot.iotServer.repository.DataSensorRepository;
import com.iot.iotServer.service.impl.ChartSSEService;
import com.iot.iotServer.service.impl.SSEService;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import java.io.IOException;

@Configuration
public class MqttConfig {
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();

//        options.setServerURIs(new String[] {"tcp://localhost:1883"});
//        options.setUserName("admin");
//        String pass = "12345678";
//        options.setPassword(pass.toCharArray());

        options.setServerURIs(new String[] {"ssl://e6fb6f09c32b4449a0aa0911e7565c47.s1.eu.hivemq.cloud:8883"});
        options.setUserName("admin");
        String pass = "12345678";
        options.setPassword(pass.toCharArray());
        options.setCleanSession(true);

        factory.setConnectionOptions(options);
        return factory;
    }

    @Bean
    public MessageChannel mqttInputChannel()  {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("serverIn", mqttClientFactory(), "#");
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(2);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }
    @Autowired
    private SSEService sseService;

    @Autowired
    private DataSensorRepository dataSensorRepository;

    @Autowired
    private DataSensorConverter dataSensorConverter;

    @Autowired
    private ChartSSEService chartSSEService;

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();
                if(topic.equals("data")) {
                    String payload = message.getPayload().toString();
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        DataSensorDTO dataSensorDTO = objectMapper.readValue(payload, DataSensorDTO.class);
                        dataSensorRepository.save(dataSensorConverter.toEntity(dataSensorDTO));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    sseService.sendToAll(payload);
                    chartSSEService.sendToAll();
                    System.out.println(message.getPayload());
                }
            }
        };
    }
    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("serverOut", mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("#");
        messageHandler.setDefaultRetained(false);
        return messageHandler;
    }


}

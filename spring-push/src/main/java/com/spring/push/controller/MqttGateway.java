package com.spring.push.controller;

import org.springframework.integration.annotation.Header;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;

/**
 * @Auther: zcx
 * @Date: 2018/10/17 10:24
 * @Description:
 */

@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {
    void sendToMqtt(String data, @Header(MqttHeaders.TOPIC) String topic);

}
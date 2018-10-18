package com.spring.push.controller;

import com.spring.push.Util.MqttPushClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: zcx
 * @Date: 2018/10/17 10:24
 * @Description:
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private MqttPushClient mqttPushClient;

    @RequestMapping("/sendMqtt")
    public String sendMqtt(String  sendData){
        String kdTopic = "topic1";
        System.out.println("publish---------");
        mqttPushClient.publish(0, false, kdTopic, "15345715326");
        System.out.println("subscribe---------");
        mqttPushClient.subscribe(kdTopic);
        return "OK";
    }
}

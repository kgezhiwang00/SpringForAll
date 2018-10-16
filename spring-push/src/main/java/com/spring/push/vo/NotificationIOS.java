package com.spring.push.vo;

import lombok.Data;

@Data
public class NotificationIOS {
    private String title;
    private String alert;
    private String badge;
    private String sound;
    private String contentAvailable;


}

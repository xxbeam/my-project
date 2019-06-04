package com.xxbeam.listen;

import org.springframework.stereotype.Component;

@Component
public class RabbitMqListener {

    public void process(String msg) {
        System.out.println("Receiver  : " + msg);
    }
}

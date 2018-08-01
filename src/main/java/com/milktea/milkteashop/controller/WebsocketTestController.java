package com.milktea.milkteashop.controller;

import com.milktea.milkteashop.websocket.WebsocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketHandler;

import java.io.IOException;

@RestController
public class WebsocketTestController {
    @Autowired
    WebsocketHandler websocketHandler;
    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketTestController.class);
    @RequestMapping("/sendsocket")
    public void sendMessage(){
        LOGGER.info("接收到请求");
        try {
            websocketHandler.sendMessage("30","新消息");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

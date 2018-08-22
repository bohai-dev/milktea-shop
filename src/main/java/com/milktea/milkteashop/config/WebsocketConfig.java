package com.milktea.milkteashop.config;

import com.milktea.milkteashop.websocket.WebsocketHandler;

import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer, SchedulingConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //设置路径和允许跨域访问
        registry.addHandler(myHandler(), "/shop/websocket").setAllowedOrigins("*");
                //.addInterceptors(new HttpSessionHandshakeInterceptor());
    }

    @Bean()
    public WebsocketHandler myHandler() {
        return new WebsocketHandler();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar arg0) {
        arg0.setTaskScheduler(new ConcurrentTaskScheduler(
                Executors.newSingleThreadScheduledExecutor()));
        
    }

}

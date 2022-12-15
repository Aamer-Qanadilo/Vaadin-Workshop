package com.example.application;

import org.springframework.web.socket.client.WebSocketClient;

import javax.websocket.ClientEndpoint;

@ClientEndpoint

public class EndPoint {
    WebSocketClient webClient;
    public EndPoint(){

    }
}

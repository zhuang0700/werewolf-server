//package com.telan.werewolf.websocket;
//
///**
// * Created by weiwenliang on 17/9/16.
// */
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
//
//import java.util.Map;
//
//
//public class HandShakeInterceptor extends HttpSessionHandshakeInterceptor {
//
//    @Override
//    public boolean beforeHandshake(ServerHttpRequest request,
//                                   ServerHttpResponse response, WebSocketHandler wsHandler,
//                                   Map<String, Object> attributes) throws Exception {
//        System.out.println("Before Handshake");
//        return super.beforeHandshake(request, response, wsHandler, attributes);
//    }
//
//    @Override
//    public void afterHandshake(ServerHttpRequest request,
//                               ServerHttpResponse response, WebSocketHandler wsHandler,
//                               Exception ex) {
//        System.out.println("After Handshake");
//        super.afterHandshake(request, response, wsHandler, ex);
//    }
//
//}
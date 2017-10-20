//package com.telan.werewolf.websocket;
//
///**
// * Created by weiwenliang on 17/9/16.
// */
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//public class WebsocketEndPoint extends TextWebSocketHandler {
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session,
//                                     TextMessage message) throws Exception {
//        super.handleTextMessage(session, message);
//        System.out.println("received message:" + message.getPayload());
//        TextMessage returnMessage = new TextMessage(message.getPayload()+" received at server");
//        session.sendMessage(returnMessage);
//    }
//}

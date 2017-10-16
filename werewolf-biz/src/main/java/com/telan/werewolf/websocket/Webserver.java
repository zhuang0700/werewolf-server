package com.telan.werewolf.websocket;

/**
 * Created by weiwenliang on 2017/8/2.
 */
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.telan.werewolf.event.EventAcceptor;
import com.telan.werewolf.param.BaseRequestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

//import javax.websocket.*;
//import javax.websocket.server.ServerEndpoint;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
//@ServerEndpoint(value="/websocket",configurator = SpringConfigurator.class)
public class Webserver {
//
//    @Autowired
//    private EventAcceptor eventAcceptor;
//
//    private final static Logger logger = LoggerFactory.getLogger(Webserver.class);
//    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
//    private static int onlineCount = 0;
//
//    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
//    private static CopyOnWriteArraySet<Webserver> webSocketSet = new CopyOnWriteArraySet<Webserver>();
//
//    //与某个客户端的连接会话，需要通过它来给客户端发送数据
//    private Session session;
//
//    private long userId;
//
//    /**
//     * 连接建立成功调用的方法
//     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
//     */
//    @OnOpen
//    public void onOpen(Session session){
//        this.session = session;
//        webSocketSet.add(this);     //加入set中
//        addOnlineCount();           //在线数加1
//        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
//    }
//
//    /**
//     * 连接关闭调用的方法
//     */
//    @OnClose
//    public void onClose(){
//        webSocketSet.remove(this);  //从set中删除
//        subOnlineCount();           //在线数减1
//        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
//    }
//
//    /**
//     * 收到客户端消息后调用的方法
//     * @param message 客户端发送过来的消息
//     * @param session 可选的参数
//     */
//    @OnMessage
//    public void onMessage(String message, Session session) {
//        System.out.println("来自客户端的消息:" + message);
////        eventAcceptor.doAccept(message);
////        try {
////            JSONObject jsonObj = (JSONObject) JSON.parse(message);
////            BaseRequestData baseMsg = com.alibaba.fastjson.JSONObject.toJavaObject(jsonObj, BaseRequestData.class);
////        } catch (Exception e) {
////            logger.error("websocket onMessage error. unknown message:", message, e);
////            System.out.println("websocket onMessage error. unknown message:" + message);
////        }
////        //群发消息
////        for(Webserver item: webSocketSet){
////            try {
////                item.sendMessage(message);
////            } catch (IOException e) {
////                e.printStackTrace();
////                continue;
////            }
////        }
//    }
//
//    /**
//     * 发生错误时调用
//     * @param session
//     * @param error
//     */
//    @OnError
//    public void onError(Session session, Throwable error){
//        System.out.println("发生错误");
//        System.out.println(error.getLocalizedMessage());
////        error.printStackTrace();
//    }
//
//    /**
//     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
//     * @param message
//     * @throws IOException
//     */
//    public void sendMessage(String message) throws IOException{
//        this.session.getBasicRemote().sendText(message);
//        //this.session.getAsyncRemote().sendText(message);
//    }
//
//
//    /**
//     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
//     * @param msgs
//     * @throws IOException
//     */
//    public static void sendMessage(Map<Long, String> msgs) throws IOException{
//        try {
//            for(Webserver item: webSocketSet){
//                if(msgs.keySet().contains(item.userId)) {
//                    item.session.getBasicRemote().sendText(msgs.get(item.userId));
//                }
//            }
//        } catch (IOException e) {
//            throw e;
//        }
//
//        //this.session.getAsyncRemote().sendText(message);
//    }
//
//    public static synchronized int getOnlineCount() {
//        return onlineCount;
//    }
//
//    public static synchronized void addOnlineCount() {
//        Webserver.onlineCount++;
//    }
//
//    public static synchronized void subOnlineCount() {
//        Webserver.onlineCount--;
//    }
}
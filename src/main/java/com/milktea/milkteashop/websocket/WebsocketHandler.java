package com.milktea.milkteashop.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class WebsocketHandler extends TextWebSocketHandler {


    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static AtomicInteger  onlineCount=new AtomicInteger(0);

    //concurrent包的线程安全Set
    private static CopyOnWriteArrayList<Map<String,Object>> webSocketList = new CopyOnWriteArrayList<Map<String,Object>>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
   //  private WebSocketSession session=null;
    //店铺id
    //private String shopId=null;
    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        LOGGER.info("socket 连接：有新店铺上线");
        int onlineSum=onlineCount.addAndGet(1);          //在线数加1
        LOGGER.info("当前在线店铺数量："+onlineSum);
        //LOGGER.info(session.toString());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        //接收店铺信息：这里只模拟发送id
        LOGGER.info("接收到店铺消息(id)：" + message.getPayload());
        String shopId=message.getPayload();
        /*this.shopId=shopId;
        this.session = session;*/
        Map<String,Object> map=new HashMap<>();
        map.put("shopId",shopId);
        map.put("session",session);
        webSocketList.add(map);     //加入set中



        //session.sendMessage(new TextMessage("我收到你发的消息了"));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        webSocketList.remove(this);  //从set中删除
        int onlineSum=onlineCount.decrementAndGet();//在线数减1
        LOGGER.info("有1连接关闭！当前在线店铺数为：" + onlineSum);

    }
    /**
     * 给指定店铺发送消息
     * @param shopId  用户id
     * @param message 发送数据，可定义为一个类
     * @throws IOException
     */
    public  void sendMessage(String shopId,String message) throws IOException{
        //群发消息
        for(Map<String,Object> item: webSocketList){
            String  saveShopId=(String) item.get("shopId");
            WebSocketSession session=(WebSocketSession) item.get("session");
            if (shopId.equals(saveShopId)){
                session.sendMessage(new TextMessage(message));
            }


        }

    }

}

package com.milktea.milkteashop.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.alibaba.fastjson.JSON;
import com.milktea.milkteashop.service.OrderService;
import com.milktea.milkteashop.vo.OrderNationVo;
import com.milktea.milkteashop.vo.QueryOrdersRequestVo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class WebsocketHandler extends TextWebSocketHandler {


    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static AtomicInteger  onlineCount=new AtomicInteger(0);

    //concurrent包的线程安全List
    private static CopyOnWriteArrayList<Map<String,Object>> webSocketList = new CopyOnWriteArrayList<Map<String,Object>>();

    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketHandler.class);
    
    @Autowired
    private OrderService orderService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        LOGGER.info("socket 连接：有新店铺上线");
        int onlineSum=onlineCount.addAndGet(1);          //在线数加1
        LOGGER.info("当前在线店铺数量："+onlineSum);
        //LOGGER.info(session.toString());
    }

    //接收客户端发送的消息，这里接收店铺的id
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        //接收店铺信息：这里只模拟发送id
        LOGGER.info("接收到店铺消息(id)：" + message.getPayload());
        String shopId=message.getPayload();
        Map<String,Object> map=new HashMap<>();
        map.put("shopId",shopId);
        map.put("session",session);
        webSocketList.add(map);     //加入set中
        
        QueryOrdersRequestVo requestVo = new QueryOrdersRequestVo();
        requestVo.setStoreNo(shopId);
        List<OrderNationVo> list = this.orderService.queryOrder(requestVo);
        if(list != null && list.size() > 0){
            for (OrderNationVo orderNationVo : list) {
                orderNationVo.setMessageType("0");
                this.sendMessage(shopId, JSON.toJSONString(orderNationVo));
            }
        }
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
     * @param shopId  店铺id
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

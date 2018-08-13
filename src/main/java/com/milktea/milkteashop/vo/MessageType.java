package com.milktea.milkteashop.vo;

public class MessageType {
    
    //消息类型 0：历史订单 1：新订单  2：撤单  3：半小时提醒
    private String messageType;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    
}

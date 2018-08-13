package com.milktea.milkteashop.vo;

public class HandleOrderRequestVo {

    /**
     * 订单编号
     */
    private String orderNo;
    
    /**
     * 消息类型  0：下单成功推送   1：撤单推送
     */
    private String messageType;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
    
    
}

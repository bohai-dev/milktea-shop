package com.milktea.milkteashop.vo;

public class ModifyOrderStatusRequestVo {
    
    //订单编号
    private String orderNo;
    
    //订单状态 0:已下单 1：制作完成 2:取货完成 3:外送 4:撤销
    private String orderStatus;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    

}

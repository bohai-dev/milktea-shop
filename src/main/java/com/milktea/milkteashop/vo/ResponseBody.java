package com.milktea.milkteashop.vo;

public class ResponseBody<T> {
    
    private String rspCode = "00000";
    
    private String cnErrorMsg;
    
    private String usErrorMsg;
    
    private T data;

    public String getRspCode() {
        return rspCode;
    }

    public void setRspCode(String rspCode) {
        this.rspCode = rspCode;
    }

    public String getCnErrorMsg() {
        return cnErrorMsg;
    }

    public void setCnErrorMsg(String cnErrorMsg) {
        this.cnErrorMsg = cnErrorMsg;
    }

    public String getUsErrorMsg() {
        return usErrorMsg;
    }

    public void setUsErrorMsg(String usErrorMsg) {
        this.usErrorMsg = usErrorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
}

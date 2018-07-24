package com.milktea.milkteashop.vo;

/**
 * 用户登录返回参数
 * @author caojia
 *
 */
public class UserLoginResponseVo {
    
    /**
     * 用户名
     */
    private String userName;
    
    /**
     * 用户类型 0：超级管理员  1：店铺管理员
     */
    private String userType;
    
    /**
     * 店铺编号  如果是超级管理员，返回默认店铺编号，如果是店铺管理员，返回所在店铺编号
     */
    private String storeNo;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }
    
    

}

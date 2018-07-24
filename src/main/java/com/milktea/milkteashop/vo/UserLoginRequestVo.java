package com.milktea.milkteashop.vo;

/**
 * 用户登录请求参数
 * @author caojia
 *
 */
public class UserLoginRequestVo {
    
    /**
     * 用户名
     */
    private String userName;
    
    /**
     * 密码
     */
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}

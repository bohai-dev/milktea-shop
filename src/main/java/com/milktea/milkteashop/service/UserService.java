package com.milktea.milkteashop.service;

import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.vo.UserLoginRequestVo;
import com.milktea.milkteashop.vo.UserLoginResponseVo;

public interface UserService {
    
    /**
     * 校验用户接口
     * @param requestVo
     * @return
     */
    public UserLoginResponseVo checkUser(UserLoginRequestVo requestVo) throws MilkTeaException;
    
    
}

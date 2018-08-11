package com.milktea.milkteashop.service;

import com.milktea.milkteashop.domain.TeaUserInfo;
import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.vo.PageRequestVo;
import com.milktea.milkteashop.vo.PageResponseVo;
import com.milktea.milkteashop.vo.UserLoginRequestVo;
import com.milktea.milkteashop.vo.UserLoginResponseVo;

public interface UserService {
    
    /**
     * 校验用户接口
     * @param requestVo
     * @return
     */
    public UserLoginResponseVo checkUser(UserLoginRequestVo requestVo) throws MilkTeaException;
    
    /**
     * 查询用户信息
     * @param requestVo
     * @return
     * @throws MilkTeaException
     */
    public PageResponseVo<TeaUserInfo> queryUsersPage(PageRequestVo<TeaUserInfo> requestVo) throws MilkTeaException;
}

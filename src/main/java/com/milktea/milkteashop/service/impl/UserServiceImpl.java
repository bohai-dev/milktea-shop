package com.milktea.milkteashop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milktea.milkteashop.dao.TeaAdminMapper;
import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.UserService;
import com.milktea.milkteashop.vo.UserLoginRequestVo;
import com.milktea.milkteashop.vo.UserLoginResponseVo;

@Service("userService")
public class UserServiceImpl implements UserService{
    
    @Autowired
    private TeaAdminMapper adminMapper;

    @Override
    public UserLoginResponseVo checkUser(UserLoginRequestVo requestVo) throws MilkTeaException {
        // TODO Auto-generated method stub
        return null;
    }

}

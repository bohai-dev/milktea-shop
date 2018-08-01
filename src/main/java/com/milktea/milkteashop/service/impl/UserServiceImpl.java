package com.milktea.milkteashop.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import com.milktea.milkteashop.dao.TeaAdminMapper;
import com.milktea.milkteashop.dao.TeaStoreInfoMapper;
import com.milktea.milkteashop.exception.MilkTeaErrorConstant;
import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.UserService;
import com.milktea.milkteashop.vo.UserLoginRequestVo;
import com.milktea.milkteashop.vo.UserLoginResponseVo;

@Service("userService")
public class UserServiceImpl implements UserService{
    
    @Autowired
    private TeaAdminMapper adminMapper;
    
    @Autowired
    private TeaStoreInfoMapper storeInfoMapper;

    @Override
    public UserLoginResponseVo checkUser(UserLoginRequestVo requestVo) throws MilkTeaException {

        if(requestVo == null){
            throw new MilkTeaException(MilkTeaErrorConstant.PARAMETER_REQUIRED);
        }
        
        if(StringUtils.isBlank(requestVo.getUserName())){
            throw new MilkTeaException(MilkTeaErrorConstant.USER_NAME_REQUIRED);
        }
        
        if(StringUtils.isBlank(requestVo.getPassword())){
            throw new MilkTeaException(MilkTeaErrorConstant.PASSWORD_REQUIRED);
        }
        
        
        
        return null;
    }

}

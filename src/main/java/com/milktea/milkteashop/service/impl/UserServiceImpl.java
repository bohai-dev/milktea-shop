package com.milktea.milkteashop.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milktea.milkteashop.dao.TeaAdminMapper;
import com.milktea.milkteashop.dao.TeaStoreInfoMapper;
import com.milktea.milkteashop.domain.TeaAdmin;
import com.milktea.milkteashop.domain.TeaStoreInfo;
import com.milktea.milkteashop.exception.MilkTeaErrorConstant;
import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.UserService;
import com.milktea.milkteashop.vo.UserLoginRequestVo;
import com.milktea.milkteashop.vo.UserLoginResponseVo;

@Service("userService")
public class UserServiceImpl implements UserService{
    
    static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Autowired
    private TeaAdminMapper adminMapper;
    
    @Autowired
    private TeaStoreInfoMapper storeInfoMapper;

    @Override
    public UserLoginResponseVo checkUser(UserLoginRequestVo requestVo) throws MilkTeaException {

        UserLoginResponseVo responseVo = new UserLoginResponseVo();
        
        if(requestVo == null){
            throw new MilkTeaException(MilkTeaErrorConstant.PARAMETER_REQUIRED);
        }
        
        if(StringUtils.isBlank(requestVo.getUserName())){
            throw new MilkTeaException(MilkTeaErrorConstant.USER_NAME_REQUIRED);
        }
        
        if(StringUtils.isBlank(requestVo.getPassword())){
            throw new MilkTeaException(MilkTeaErrorConstant.PASSWORD_REQUIRED);
        }
        
        TeaAdmin admin = null;
        try {
            admin = this.adminMapper.selectByUserNameAndPasswd(requestVo);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        if(admin != null){
            responseVo.setUserName(admin.getUserName());
            responseVo.setUserType("0");
            
            //查询默认店铺
            TeaStoreInfo storeInfo = new TeaStoreInfo();
            storeInfo.setIsDefault("1");
            
            List<TeaStoreInfo> list = null;
            try {
                list = this.storeInfoMapper.selectByCondition(storeInfo);
            } catch (Exception e) {
                logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
                throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
            }
            
            if(list != null && list.size() > 0){
                responseVo.setStoreNo(list.get(0).getStoreNo());
            }
            
        }else {
            TeaStoreInfo storeInfo = null;
            try {
                storeInfo = this.storeInfoMapper.selectByUserNameAndPasswd(requestVo);
            } catch (Exception e) {
                logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
                throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
            }
            
            if(storeInfo == null){
                throw new MilkTeaException(MilkTeaErrorConstant.USER_NAME_OR_PASSWORD_WRONG);
            }
            responseVo.setStoreNo(storeInfo.getStoreNo());
            responseVo.setUserName(storeInfo.getStoreUserName());
            responseVo.setUserType("1");
        }
        return responseVo;
    }

}

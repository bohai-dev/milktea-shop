package com.milktea.milkteashop.dao;

import com.milktea.milkteashop.domain.TeaAdmin;
import com.milktea.milkteashop.vo.UserLoginRequestVo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeaAdminMapper {
    int insert(TeaAdmin record);

    int insertSelective(TeaAdmin record);
    
    TeaAdmin selectByUserNameAndPasswd(UserLoginRequestVo requestVo);
}
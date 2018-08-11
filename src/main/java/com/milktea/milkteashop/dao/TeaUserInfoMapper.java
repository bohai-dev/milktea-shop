package com.milktea.milkteashop.dao;

import java.util.List;

import com.milktea.milkteashop.domain.TeaUserInfo;import com.milktea.milkteashop.exception.MilkTeaException;

public interface TeaUserInfoMapper {
    int deleteByPrimaryKey(String userNo);

    int insert(TeaUserInfo record);

    int insertSelective(TeaUserInfo record);

    TeaUserInfo selectByPrimaryKey(String userNo);

    int updateByPrimaryKeySelective(TeaUserInfo record);

    int updateByPrimaryKey(TeaUserInfo record);
    
    List<TeaUserInfo> selectByCondition(TeaUserInfo record);
}
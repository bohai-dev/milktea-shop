package com.milktea.milkteashop.dao;

import com.milktea.milkteashop.domain.TeaUserInfo;

public interface TeaUserInfoMapper {
    int deleteByPrimaryKey(String userNo);

    int insert(TeaUserInfo record);

    int insertSelective(TeaUserInfo record);

    TeaUserInfo selectByPrimaryKey(String userNo);

    int updateByPrimaryKeySelective(TeaUserInfo record);

    int updateByPrimaryKey(TeaUserInfo record);
}
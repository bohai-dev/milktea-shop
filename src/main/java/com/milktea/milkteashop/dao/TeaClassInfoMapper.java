package com.milktea.milkteashop.dao;

import com.milktea.milkteashop.domain.TeaClassInfo;

public interface TeaClassInfoMapper {
    int deleteByPrimaryKey(String classId);

    int insert(TeaClassInfo record);

    int insertSelective(TeaClassInfo record);

    TeaClassInfo selectByPrimaryKey(String classId);

    int updateByPrimaryKeySelective(TeaClassInfo record);

    int updateByPrimaryKey(TeaClassInfo record);
}
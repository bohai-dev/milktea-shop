package com.milktea.milkteashop.dao;

import com.milktea.milkteashop.domain.TeaAttributesInfo;

public interface TeaAttributesInfoMapper {
    int deleteByPrimaryKey(String attrId);

    int insert(TeaAttributesInfo record);

    int insertSelective(TeaAttributesInfo record);

    TeaAttributesInfo selectByPrimaryKey(String attrId);

    int updateByPrimaryKeySelective(TeaAttributesInfo record);

    int updateByPrimaryKey(TeaAttributesInfo record);
}
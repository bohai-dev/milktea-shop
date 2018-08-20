package com.milktea.milkteashop.dao;

import org.apache.ibatis.annotations.Mapper;

import com.milktea.milkteashop.domain.TeaPayInfo;
@Mapper
public interface TeaPayInfoMapper {
    int deleteByPrimaryKey(String payId);

    int insert(TeaPayInfo record);

    int insertSelective(TeaPayInfo record);

    TeaPayInfo selectByPrimaryKey(String payId);

    int updateByPrimaryKeySelective(TeaPayInfo record);

    int updateByPrimaryKey(TeaPayInfo record);
}
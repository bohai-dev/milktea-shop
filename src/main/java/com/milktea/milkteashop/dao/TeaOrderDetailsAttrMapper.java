package com.milktea.milkteashop.dao;

import org.apache.ibatis.annotations.Mapper;

import com.milktea.milkteashop.domain.TeaOrderDetailsAttr;
@Mapper
public interface TeaOrderDetailsAttrMapper {
    int insert(TeaOrderDetailsAttr record);

    int insertSelective(TeaOrderDetailsAttr record);
}
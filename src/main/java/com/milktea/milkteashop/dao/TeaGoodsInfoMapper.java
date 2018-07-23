package com.milktea.milkteashop.dao;

import com.milktea.milkteashop.domain.TeaGoodsInfo;

public interface TeaGoodsInfoMapper {
    int deleteByPrimaryKey(String goodsId);

    int insert(TeaGoodsInfo record);

    int insertSelective(TeaGoodsInfo record);

    TeaGoodsInfo selectByPrimaryKey(String goodsId);

    int updateByPrimaryKeySelective(TeaGoodsInfo record);

    int updateByPrimaryKey(TeaGoodsInfo record);
}
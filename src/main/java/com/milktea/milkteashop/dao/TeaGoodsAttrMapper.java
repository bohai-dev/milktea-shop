package com.milktea.milkteashop.dao;

import com.milktea.milkteashop.domain.TeaGoodsAttr;
import com.milktea.milkteashop.domain.TeaGoodsAttrKey;

public interface TeaGoodsAttrMapper {
    int deleteByPrimaryKey(TeaGoodsAttrKey key);

    int insert(TeaGoodsAttr record);

    int insertSelective(TeaGoodsAttr record);

    TeaGoodsAttr selectByPrimaryKey(TeaGoodsAttrKey key);

    int updateByPrimaryKeySelective(TeaGoodsAttr record);

    int updateByPrimaryKey(TeaGoodsAttr record);
}
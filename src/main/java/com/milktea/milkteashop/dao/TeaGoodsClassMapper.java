package com.milktea.milkteashop.dao;

import com.milktea.milkteashop.domain.TeaGoodsClass;
import com.milktea.milkteashop.domain.TeaGoodsClassKey;

public interface TeaGoodsClassMapper {
    int deleteByPrimaryKey(TeaGoodsClassKey key);

    int insert(TeaGoodsClass record);

    int insertSelective(TeaGoodsClass record);

    TeaGoodsClass selectByPrimaryKey(TeaGoodsClassKey key);

    int updateByPrimaryKeySelective(TeaGoodsClass record);

    int updateByPrimaryKey(TeaGoodsClass record);
}
package com.milktea.milkteashop.dao;

import org.apache.ibatis.annotations.Delete;

import com.milktea.milkteashop.domain.TeaGoodsAttr;
import com.milktea.milkteashop.domain.TeaGoodsAttrKey;

public interface TeaGoodsAttrMapper {
    int deleteByPrimaryKey(TeaGoodsAttrKey key);
    
    @Delete(value="delete from TEA_GOODS_ATTR where GOODS_ID = #{goodsId}")
    int deleteByGoodsId(String goodsId);

    int insert(TeaGoodsAttr record);

    int insertSelective(TeaGoodsAttr record);

    TeaGoodsAttr selectByPrimaryKey(TeaGoodsAttrKey key);

    int updateByPrimaryKeySelective(TeaGoodsAttr record);

    int updateByPrimaryKey(TeaGoodsAttr record);
}
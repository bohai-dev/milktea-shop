package com.milktea.milkteashop.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.milktea.milkteashop.domain.TeaGoodsClass;
import com.milktea.milkteashop.domain.TeaGoodsClassKey;
@Mapper
public interface TeaGoodsClassMapper {
    int deleteByPrimaryKey(TeaGoodsClassKey key);

    int insert(TeaGoodsClass record);

    int insertSelective(TeaGoodsClass record);

    TeaGoodsClass selectByPrimaryKey(TeaGoodsClassKey key);

    int updateByPrimaryKeySelective(TeaGoodsClass record);

    int updateByPrimaryKey(TeaGoodsClass record);
    
    @Delete(value="delete from TEA_GOODS_CLASS where GOODS_ID = #{goodsId}")
    int deleteByGoodsId(String goodsId);
    
}
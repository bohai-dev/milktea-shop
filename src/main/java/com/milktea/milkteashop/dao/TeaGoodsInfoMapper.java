package com.milktea.milkteashop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.milktea.milkteashop.domain.TeaGoodsInfo;

public interface TeaGoodsInfoMapper {
    int deleteByPrimaryKey(String goodsId);
    
    int insert(TeaGoodsInfo record);

    int insertSelective(TeaGoodsInfo record);

    TeaGoodsInfo selectByPrimaryKey(String goodsId);

    int updateByPrimaryKeySelective(TeaGoodsInfo record);

    int updateByPrimaryKey(TeaGoodsInfo record);
    
    @Update(value="update TEA_GOODS_INFO set DELETE_FLAG = '1' where GOODS_ID = #{goodsId}")
    int logicalDeleteByPrimaryKey(String goodsId);
    
    List<TeaGoodsInfo> selectByCondition(TeaGoodsInfo goodsInfo);
    
    //TODO 生成店铺编号
    @Select(value="")
    String generateStoreNo();
}
package com.milktea.milkteashop.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
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
    
    @Select(value="select * from TEA_GOODS_INFO where DELETE_FLAG = '0' and CN_GOODS_NAME = #{goodsName}")
    TeaGoodsInfo selectByCnName(String goodsName);
    
    @Select(value="select * from TEA_GOODS_INFO where DELETE_FLAG = '0' and US_GOODS_NAME = #{goodsName}")
    TeaGoodsInfo selectByUsName(String goodsName);
    
    @Select(value="select TEA_GOODS_ID_SEQ.NEXTVAL from dual")
    String generateGoodsId();
    
    @Update(value="update TEA_GOODS_INFO set GOODS_STOCK = GOODS_STOCK - #{volume} where GOODS_ID = #{goodsId}")
    int updateStockByGoodsId(@Param("goodsId") String goodsId,@Param("volume") BigDecimal volume);
}
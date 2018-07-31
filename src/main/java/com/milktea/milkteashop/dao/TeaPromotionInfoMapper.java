package com.milktea.milkteashop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.milktea.milkteashop.domain.TeaPromotionInfo;

public interface TeaPromotionInfoMapper {
    int deleteByPrimaryKey(String promotionId);

    int insert(TeaPromotionInfo record);

    int insertSelective(TeaPromotionInfo record);

    TeaPromotionInfo selectByPrimaryKey(String promotionId);

    int updateByPrimaryKeySelective(TeaPromotionInfo record);

    int updateByPrimaryKey(TeaPromotionInfo record);
    
    @Update(value="update TEA_PROMOTION_INFO set DELETE_FLAG = '1' where PROMOTION_ID = #{promotionId}")
    int logicalDeleteByPrimaryKey(String promotionId);
    
    @Select(value="select * from TEA_PROMOTION_INFO")
    List<TeaPromotionInfo> selectAll();
}
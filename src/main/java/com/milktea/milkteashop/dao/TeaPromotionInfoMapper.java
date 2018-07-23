package com.milktea.milkteashop.dao;

import com.milktea.milkteashop.domain.TeaPromotionInfo;

public interface TeaPromotionInfoMapper {
    int deleteByPrimaryKey(String promotionId);

    int insert(TeaPromotionInfo record);

    int insertSelective(TeaPromotionInfo record);

    TeaPromotionInfo selectByPrimaryKey(String promotionId);

    int updateByPrimaryKeySelective(TeaPromotionInfo record);

    int updateByPrimaryKey(TeaPromotionInfo record);
}
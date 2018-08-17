package com.milktea.milkteashop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
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
    
    List<TeaPromotionInfo> selectByCondition(TeaPromotionInfo record);
    
    @Select(value="select * from TEA_PROMOTION_INFO where DELETE_FLAG = '0' "
            + "and STORE_NOS like '%'|| #{storeNo} ||'%'"
            + "and sysdate between BEGIN_TIME and END_TIME")
    List<TeaPromotionInfo> selectByStoreNo(String storeNo);
    
    @Select(value="select * from TEA_PROMOTION_INFO where DELETE_FLAG = '0' "
            + "and STORE_NOS like '%'|| #{storeNo} ||'%'"
            + "and PROMOTION_ID = #{promotionId}"
            + "and sysdate between BEGIN_TIME and END_TIME")
    TeaPromotionInfo selectByStoreNoAndId(@Param("storeNo") String storeNo, @Param("promotionId") String promotionId);
    
    @Select(value="select TEA_PROMOTION_ID_SEQ.NEXTVAL from dual")
    String generatePromotionId();
}
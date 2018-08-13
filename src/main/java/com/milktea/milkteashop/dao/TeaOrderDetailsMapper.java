package com.milktea.milkteashop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.milktea.milkteashop.domain.TeaOrderDetails;

public interface TeaOrderDetailsMapper {
    int deleteByPrimaryKey(String orderDetailId);

    int insert(TeaOrderDetails record);

    int insertSelective(TeaOrderDetails record);

    TeaOrderDetails selectByPrimaryKey(String orderDetailId);

    int updateByPrimaryKeySelective(TeaOrderDetails record);

    int updateByPrimaryKey(TeaOrderDetails record);
    
    @Select(value="select * from TEA_ORDER_DETAILS where ORDER_NO = #{orderNo}")
    List<TeaOrderDetails> selectByOrderNo(String orderNo);
}
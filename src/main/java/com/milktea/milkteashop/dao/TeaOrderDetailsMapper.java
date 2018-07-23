package com.milktea.milkteashop.dao;

import com.milktea.milkteashop.domain.TeaOrderDetails;

public interface TeaOrderDetailsMapper {
    int deleteByPrimaryKey(String orderDetailId);

    int insert(TeaOrderDetails record);

    int insertSelective(TeaOrderDetails record);

    TeaOrderDetails selectByPrimaryKey(String orderDetailId);

    int updateByPrimaryKeySelective(TeaOrderDetails record);

    int updateByPrimaryKey(TeaOrderDetails record);
}
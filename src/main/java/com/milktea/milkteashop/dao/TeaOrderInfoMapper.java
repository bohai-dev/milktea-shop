package com.milktea.milkteashop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.milktea.milkteashop.domain.TeaOrderInfo;
import com.milktea.milkteashop.vo.QueryOrdersRequestVo;
@Mapper
public interface TeaOrderInfoMapper {
    int deleteByPrimaryKey(String orderNo);

    int insert(TeaOrderInfo record);

    int insertSelective(TeaOrderInfo record);

    TeaOrderInfo selectByPrimaryKey(String orderNo);

    int updateByPrimaryKeySelective(TeaOrderInfo record);

    int updateByPrimaryKey(TeaOrderInfo record);
    
    List<TeaOrderInfo> selectByCondition(QueryOrdersRequestVo requestVo);
    
    List<TeaOrderInfo> selectNotFinish(QueryOrdersRequestVo requestVo);
    
    List<TeaOrderInfo> selectDueOrder(QueryOrdersRequestVo requestVo);
}
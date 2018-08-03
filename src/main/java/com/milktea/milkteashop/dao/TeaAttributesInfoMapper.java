package com.milktea.milkteashop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.milktea.milkteashop.domain.TeaAttributesInfo;
public interface TeaAttributesInfoMapper {
    int deleteByPrimaryKey(String attrId);

    int insert(TeaAttributesInfo record);

    int insertSelective(TeaAttributesInfo record);

    TeaAttributesInfo selectByPrimaryKey(String attrId);

    int updateByPrimaryKeySelective(TeaAttributesInfo record);

    int updateByPrimaryKey(TeaAttributesInfo record);
    
    @Select(value="select * from TEA_ATTRIBUTES_INFO")
    List<TeaAttributesInfo> selectAll();
    
    List<TeaAttributesInfo> selectByGoodsId(String goodsId);
    
    @Select(value="select TEA_ATTR_ID_SEQ.NEXTVAL from dual")
    String generateAttrId();
}
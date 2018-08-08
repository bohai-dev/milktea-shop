package com.milktea.milkteashop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.milktea.milkteashop.domain.TeaAttributesInfo;
public interface TeaAttributesInfoMapper {
    int deleteByPrimaryKey(String attrId);

    int insert(TeaAttributesInfo record);

    int insertSelective(TeaAttributesInfo record);

    TeaAttributesInfo selectByPrimaryKey(String attrId);

    int updateByPrimaryKeySelective(TeaAttributesInfo record);

    int updateByPrimaryKey(TeaAttributesInfo record);
    
    @Select(value="select * from TEA_ATTRIBUTES_INFO where DELETE_FLAG = '0'")
    List<TeaAttributesInfo> selectAll();
    
    List<TeaAttributesInfo> selectByGoodsId(String goodsId);
    
    @Select(value="select TEA_ATTR_ID_SEQ.NEXTVAL from dual")
    String generateAttrId();
    
    @Select(value="select * from TEA_ATTRIBUTES_INFO where CN_ATTR_NAME = #{attrName} and DELETE_FLAG = '0'")
    TeaAttributesInfo selectByCnAttrName(String attrName);
    
    @Select(value="select * from TEA_ATTRIBUTES_INFO where US_ATTR_NAME = #{attrName} and DELETE_FLAG = '0'")
    TeaAttributesInfo selectByUsAttrName(String attrName);
    
    @Update(value="update TEA_ATTRIBUTES_INFO set DELETE_FLAG = '1' where ATTR_ID = #{attrId}")
    int logicDeleteByPrimaryKey(String attrId);
}
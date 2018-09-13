package com.milktea.milkteashop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.milktea.milkteashop.domain.TeaClassInfo;
@Mapper
public interface TeaClassInfoMapper {
    int deleteByPrimaryKey(String classId);

    int insert(TeaClassInfo record);

    int insertSelective(TeaClassInfo record);

    TeaClassInfo selectByPrimaryKey(String classId);

    int updateByPrimaryKeySelective(TeaClassInfo record);

    int updateByPrimaryKey(TeaClassInfo record);
    
    @Select(value="select * from TEA_CLASS_INFO order by INDEX_NO")
    List<TeaClassInfo> selectAll();
    
    List<TeaClassInfo> selectByCondition(TeaClassInfo classInfo);
    
    List<TeaClassInfo> selectByGoodsId(String goodsId);
    
    @Select(value="select TEA_CLASS_ID_SEQ.NEXTVAL from dual")
    String generateClassId();
    
    @Select(value="select count(1) from TEA_CLASS_INFO where INDEX_NO = #{indexNo}")
    Long countByIndexNo(Short indexNo);
    
    @Select(value="select count(1) from TEA_CLASS_INFO where CN_CLASS_NAME = #{CnName}")
    Long countByCnName(String CnName);

    @Select(value="select count(1) from TEA_CLASS_INFO where US_CLASS_NAME = #{UsName}")
    Long countByUsName(String UsName);
    
    @Select(value="select count(1) from TEA_CLASS_INFO where CN_CLASS_NAME = #{CnName} and CLASS_ID != #{classId}")
    Long countOtherByCnName(@Param("classId") String classId, @Param("CnName") String CnName);

    @Select(value="select count(1) from TEA_CLASS_INFO where US_CLASS_NAME = #{UsName} and CLASS_ID != #{classId}")
    Long countOtherByUsName(@Param("classId") String classId, @Param("UsName") String UsName);
}
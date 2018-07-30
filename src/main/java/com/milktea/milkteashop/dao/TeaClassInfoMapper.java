package com.milktea.milkteashop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.milktea.milkteashop.domain.TeaClassInfo;

public interface TeaClassInfoMapper {
    int deleteByPrimaryKey(String classId);

    int insert(TeaClassInfo record);

    int insertSelective(TeaClassInfo record);

    TeaClassInfo selectByPrimaryKey(String classId);

    int updateByPrimaryKeySelective(TeaClassInfo record);

    int updateByPrimaryKey(TeaClassInfo record);
    
    @Select(value="select * from TEA_CLASS_INFO order by INDEX_NO")
    List<TeaClassInfo> selectAll();
}
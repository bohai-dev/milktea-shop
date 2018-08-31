package com.milktea.milkteashop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.milktea.milkteashop.domain.TeaContact;
@Mapper
public interface TeaContactMapper {
    int deleteByPrimaryKey(String id);

    int insert(TeaContact record);

    int insertSelective(TeaContact record);

    TeaContact selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TeaContact record);

    int updateByPrimaryKey(TeaContact record);
    
    @Select("select * from TEA_CONTACT order by CREATE_TIME desc ")
    List<TeaContact> selectAll();
}
package com.milktea.milkteashop.dao;

import com.milktea.milkteashop.domain.TeaAdmin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeaAdminMapper {
    int insert(TeaAdmin record);

    int insertSelective(TeaAdmin record);
}
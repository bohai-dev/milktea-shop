package com.milktea.milkteashop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.milktea.milkteashop.domain.TeaStoreInfo;
import com.milktea.milkteashop.vo.UserLoginRequestVo;

public interface TeaStoreInfoMapper {
    int deleteByPrimaryKey(String storeNo);

    int insert(TeaStoreInfo record);

    int insertSelective(TeaStoreInfo record);

    TeaStoreInfo selectByPrimaryKey(String storeNo);

    int updateByPrimaryKeySelective(TeaStoreInfo record);

    int updateByPrimaryKey(TeaStoreInfo record);
    
    @Select(value="select * from TEA_STORE_INFO")
    List<TeaStoreInfo> selectAll();
    
    List<TeaStoreInfo> selectByCondition(TeaStoreInfo storeInfo);
    
    TeaStoreInfo selectByUserNameAndPasswd(UserLoginRequestVo requestVo);
}
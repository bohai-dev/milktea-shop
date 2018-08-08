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
    
    @Select(value="select 'D'||lpad(TEA_STORE_NO_SEQ.NEXTVAL,5,'0') from dual")
    String generateStoreNo();
    
    @Select(value="select * from TEA_STORE_INFO where CN_STORE_NAME = #{storeName} and DELETE_FLAG = '0'")
    TeaStoreInfo selectByCnStoreName(String storeName);
    
    @Select(value="select * from TEA_STORE_INFO where US_STORE_NAME = #{storeName} and DELETE_FLAG = '0'")
    TeaStoreInfo selectByUsStoreName(String storeName);
}
package com.milktea.milkteashop.service;

import java.util.List;

import com.milktea.milkteashop.domain.TeaStoreInfo;
import com.milktea.milkteashop.exception.MilkTeaException;

/**
 * 店铺接口
 * @author caojia
 *
 */
public interface StoreService {

    /**
     * 查询所有店铺信息
     * @return
     * @throws MilkTeaException
     */
    public List<TeaStoreInfo> queryStoreInfo() throws MilkTeaException;
    
    /**
     * 根据店铺名查询店铺详情
     * @param storeNo
     * @return
     * @throws MilkTeaException
     */
    public TeaStoreInfo queryStoreInfo(String storeNo) throws MilkTeaException;
    
    /**
     * 添加店铺信息
     * @param storeInfo
     * @throws MilkTeaException
     */
    public void addStoreInfo(TeaStoreInfo storeInfo) throws MilkTeaException;
    
    /**
     * 更新店铺信息
     * @param storeInfo
     * @throws MilkTeaException
     */
    public void modifyStoreInfo(TeaStoreInfo storeInfo) throws MilkTeaException;
    
    /**
     * 删除店铺信息
     * @param storeNo
     * @throws MilkTeaException
     */
    public void removeStore(String storeNo) throws MilkTeaException;
    
    
    
}

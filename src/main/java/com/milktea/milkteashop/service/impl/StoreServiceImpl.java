package com.milktea.milkteashop.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milktea.milkteashop.dao.TeaStoreInfoMapper;
import com.milktea.milkteashop.domain.TeaStoreInfo;
import com.milktea.milkteashop.exception.MilkTeaErrorConstant;
import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.StoreService;

@Service("storeService")
public class StoreServiceImpl implements StoreService {
    
    static Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class);
    
    @Autowired
    private TeaStoreInfoMapper storeInfoMapper;
    
    @Override
    public List<TeaStoreInfo> queryStoreInfo() throws MilkTeaException {
        
        List<TeaStoreInfo> stores = null;
        try {
            stores = this.storeInfoMapper.selectAll();
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        return stores;
    }

    @Override
    public TeaStoreInfo queryStoreInfo(String storeNo) throws MilkTeaException {

        if(storeNo == null){
            throw new MilkTeaException(MilkTeaErrorConstant.STORE_NO_REQUIRED);
        }
        
        TeaStoreInfo storeInfo = null;
        try {
            storeInfo = this.storeInfoMapper.selectByPrimaryKey(storeNo);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        return storeInfo;
    }

    @Override
    public void addStoreInfo(TeaStoreInfo storeInfo) throws MilkTeaException {

        if(storeInfo == null){
            throw new MilkTeaException(MilkTeaErrorConstant.PARAMETER_REQUIRED);
        }
        
        if(StringUtils.isBlank(storeInfo.getStoreStatus())){
            throw new MilkTeaException(MilkTeaErrorConstant.STORE_STATUS_REQUIRED);
        }
        
        if(StringUtils.isBlank(storeInfo.getCnStoreName())){
            throw new MilkTeaException(MilkTeaErrorConstant.CN_STORE_NAME_REQUIRED);
        }
        
        if(StringUtils.isBlank(storeInfo.getUsStoreName())){
            throw new MilkTeaException(MilkTeaErrorConstant.US_STORE_NAME_REQUIRED);
        }
        
        if(StringUtils.isBlank(storeInfo.getStorePasswd())){
            throw new MilkTeaException(MilkTeaErrorConstant.STORE_PASSWORD_REQUIRED);
        }
        
        TeaStoreInfo info = null;
        try {
            info = this.storeInfoMapper.selectByCnStoreName(storeInfo.getCnStoreName());
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        if(info != null){
            logger.warn(MilkTeaErrorConstant.CN_STORE_NAME_EXISTS.getCnErrorMsg());
            throw new MilkTeaException(MilkTeaErrorConstant.CN_STORE_NAME_EXISTS);
        }
        
        try {
            info = this.storeInfoMapper.selectByUsStoreName(storeInfo.getUsStoreName());
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        if(info != null){
            logger.warn(MilkTeaErrorConstant.US_STORE_NAME_EXISTS.getCnErrorMsg());
            throw new MilkTeaException(MilkTeaErrorConstant.US_STORE_NAME_EXISTS);
        }
        
        try {
            storeInfo.setStoreNo(this.storeInfoMapper.generateStoreNo());
            this.storeInfoMapper.insertSelective(storeInfo);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }

    }

    @Override
    public void modifyStoreInfo(TeaStoreInfo storeInfo) throws MilkTeaException {
        
        
        if(storeInfo == null || storeInfo.getStoreNo() == null){
            throw new MilkTeaException(MilkTeaErrorConstant.STORE_NO_REQUIRED);
        }
        
        if(StringUtils.isBlank(storeInfo.getStoreStatus())){
            throw new MilkTeaException(MilkTeaErrorConstant.STORE_STATUS_REQUIRED);
        }
        
        if(StringUtils.isBlank(storeInfo.getCnStoreName())){
            throw new MilkTeaException(MilkTeaErrorConstant.CN_STORE_NAME_REQUIRED);
        }
        
        if(StringUtils.isBlank(storeInfo.getUsStoreName())){
            throw new MilkTeaException(MilkTeaErrorConstant.US_STORE_NAME_REQUIRED);
        }
        
        if(StringUtils.isBlank(storeInfo.getStorePasswd())){
            throw new MilkTeaException(MilkTeaErrorConstant.STORE_PASSWORD_REQUIRED);
        }
        
        try {
            this.storeInfoMapper.updateByPrimaryKey(storeInfo);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        
    }

    @Override
    public void removeStore(String storeNo) throws MilkTeaException {
        
        if(storeNo == null){
            throw new MilkTeaException(MilkTeaErrorConstant.STORE_NO_REQUIRED);
        }
        try {
            this.storeInfoMapper.deleteByPrimaryKey(storeNo);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
    }

}

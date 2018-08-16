package com.milktea.milkteashop.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milktea.milkteashop.dao.TeaAttributesInfoMapper;
import com.milktea.milkteashop.domain.TeaAttributesInfo;
import com.milktea.milkteashop.exception.MilkTeaErrorConstant;
import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.AttributeService;

@Service("attributeService")
public class AttributeServiceImpl implements AttributeService {
    
    static Logger logger = LoggerFactory.getLogger(AttributeServiceImpl.class);
    
    @Autowired
    private TeaAttributesInfoMapper attributesInfoMapper;

    @Override
    public void addAttribute(TeaAttributesInfo attributesInfo) throws MilkTeaException {
        
        if(attributesInfo == null){
            throw new MilkTeaException(MilkTeaErrorConstant.PARAMETER_REQUIRED);
        }
        
        if(StringUtils.isBlank(attributesInfo.getCnAttrName())){
            throw new MilkTeaException(MilkTeaErrorConstant.CN_ATTR_NAME_REQUIRED);
        }
        
        if(StringUtils.isBlank(attributesInfo.getUsAttrName())){
            throw new MilkTeaException(MilkTeaErrorConstant.US_ATTR_NAME_REQUIRED);
        }
        
        if(StringUtils.isBlank(attributesInfo.getAttrType())){
            throw new MilkTeaException(MilkTeaErrorConstant.ATTR_TYPE_REQUIRED);
        }
        
        //属性名称校验
        TeaAttributesInfo info = null;
        try {
            info = this.attributesInfoMapper.selectByCnAttrName(attributesInfo.getCnAttrName());
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        
        if(info != null){
            logger.warn(MilkTeaErrorConstant.CN_ATTR_NAME_EXISTS.getCnErrorMsg());
            throw new MilkTeaException(MilkTeaErrorConstant.CN_ATTR_NAME_EXISTS);
        }
        
        try {
            info = this.attributesInfoMapper.selectByUsAttrName(attributesInfo.getUsAttrName());
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        
        if(info != null){
            logger.warn(MilkTeaErrorConstant.US_ATTR_NAME_EXISTS.getCnErrorMsg());
            throw new MilkTeaException(MilkTeaErrorConstant.US_ATTR_NAME_EXISTS);
        }
        
        
        try {
            attributesInfo.setAttrId(this.attributesInfoMapper.generateAttrId());
            this.attributesInfoMapper.insertSelective(attributesInfo);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }

    }

    @Override
    public void removeAttribute(String attrId) throws MilkTeaException {

        if(StringUtils.isBlank(attrId)){
            throw new MilkTeaException(MilkTeaErrorConstant.ATTR_ID_REQUIRED);
        }
        
        try {
            this.attributesInfoMapper.logicDeleteByPrimaryKey(attrId);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }

    }

    @Override
    public void modifyAttribute(TeaAttributesInfo attributesInfo) throws MilkTeaException {
        
        if(attributesInfo == null){
            throw new MilkTeaException(MilkTeaErrorConstant.PARAMETER_REQUIRED);
        }
        
        if(StringUtils.isBlank(attributesInfo.getAttrId())){
            throw new MilkTeaException(MilkTeaErrorConstant.ATTR_ID_REQUIRED);
        }
        
        if(StringUtils.isBlank(attributesInfo.getCnAttrName())){
            throw new MilkTeaException(MilkTeaErrorConstant.CN_ATTR_NAME_REQUIRED);
        }
        
        if(StringUtils.isBlank(attributesInfo.getUsAttrName())){
            throw new MilkTeaException(MilkTeaErrorConstant.US_ATTR_NAME_REQUIRED);
        }
        
        if(StringUtils.isBlank(attributesInfo.getAttrType())){
            throw new MilkTeaException(MilkTeaErrorConstant.ATTR_TYPE_REQUIRED);
        }
        
        try {
            this.attributesInfoMapper.updateByPrimaryKey(attributesInfo);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }

    }

    @Override
    public List<TeaAttributesInfo> queryAttributesByGoodsId(String goodsId) throws MilkTeaException {
        // TODO Auto-generated method stub
        return null;
    }
    
    

    @Override
    public List<TeaAttributesInfo> queryAttributesInfo() throws MilkTeaException {

        List<TeaAttributesInfo> list = null;
        try {
            list = this.attributesInfoMapper.selectAll();
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        return list;
    }

    @Override
    public TeaAttributesInfo queryAttributesByAttrId(String attrId) throws MilkTeaException {
        TeaAttributesInfo attributesInfo = new TeaAttributesInfo();
        try {
            attributesInfo = this.attributesInfoMapper.selectByPrimaryKey(attrId);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        return attributesInfo;
    }

}

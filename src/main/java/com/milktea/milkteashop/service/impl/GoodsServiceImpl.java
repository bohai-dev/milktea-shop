package com.milktea.milkteashop.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.milktea.milkteashop.dao.TeaGoodsAttrMapper;
import com.milktea.milkteashop.dao.TeaGoodsClassMapper;
import com.milktea.milkteashop.dao.TeaGoodsInfoMapper;
import com.milktea.milkteashop.domain.TeaAttributesInfo;
import com.milktea.milkteashop.domain.TeaClassInfo;
import com.milktea.milkteashop.domain.TeaGoodsAttr;
import com.milktea.milkteashop.domain.TeaGoodsClass;
import com.milktea.milkteashop.domain.TeaGoodsInfo;
import com.milktea.milkteashop.exception.MilkTeaErrorConstant;
import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.GoodsService;
import com.milktea.milkteashop.vo.GoodsInfoVo;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
    
    static Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);
    
    @Autowired
    private TeaGoodsInfoMapper goodsInfoMapper;
    
    @Autowired
    private TeaGoodsClassMapper goodsClassMapper;
    
    @Autowired
    private TeaGoodsAttrMapper goodsAttrMapper;

    @Override
    @Transactional
    public void addGoodsInfo(GoodsInfoVo infoVo) throws MilkTeaException {
        
        if(infoVo == null){
            throw new MilkTeaException(MilkTeaErrorConstant.PARAMETER_REQUIRED);
        }
        
        if(StringUtils.isBlank(infoVo.getStoreNo())){
            throw new MilkTeaException(MilkTeaErrorConstant.STORE_NO_REQUIRED);
        }
        
        if(StringUtils.isBlank(infoVo.getCnGoodsName())){
            throw new MilkTeaException(MilkTeaErrorConstant.CN_GOODS_NAME_REQUIRED);
        }
        
        if(StringUtils.isBlank(infoVo.getUsGoodsName())){
            throw new MilkTeaException(MilkTeaErrorConstant.US_GOODS_NAME_REQUIRED);
        }
        
        if(infoVo.getClassInfos() == null){
            throw new MilkTeaException(MilkTeaErrorConstant.GOODS_CLASS_REQUIRED);
        }
        
        TeaGoodsInfo dest = new TeaGoodsInfo();
        try {
            BeanUtils.copyProperties(dest, infoVo);
            this.goodsInfoMapper.insert(dest);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.UNKNOW_EXCEPTION.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.UNKNOW_EXCEPTION, e);
        }
        
        //添加商品分类
        for (TeaClassInfo classInfo : infoVo.getClassInfos()) {
            if(StringUtils.isBlank(classInfo.getClassId())){
                throw new MilkTeaException(MilkTeaErrorConstant.CLASS_ID_REQUIRED);
            }
            
            TeaGoodsClass goodsClass = new TeaGoodsClass();
            goodsClass.setClassId(classInfo.getClassId());
            //TODO
            goodsClass.setGoodsId();
            try {
                this.goodsClassMapper.insert(goodsClass);
            } catch (Exception e) {
                logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
                throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
            }
        }
        
        //添加商品属性
        if(infoVo.getGoodsAttrs() != null){
            for(TeaAttributesInfo attributesInfo : infoVo.getGoodsAttrs()){
                if(StringUtils.isBlank(attributesInfo.getAttrId())){
                    throw new MilkTeaException(MilkTeaErrorConstant.ATTR_ID_REQUIRED);
                }
                TeaGoodsAttr goodsAttr = new TeaGoodsAttr();
                goodsAttr.setAttrId(attributesInfo.getAttrId());
                //TODO
                goodsAttr.setGoodsId();
                try {
                    this.goodsAttrMapper.insert(goodsAttr);
                } catch (Exception e) {
                    logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
                    throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
                }
            }
        }

    }

    @Override
    public void modifyGoodsInfo(GoodsInfoVo infoVo) throws MilkTeaException {
        
        if(infoVo == null){
            throw new MilkTeaException(MilkTeaErrorConstant.PARAMETER_REQUIRED);
        }
        
        if(StringUtils.isBlank(infoVo.getGoodsId())){
            throw new MilkTeaException(MilkTeaErrorConstant.GOODS_ID_REQUIRED);
        }
        
        if(StringUtils.isBlank(infoVo.getStoreNo())){
            throw new MilkTeaException(MilkTeaErrorConstant.STORE_NO_REQUIRED);
        }
        
        if(StringUtils.isBlank(infoVo.getCnGoodsName())){
            throw new MilkTeaException(MilkTeaErrorConstant.CN_GOODS_NAME_REQUIRED);
        }
        
        if(StringUtils.isBlank(infoVo.getUsGoodsName())){
            throw new MilkTeaException(MilkTeaErrorConstant.US_GOODS_NAME_REQUIRED);
        }
        
        if(infoVo.getClassInfos() == null){
            throw new MilkTeaException(MilkTeaErrorConstant.GOODS_CLASS_REQUIRED);
        }
        
        TeaGoodsInfo dest = new TeaGoodsInfo();
        try {
            BeanUtils.copyProperties(dest, infoVo);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.UNKNOW_EXCEPTION.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.UNKNOW_EXCEPTION, e);
        }
        
        //TODO  修改商品分类和属性
        try {
            this.goodsClassMapper.deleteByGoodsId(infoVo.getGoodsId());
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }

    }

    @Override
    public void removeGoodsInfo(String goodsId) throws MilkTeaException {
        // TODO Auto-generated method stub

    }

    @Override
    public List<GoodsInfoVo> queryGoodsInfoByStoreNo(String storeNo) throws MilkTeaException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GoodsInfoVo queryGoodsInfo(String goodsId) throws MilkTeaException {
        // TODO Auto-generated method stub
        return null;
    }

}

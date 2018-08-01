package com.milktea.milkteashop.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.milktea.milkteashop.dao.TeaAttributesInfoMapper;
import com.milktea.milkteashop.dao.TeaClassInfoMapper;
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
import com.milktea.milkteashop.vo.ClassGoodsRequestVo;
import com.milktea.milkteashop.vo.ClassInfoVo;
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
    
    @Autowired
    private TeaClassInfoMapper classInfoMapper;
    
    @Autowired
    private TeaAttributesInfoMapper attributesInfoMapper;

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
        
        //生成店铺编号
        String storeNo = this.goodsInfoMapper.generateStoreNo();
        
        //添加商品分类
        for (TeaClassInfo classInfo : infoVo.getClassInfos()) {
            if(StringUtils.isBlank(classInfo.getClassId())){
                throw new MilkTeaException(MilkTeaErrorConstant.CLASS_ID_REQUIRED);
            }
            
            TeaGoodsClass goodsClass = new TeaGoodsClass();
            goodsClass.setClassId(classInfo.getClassId());
            goodsClass.setGoodsId(storeNo);
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
                goodsAttr.setGoodsId(storeNo);
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
        
        //修改商品分类（先删除后保存）
        try {
            this.goodsClassMapper.deleteByGoodsId(infoVo.getGoodsId());
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }

        for (TeaClassInfo classInfo : infoVo.getClassInfos()) {
            if(StringUtils.isBlank(classInfo.getClassId())){
                throw new MilkTeaException(MilkTeaErrorConstant.CLASS_ID_REQUIRED);
            }
            
            TeaGoodsClass goodsClass = new TeaGoodsClass();
            goodsClass.setClassId(classInfo.getClassId());
            goodsClass.setGoodsId(infoVo.getGoodsId());
            try {
                this.goodsClassMapper.insert(goodsClass);
            } catch (Exception e) {
                logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
                throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
            }
        }
        
        //修改商品属性（先删除后保存）
        
        try {
            this.goodsAttrMapper.deleteByGoodsId(infoVo.getGoodsId());
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        
        if(infoVo.getGoodsAttrs() != null){
            for(TeaAttributesInfo attributesInfo : infoVo.getGoodsAttrs()){
                if(StringUtils.isBlank(attributesInfo.getAttrId())){
                    throw new MilkTeaException(MilkTeaErrorConstant.ATTR_ID_REQUIRED);
                }
                TeaGoodsAttr goodsAttr = new TeaGoodsAttr();
                goodsAttr.setAttrId(attributesInfo.getAttrId());
                goodsAttr.setGoodsId(infoVo.getGoodsId());
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
    public void removeGoodsInfo(String goodsId) throws MilkTeaException {
        
        if(StringUtils.isBlank(goodsId)){
            throw new MilkTeaException(MilkTeaErrorConstant.GOODS_ID_REQUIRED);
        }
        
        try {
            this.goodsInfoMapper.logicalDeleteByPrimaryKey(goodsId);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        
    }

    @Override
    public List<GoodsInfoVo> queryGoodsInfo(GoodsInfoVo infoVo) throws MilkTeaException {
        
        if(infoVo == null){
            throw new MilkTeaException(MilkTeaErrorConstant.PARAMETER_REQUIRED);
        }
        
        if(StringUtils.isBlank(infoVo.getStoreNo())){
            throw new MilkTeaException(MilkTeaErrorConstant.STORE_NO_REQUIRED);
        }
        
        List<TeaGoodsInfo> goodsInfos = null;
        
        TeaGoodsInfo dest = new TeaGoodsInfo();
        try {
            BeanUtils.copyProperties(dest, infoVo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error(MilkTeaErrorConstant.UNKNOW_EXCEPTION.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.UNKNOW_EXCEPTION, e);
        }
        try {
            goodsInfos = this.goodsInfoMapper.selectByCondition(dest);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        
        List<GoodsInfoVo> list = null;
        if(goodsInfos != null){
            list = new ArrayList<>();
            for (TeaGoodsInfo goodsInfo : goodsInfos) {
                GoodsInfoVo vo = new GoodsInfoVo();
                try {
                    BeanUtils.copyProperties(vo, goodsInfo);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    logger.error(MilkTeaErrorConstant.UNKNOW_EXCEPTION.getCnErrorMsg(), e);
                    throw new MilkTeaException(MilkTeaErrorConstant.UNKNOW_EXCEPTION, e);
                }
                //查询商品分类
                List<TeaClassInfo> classInfos = null;
                try {
                    classInfos = this.classInfoMapper.selectByGoodsId(goodsInfo.getGoodsId());
                } catch (Exception e) {
                    logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
                    throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
                }
                vo.setClassInfos(classInfos);
                //查询商品属性
                List<TeaAttributesInfo> attributesInfos = null;
                try {
                    attributesInfos = this.attributesInfoMapper.selectByGoodsId(goodsInfo.getGoodsId());
                } catch (Exception e) {
                    logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
                    throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
                }
                vo.setGoodsAttrs(attributesInfos);
                list.add(vo);
            }
        }
        
        return list;
    }

    @Override
    public GoodsInfoVo queryGoodsInfo(String goodsId) throws MilkTeaException {
        if(StringUtils.isBlank(goodsId)){
            throw new MilkTeaException(MilkTeaErrorConstant.GOODS_ID_REQUIRED);
        }
        GoodsInfoVo goodsInfoVo = new GoodsInfoVo();
        TeaGoodsInfo goodsInfo = null;
        try {
            goodsInfo = this.goodsInfoMapper.selectByPrimaryKey(goodsId);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        
        if(goodsInfo != null){
            try {
                BeanUtils.copyProperties(goodsInfoVo, goodsInfo);
            } catch (Exception e) {
                logger.error(MilkTeaErrorConstant.UNKNOW_EXCEPTION.getCnErrorMsg(), e);
                throw new MilkTeaException(MilkTeaErrorConstant.UNKNOW_EXCEPTION, e);
            }
            
            //查询商品分类
            List<TeaClassInfo> classInfos = null;
            try {
                classInfos = this.classInfoMapper.selectByGoodsId(goodsInfo.getGoodsId());
            } catch (Exception e) {
                logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
                throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
            }
            goodsInfoVo.setClassInfos(classInfos);
            //查询商品属性
            List<TeaAttributesInfo> attributesInfos = null;
            try {
                attributesInfos = this.attributesInfoMapper.selectByGoodsId(goodsInfo.getGoodsId());
            } catch (Exception e) {
                logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
                throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
            }
            goodsInfoVo.setGoodsAttrs(attributesInfos);
        }
        
        return goodsInfoVo;
    }

    @Override
    public List<ClassInfoVo> queryClassGoods(ClassGoodsRequestVo requestVo) throws MilkTeaException {

        //参数非空校验
        if(requestVo == null){
            throw new MilkTeaException(MilkTeaErrorConstant.PARAMETER_REQUIRED);
        }
        
        //店铺编号非空校验
        if(StringUtils.isBlank(requestVo.getStoreNo())){
            throw new MilkTeaException(MilkTeaErrorConstant.STORE_NO_REQUIRED);
        }
        
        List<TeaClassInfo> classInfos = null;
        TeaClassInfo classInfo = new TeaClassInfo();
        classInfo.setClassType(requestVo.getClassType());
        try {
            classInfos = this.classInfoMapper.selectByCondition(classInfo);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        
        List<ClassInfoVo> list = null;
        
        if(classInfos != null){
            list = new ArrayList<ClassInfoVo>();
            for (TeaClassInfo teaClassInfo : classInfos) {
                ClassInfoVo classInfoVo = new ClassInfoVo();
                try {
                    BeanUtils.copyProperties(classInfoVo, teaClassInfo);
                } catch (Exception e) {
                    logger.error(MilkTeaErrorConstant.UNKNOW_EXCEPTION.getCnErrorMsg(), e);
                    throw new MilkTeaException(MilkTeaErrorConstant.UNKNOW_EXCEPTION, e);
                }
                
                
                //根据商品分类查询商品信息
                List<TeaGoodsInfo> goodsList = null;
                TeaGoodsInfo goodsInfo = new TeaGoodsInfo();
                goodsInfo.setStoreNo(requestVo.getStoreNo());
                goodsInfo.setClassId(teaClassInfo.getClassId());
                try {
                    goodsList = this.goodsInfoMapper.selectByCondition(goodsInfo);
                } catch (Exception e) {
                    logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
                    throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
                }
                
                //查询商品属性
                List<GoodsInfoVo> goodsInfoVos = null;
                if(goodsList != null){
                    goodsInfoVos = new ArrayList<GoodsInfoVo>();
                    for (TeaGoodsInfo info : goodsList) {
                        GoodsInfoVo goodsInfoVo = new GoodsInfoVo();
                        try {
                            BeanUtils.copyProperties(goodsInfoVo, info);
                        } catch (Exception e) {
                            logger.error(MilkTeaErrorConstant.UNKNOW_EXCEPTION.getCnErrorMsg(), e);
                            throw new MilkTeaException(MilkTeaErrorConstant.UNKNOW_EXCEPTION, e);
                        }
                        
                        List<TeaAttributesInfo> attributesInfos = null;
                        try {
                            attributesInfos = this.attributesInfoMapper.selectByGoodsId(info.getGoodsId());
                        } catch (Exception e) {
                            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
                            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
                        }
                        
                        goodsInfoVo.setGoodsAttrs(attributesInfos);
                        goodsInfoVos.add(goodsInfoVo);
                    }
                    
                }
                
                classInfoVo.setGoods(goodsInfoVos);
                list.add(classInfoVo);
            }
        }
        
        return list;
    }

}

package com.milktea.milkteashop.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
import com.milktea.milkteashop.vo.ClassInfoNationVo;
import com.milktea.milkteashop.vo.ClassInfoVo;
import com.milktea.milkteashop.vo.DeductGoodsStockRequestVo;
import com.milktea.milkteashop.vo.GoodsInfoNationVo;
import com.milktea.milkteashop.vo.GoodsInfoVo;
import com.milktea.milkteashop.vo.GoodsStockAndStatusRequestVo;
import com.milktea.milkteashop.vo.TeaAttributesInfoNationVo;

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
        
        TeaGoodsInfo info = null;
        try {
            info = this.goodsInfoMapper.selectByCnName(infoVo.getCnGoodsName(),infoVo.getStoreNo());
            
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        
        if(info != null){
            throw new MilkTeaException(MilkTeaErrorConstant.CN_GOODS_NAME_EXISTS);
        }
        
        try {
            info = this.goodsInfoMapper.selectByUsName(infoVo.getUsGoodsName(),infoVo.getStoreNo());
            
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        
        if(info != null){
            throw new MilkTeaException(MilkTeaErrorConstant.US_GOODS_NAME_EXISTS);
        }
        
        TeaGoodsInfo dest = new TeaGoodsInfo();
        try {
            BeanUtils.copyProperties(infoVo, dest);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.UNKNOW_EXCEPTION.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.UNKNOW_EXCEPTION, e);
        }
        
        //生成商品ID
        String goodsId = this.goodsInfoMapper.generateGoodsId();
        dest.setGoodsId(goodsId);
        try {
            this.goodsInfoMapper.insert(dest);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        
        //添加商品分类
        for (TeaClassInfo classInfo : infoVo.getClassInfos()) {
            if(StringUtils.isBlank(classInfo.getClassId())){
                throw new MilkTeaException(MilkTeaErrorConstant.CLASS_ID_REQUIRED);
            }
            
            TeaGoodsClass goodsClass = new TeaGoodsClass();
            goodsClass.setClassId(classInfo.getClassId());
            goodsClass.setGoodsId(goodsId);
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
                goodsAttr.setGoodsId(goodsId);
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
    @Transactional
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
            BeanUtils.copyProperties(infoVo, dest);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.UNKNOW_EXCEPTION.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.UNKNOW_EXCEPTION, e);
        }
        
        //交易商品名称
        Long count = null;
        try {
            count = this.goodsInfoMapper.countOthersByCnName(dest.getGoodsId(), dest.getCnGoodsName(),infoVo.getStoreNo());
            
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        
        if(count > 0){
            throw new MilkTeaException(MilkTeaErrorConstant.CN_GOODS_NAME_EXISTS);
        }
        
        try {
            count = this.goodsInfoMapper.countOthersByUsName(dest.getGoodsId(), dest.getUsGoodsName(),infoVo.getStoreNo());
            
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        
        if(count > 0){
            throw new MilkTeaException(MilkTeaErrorConstant.US_GOODS_NAME_EXISTS);
        }
        
        //先修改商品主信息
        try {
            this.goodsInfoMapper.updateByPrimaryKey(dest);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
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
            BeanUtils.copyProperties(infoVo, dest);
        } catch (Exception e) {
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
                    BeanUtils.copyProperties(goodsInfo, vo);
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
    public GoodsInfoVo queryGoodsDetail(String goodsId) throws MilkTeaException {
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
                BeanUtils.copyProperties(goodsInfo, goodsInfoVo);
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
                    BeanUtils.copyProperties(teaClassInfo, classInfoVo);
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
                            BeanUtils.copyProperties(info, goodsInfoVo);
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

    @Override
    public void updateGoodsStockAndStatus(GoodsStockAndStatusRequestVo requestVo) throws MilkTeaException {

        //参数非空检验
        if(requestVo == null){
            throw new MilkTeaException(MilkTeaErrorConstant.PARAMETER_REQUIRED);
        }
        
        if(StringUtils.isBlank(requestVo.getGoodsId())){
            throw new MilkTeaException(MilkTeaErrorConstant.GOODS_ID_REQUIRED);
        }
        
        if(StringUtils.isBlank(requestVo.getGoodsStatus()) && requestVo.getGoodsStock() == null){
            throw new MilkTeaException(MilkTeaErrorConstant.PARAMETER_REQUIRED);
        }
        
        TeaGoodsInfo record = new TeaGoodsInfo();
        record.setGoodsId(requestVo.getGoodsId());
        record.setGoodsStatus(requestVo.getGoodsStatus());
        record.setGoodsStock(requestVo.getGoodsStock());
        try {
            this.goodsInfoMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        
    }

    @Override
    public synchronized void deductGoodsStock(DeductGoodsStockRequestVo requestVo) throws MilkTeaException {
        
        if(requestVo == null){
            throw new MilkTeaException(MilkTeaErrorConstant.PARAMETER_REQUIRED);
        }
        
        if(StringUtils.isBlank(requestVo.getGoodsId())){
            throw new MilkTeaException(MilkTeaErrorConstant.GOODS_ID_REQUIRED);
        }
        
        if(requestVo.getVolume() == null){
            throw new MilkTeaException(MilkTeaErrorConstant.VOLUME_ILLEGAL);
        }
        
        TeaGoodsInfo goodsInfo = null;
        
        try {
            goodsInfo = this.goodsInfoMapper.selectByPrimaryKey(requestVo.getGoodsId());
            
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        
        if(goodsInfo == null){
            logger.warn(MilkTeaErrorConstant.GOODS_NOT_EXISTS.getCnErrorMsg());
            throw new MilkTeaException(MilkTeaErrorConstant.GOODS_NOT_EXISTS);
        }
        
        //非在售商品
        if(goodsInfo.getGoodsStatus() != null && !goodsInfo.getGoodsStatus().equals("1")){
            logger.warn(MilkTeaErrorConstant.GOODS_NOT_ON_SALE.getCnErrorMsg());
            throw new MilkTeaException(MilkTeaErrorConstant.GOODS_NOT_ON_SALE);
        }
        
        //商品库存未维护
        if(goodsInfo.getGoodsStock() == null){
            logger.warn(MilkTeaErrorConstant.GOODS_STOCK_UNMAINTAINED.getCnErrorMsg());
            throw new MilkTeaException(MilkTeaErrorConstant.GOODS_STOCK_UNMAINTAINED);
        }
        
        //扣减商品库存
        if(goodsInfo.getGoodsStock().compareTo(requestVo.getVolume()) > -1){
            try {
                this.goodsInfoMapper.updateStockByGoodsId(requestVo.getGoodsId(), requestVo.getVolume());
            } catch (Exception e) {
                logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
                throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
            }
        }else {
            throw new MilkTeaException(MilkTeaErrorConstant.LACK_OF_STOCK);
        }
        
    }

    @Override
    public List<ClassInfoNationVo> queryClassGoodsNation(ClassGoodsRequestVo requestVo) throws MilkTeaException {
        //参数非空校验
        if(requestVo == null){
            throw new MilkTeaException(MilkTeaErrorConstant.PARAMETER_REQUIRED);
        }
        
        //店铺编号非空校验
        if(StringUtils.isBlank(requestVo.getStoreNo())){
            throw new MilkTeaException(MilkTeaErrorConstant.STORE_NO_REQUIRED);
        }
        
        if(StringUtils.isBlank(requestVo.getLang())){
            throw new MilkTeaException(MilkTeaErrorConstant.LANG_REQUIRED);
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
        
        List<ClassInfoNationVo> list = null;
        
        if(classInfos != null){
            list = new ArrayList<ClassInfoNationVo>();
            for (TeaClassInfo teaClassInfo : classInfos) {
                ClassInfoNationVo classInfoVo = new ClassInfoNationVo();
                try {
                    BeanUtils.copyProperties(teaClassInfo, classInfoVo);
                } catch (Exception e) {
                    logger.error(MilkTeaErrorConstant.UNKNOW_EXCEPTION.getCnErrorMsg(), e);
                    throw new MilkTeaException(MilkTeaErrorConstant.UNKNOW_EXCEPTION, e);
                }
                if(requestVo.getLang().equals("zh")){
                    classInfoVo.setClassName(teaClassInfo.getCnClassName());
                    classInfoVo.setClassLogo(teaClassInfo.getCnClassLogo());
                }else if (requestVo.getLang().equals("en")) {
                    classInfoVo.setClassName(teaClassInfo.getUsClassName());
                    classInfoVo.setClassLogo(teaClassInfo.getUsClassLogo());
                }
                
                //根据商品分类查询商品信息
                List<TeaGoodsInfo> goodsList = null;
                TeaGoodsInfo goodsInfo = new TeaGoodsInfo();
                goodsInfo.setStoreNo(requestVo.getStoreNo());
                goodsInfo.setClassId(teaClassInfo.getClassId());
                //只查询在售商品
                goodsInfo.setGoodsStatus("1");
                try {
                    goodsList = this.goodsInfoMapper.selectByCondition(goodsInfo);
                } catch (Exception e) {
                    logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
                    throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
                }
                
                //查询商品属性
                List<GoodsInfoNationVo> goodsInfoVos = null;
                if(goodsList != null){
                    goodsInfoVos = new ArrayList<GoodsInfoNationVo>();
                    for (TeaGoodsInfo info : goodsList) {
                        GoodsInfoNationVo goodsInfoVo = new GoodsInfoNationVo();
                        try {
                            BeanUtils.copyProperties(info, goodsInfoVo);
                        } catch (Exception e) {
                            logger.error(MilkTeaErrorConstant.UNKNOW_EXCEPTION.getCnErrorMsg(), e);
                            throw new MilkTeaException(MilkTeaErrorConstant.UNKNOW_EXCEPTION, e);
                        }
                        
                        if(requestVo.getLang().equals("zh")){
                            goodsInfoVo.setGoodsName(info.getCnGoodsName());
                            goodsInfoVo.setGoodsIntroduction(info.getCnGoodsIntroduction());
                            goodsInfoVo.setGoodsPictureBig(info.getCnGoodsPictureBig());
                            goodsInfoVo.setGoodsPictureRound(info.getCnGoodsPictureRound());
                        }else if (requestVo.getLang().equals("en")) {
                            goodsInfoVo.setGoodsName(info.getUsGoodsName());
                            goodsInfoVo.setGoodsIntroduction(info.getUsGoodsIntroduction());
                            goodsInfoVo.setGoodsPictureBig(info.getUsGoodsPictureBig());
                            goodsInfoVo.setGoodsPictureRound(info.getUsGoodsPictureRound());
                        }
                        
                        List<TeaAttributesInfo> attributesInfos = null;
                        try {
                            attributesInfos = this.attributesInfoMapper.selectByGoodsId(info.getGoodsId());
                        } catch (Exception e) {
                            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
                            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
                        }
                        
                        if(attributesInfos != null && attributesInfos.size() > 0){
                            List<TeaAttributesInfoNationVo> nationVos = new ArrayList<>();
                            for (TeaAttributesInfo attributesInfo : attributesInfos) {
                                TeaAttributesInfoNationVo target = new TeaAttributesInfoNationVo();
                                BeanUtils.copyProperties(attributesInfo, target);
                                if(requestVo.getLang().equals("zh")){
                                    target.setAttrName(attributesInfo.getCnAttrName());
                                }else if (requestVo.getLang().equals("en")) {
                                    target.setAttrName(attributesInfo.getUsAttrName());
                                }
                                nationVos.add(target);
                            }
                            goodsInfoVo.setGoodsAttrs(nationVos);
                        }
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

package com.milktea.milkteashop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milktea.milkteashop.dao.TeaPromotionInfoMapper;
import com.milktea.milkteashop.domain.TeaPromotionInfo;
import com.milktea.milkteashop.exception.MilkTeaErrorConstant;
import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.PromotionService;
import com.milktea.milkteashop.vo.PromotionNationVo;
import com.milktea.milkteashop.vo.PromotionVo;
import com.milktea.milkteashop.vo.QueryPromotionByStoreNoNationRequestVo;

@Service("promotionService")
public class PromotionServiceImpl implements PromotionService {
    
    static Logger logger = LoggerFactory.getLogger(PromotionServiceImpl.class);
    
    @Autowired
    private TeaPromotionInfoMapper promotionInfoMapper;

    @Override
    public void addPromotion(PromotionVo promotionInfo) throws MilkTeaException {

        //参数非空校验
        if(promotionInfo == null){
            throw new MilkTeaException(MilkTeaErrorConstant.PARAMETER_REQUIRED);
        }
        
        if(StringUtils.isBlank(promotionInfo.getCnPromotionName())){
            throw new MilkTeaException(MilkTeaErrorConstant.CN_PROMOTION_NAME_REQUIRED);
        }
        
        if(StringUtils.isBlank(promotionInfo.getUsPromotionName())){
            throw new MilkTeaException(MilkTeaErrorConstant.US_PROMOTION_NAME_REQUIRED);
        }
        
        TeaPromotionInfo dest = new TeaPromotionInfo();
        try {
            BeanUtils.copyProperties(promotionInfo, dest);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.UNKNOW_EXCEPTION.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.UNKNOW_EXCEPTION, e);
        }
        
        StringBuffer sb = new StringBuffer();
        if(promotionInfo.getStoreNos() != null && promotionInfo.getStoreNos().size() > 0){
            for (String s : promotionInfo.getStoreNos()) {
                if(StringUtils.isBlank(s)){
                    throw new MilkTeaException(MilkTeaErrorConstant.STORE_NO_REQUIRED);
                }
                sb.append(s+",");
            }
            //删除最后一个逗号
            sb.deleteCharAt(sb.length()-1);
        }
        dest.setStoreNos(sb.toString());

        try {
            dest.setPromotionId(this.promotionInfoMapper.generatePromotionId());
            this.promotionInfoMapper.insertSelective(dest);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
    }

    @Override
    public void removePromotion(String promotionId) throws MilkTeaException {

        if(StringUtils.isBlank(promotionId)){
            throw new MilkTeaException(MilkTeaErrorConstant.PROMOTION_ID_REQUIRED);
        }
        
        try {
            this.promotionInfoMapper.logicalDeleteByPrimaryKey(promotionId);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }

    }

    @Override
    public void modifyPromotion(PromotionVo promotionInfo) throws MilkTeaException {
        //参数非空校验
        if(promotionInfo == null){
            throw new MilkTeaException(MilkTeaErrorConstant.PARAMETER_REQUIRED);
        }
        
        if(StringUtils.isBlank(promotionInfo.getPromotionId())){
            throw new MilkTeaException(MilkTeaErrorConstant.PROMOTION_ID_REQUIRED);
        }
        
        if(StringUtils.isBlank(promotionInfo.getCnPromotionName())){
            throw new MilkTeaException(MilkTeaErrorConstant.CN_PROMOTION_NAME_REQUIRED);
        }
        
        if(StringUtils.isBlank(promotionInfo.getUsPromotionName())){
            throw new MilkTeaException(MilkTeaErrorConstant.US_PROMOTION_NAME_REQUIRED);
        }

        TeaPromotionInfo dest = new TeaPromotionInfo();
        try {
            BeanUtils.copyProperties(promotionInfo, dest);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.UNKNOW_EXCEPTION.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.UNKNOW_EXCEPTION, e);
        }
        
        StringBuffer sb = new StringBuffer();
        if(promotionInfo.getStoreNos() != null && promotionInfo.getStoreNos().size() > 0){
            for (String s : promotionInfo.getStoreNos()) {
                if(StringUtils.isBlank(s)){
                    throw new MilkTeaException(MilkTeaErrorConstant.STORE_NO_REQUIRED);
                }
                sb.append(s+",");
            }
            //删除最后一个逗号
            sb.deleteCharAt(sb.length()-1);
        }
        dest.setStoreNos(sb.toString());
        
        try {
            this.promotionInfoMapper.updateByPrimaryKey(dest);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
    }

    @Override
    public List<PromotionVo> queryPromotions(TeaPromotionInfo request) throws MilkTeaException {

        List<TeaPromotionInfo> list = null;
        try {
            list = this.promotionInfoMapper.selectByCondition(request);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        
        List<PromotionVo> list2 = new ArrayList<PromotionVo>();
        if(list != null){
            for (TeaPromotionInfo info : list) {
                PromotionVo dest = new PromotionVo();
                try {
                    BeanUtils.copyProperties(info, dest);
                } catch (Exception e) {
                    logger.error(MilkTeaErrorConstant.UNKNOW_EXCEPTION.getCnErrorMsg(), e);
                    throw new MilkTeaException(MilkTeaErrorConstant.UNKNOW_EXCEPTION, e);
                }
                
                if(StringUtils.isNotBlank(info.getStoreNos())){
                    String[] s = info.getStoreNos().split(",");
                    List<String> storeList = java.util.Arrays.asList(s);
                    dest.setStoreNos(storeList);
                }
                list2.add(dest);
            }
        }
        
        return list2;
    }

    @Override
    public List<PromotionVo> queryPromotions(String storeNo) throws MilkTeaException {
        
        if(StringUtils.isBlank(storeNo)){
            throw new MilkTeaException(MilkTeaErrorConstant.STORE_NO_REQUIRED);
        }
        
        List<TeaPromotionInfo> list = null;
        try {
            list = this.promotionInfoMapper.selectByStoreNo(storeNo);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        
        List<PromotionVo> list2 = new ArrayList<PromotionVo>();
        if(list != null){
            for (TeaPromotionInfo info : list) {
                PromotionVo dest = new PromotionVo();
                try {
                    BeanUtils.copyProperties(info, dest);
                } catch (Exception e) {
                    logger.error(MilkTeaErrorConstant.UNKNOW_EXCEPTION.getCnErrorMsg(), e);
                    throw new MilkTeaException(MilkTeaErrorConstant.UNKNOW_EXCEPTION, e);
                }
                
                if(StringUtils.isNotBlank(info.getStoreNos())){
                    String[] s = info.getStoreNos().split(",");
                    List<String> storeList = java.util.Arrays.asList(s);
                    dest.setStoreNos(storeList);
                }
                list2.add(dest);
            }
        }
        
        return list2;
    }

    @Override
    public PromotionVo queryPromotion(String promotionId, String storeNo) throws MilkTeaException {
        
        
        if(StringUtils.isBlank(promotionId)){
            throw new MilkTeaException(MilkTeaErrorConstant.PROMOTION_ID_REQUIRED);
        }
        
        if(StringUtils.isBlank(storeNo)){
            throw new MilkTeaException(MilkTeaErrorConstant.STORE_NO_REQUIRED);
        }
        
        TeaPromotionInfo info = null;
        try {
            info = this.promotionInfoMapper.selectByStoreNoAndId(storeNo, promotionId);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        
        PromotionVo dest = null;
        
        if(info != null){
            dest = new PromotionVo();
            try {
                BeanUtils.copyProperties(info, dest);
            } catch (Exception e) {
                logger.error(MilkTeaErrorConstant.UNKNOW_EXCEPTION.getCnErrorMsg(), e);
                throw new MilkTeaException(MilkTeaErrorConstant.UNKNOW_EXCEPTION, e);
            }
            
            if(StringUtils.isNotBlank(info.getStoreNos())){
                String[] s = info.getStoreNos().split(",");
                List<String> storeList = java.util.Arrays.asList(s);
                dest.setStoreNos(storeList);
            }
        }
        
        return dest;
    }

    @Override
    public List<PromotionNationVo> queryPromotionsNation(QueryPromotionByStoreNoNationRequestVo requestVo) throws MilkTeaException {
        
        if(requestVo == null){
            throw new MilkTeaException(MilkTeaErrorConstant.PARAMETER_REQUIRED);
        }
        
        if(StringUtils.isBlank(requestVo.getStoreNo())){
            throw new MilkTeaException(MilkTeaErrorConstant.STORE_NO_REQUIRED);
        }
        
        if(StringUtils.isBlank(requestVo.getLang())){
            throw new MilkTeaException(MilkTeaErrorConstant.LANG_REQUIRED);
        }
        
        List<TeaPromotionInfo> list = null;
        try {
            list = this.promotionInfoMapper.selectByStoreNo(requestVo.getStoreNo());
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        
        List<PromotionNationVo> list2 = new ArrayList<PromotionNationVo>();
        if(list != null){
            for (TeaPromotionInfo info : list) {
                PromotionNationVo dest = new PromotionNationVo();
                try {
                    BeanUtils.copyProperties(info, dest);
                } catch (Exception e) {
                    logger.error(MilkTeaErrorConstant.UNKNOW_EXCEPTION.getCnErrorMsg(), e);
                    throw new MilkTeaException(MilkTeaErrorConstant.UNKNOW_EXCEPTION, e);
                }
                
                if(requestVo.getLang().equals("zh")){
                    dest.setPromotionName(info.getCnPromotionName());
                    dest.setPromotionIntroduction(info.getCnPromotionIntroduction());
                }else if (requestVo.getLang().equals("en")) {
                    dest.setPromotionName(info.getUsPromotionName());
                    dest.setPromotionIntroduction(info.getUsPromotionIntroduction());
                }
                
                if(StringUtils.isNotBlank(info.getStoreNos())){
                    String[] s = info.getStoreNos().split(",");
                    List<String> storeList = java.util.Arrays.asList(s);
                    dest.setStoreNos(storeList);
                }
                list2.add(dest);
            }
        }
        
        return list2;
    }

    @Override
    public PromotionVo queryPromotionById(String promotionId) throws MilkTeaException {
        if(StringUtils.isBlank(promotionId)){
            throw new MilkTeaException(MilkTeaErrorConstant.PROMOTION_ID_REQUIRED);
        }
        
        TeaPromotionInfo info = null;
        try {
            info = this.promotionInfoMapper.selectByPrimaryKey(promotionId);
        } catch (Exception e) {
            logger.error(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE.getCnErrorMsg(), e);
            throw new MilkTeaException(MilkTeaErrorConstant.DATABASE_ACCESS_FAILURE, e);
        }
        
        PromotionVo vo = null;
        if(info != null){
                vo = new PromotionVo();
                try {
                    BeanUtils.copyProperties(info, vo);
                } catch (Exception e) {
                    logger.error(MilkTeaErrorConstant.UNKNOW_EXCEPTION.getCnErrorMsg(), e);
                    throw new MilkTeaException(MilkTeaErrorConstant.UNKNOW_EXCEPTION, e);
                }
                
                if(StringUtils.isNotBlank(info.getStoreNos())){
                    String[] s = info.getStoreNos().split(",");
                    List<String> storeList = java.util.Arrays.asList(s);
                    vo.setStoreNos(storeList);
                }
        }
        
        return vo;
    }

}

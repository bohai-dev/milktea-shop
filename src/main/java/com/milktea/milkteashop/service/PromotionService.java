package com.milktea.milkteashop.service;

import java.util.List;

import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.vo.PromotionNationVo;
import com.milktea.milkteashop.vo.PromotionVo;
import com.milktea.milkteashop.vo.QueryPromotionByStoreNoNationRequestVo;

/**
 * 活动接口
 * @author caojia
 *
 */
public interface PromotionService {

    /**
     * 新增活动
     * @param promotionInfo
     * @throws MilkTeaException
     */
    public void addPromotion(PromotionVo promotionInfo) throws MilkTeaException;
    
    /**
     * 删除活动
     * @param promotionId
     * @throws MilkTeaException
     */
    public void removePromotion(String promotionId) throws MilkTeaException;
    
    /**
     * 修改活动信息
     * @param promotionInfo
     * @throws MilkTeaException
     */
    public void modifyPromotion(PromotionVo promotionInfo) throws MilkTeaException;
    
    /**
     * 查询活动信息
     * @return
     * @throws MilkTeaException
     */
    public List<PromotionVo> queryPromotions() throws MilkTeaException;
    
    /**
     * 查询有效活动信息
     * @return
     * @throws MilkTeaException
     */
    public List<PromotionVo> queryPromotions(String storeNo) throws MilkTeaException;
    
    /**
     * 查询有效活动信息(支持国际化)
     * @return
     * @throws MilkTeaException
     */
    public List<PromotionNationVo> queryPromotionsNation(QueryPromotionByStoreNoNationRequestVo requestVo) throws MilkTeaException;
    
    /**
     * 查询活动详情
     * @param promotionId
     * @param storeNo
     * @return
     * @throws MilkTeaException
     */
    public PromotionVo queryPromotion(String promotionId, String storeNo) throws MilkTeaException;
}

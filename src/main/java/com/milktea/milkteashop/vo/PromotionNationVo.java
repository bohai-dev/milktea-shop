package com.milktea.milkteashop.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 促销活动
 * @author caojia
 *
 */
public class PromotionNationVo {

    private String promotionId;

    private String promotionName;

    private String promotionIntroduction;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    private String promotionStatus;

    private List<String> storeNos;

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public String getPromotionIntroduction() {
        return promotionIntroduction;
    }

    public void setPromotionIntroduction(String promotionIntroduction) {
        this.promotionIntroduction = promotionIntroduction;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPromotionStatus() {
        return promotionStatus;
    }

    public void setPromotionStatus(String promotionStatus) {
        this.promotionStatus = promotionStatus;
    }

    public List<String> getStoreNos() {
        return storeNos;
    }

    public void setStoreNos(List<String> storeNos) {
        this.storeNos = storeNos;
    }
    
}

package com.milktea.milkteashop.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品信息
 * @author caojia
 *
 */
public class GoodsInfoNationVo {
    
    /**
     * 商品Id
     */
    private String goodsId;

    /**
     * 店铺编号
     */
    private String storeNo;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品介绍
     */
    private String goodsIntroduction;

    /**
     * 商品图片路径
     */
    private String goodsPictureBig;

    /**
     * 商品图片路径
     */
    private String goodsPictureRound;

    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;

    /**
     * 商品状态 0：不在售 1：在售
     */
    private String goodsStatus;

    /**
     * 商品库存
     */
    private BigDecimal goodsStock;

    /**
     * 商品属性
     */
    private List<TeaAttributesInfoNationVo> goodsAttrs;
    
    /**
     * 商品分类
     */
    private List<TeaClassInfoNationVo> classInfos;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }


    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public BigDecimal getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(BigDecimal goodsStock) {
        this.goodsStock = goodsStock;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsIntroduction() {
        return goodsIntroduction;
    }

    public void setGoodsIntroduction(String goodsIntroduction) {
        this.goodsIntroduction = goodsIntroduction;
    }

    public String getGoodsPictureBig() {
        return goodsPictureBig;
    }

    public void setGoodsPictureBig(String goodsPictureBig) {
        this.goodsPictureBig = goodsPictureBig;
    }

    public String getGoodsPictureRound() {
        return goodsPictureRound;
    }

    public void setGoodsPictureRound(String goodsPictureRound) {
        this.goodsPictureRound = goodsPictureRound;
    }

    public List<TeaAttributesInfoNationVo> getGoodsAttrs() {
        return goodsAttrs;
    }

    public void setGoodsAttrs(List<TeaAttributesInfoNationVo> goodsAttrs) {
        this.goodsAttrs = goodsAttrs;
    }

    public List<TeaClassInfoNationVo> getClassInfos() {
        return classInfos;
    }

    public void setClassInfos(List<TeaClassInfoNationVo> classInfos) {
        this.classInfos = classInfos;
    }


}

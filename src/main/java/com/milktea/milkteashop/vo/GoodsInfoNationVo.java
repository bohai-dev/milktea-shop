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
    private String GoodsName;

    /**
     * 商品介绍
     */
    private String GoodsIntroduction;

    /**
     * 商品图片路径
     */
    private String GoodsPictureBig;

    /**
     * 商品图片路径
     */
    private String GoodsPictureRound;

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
        return GoodsName;
    }

    public void setGoodsName(String goodsName) {
        GoodsName = goodsName;
    }

    public String getGoodsIntroduction() {
        return GoodsIntroduction;
    }

    public void setGoodsIntroduction(String goodsIntroduction) {
        GoodsIntroduction = goodsIntroduction;
    }

    public String getGoodsPictureBig() {
        return GoodsPictureBig;
    }

    public void setGoodsPictureBig(String goodsPictureBig) {
        GoodsPictureBig = goodsPictureBig;
    }

    public String getGoodsPictureRound() {
        return GoodsPictureRound;
    }

    public void setGoodsPictureRound(String goodsPictureRound) {
        GoodsPictureRound = goodsPictureRound;
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

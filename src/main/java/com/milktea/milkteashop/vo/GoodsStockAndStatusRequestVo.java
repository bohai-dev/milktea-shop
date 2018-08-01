package com.milktea.milkteashop.vo;

import java.math.BigDecimal;

/**
 * 商品状态和库存请求参数
 * @author caojia
 *
 */
public class GoodsStockAndStatusRequestVo {

    /**
     * 商品Id
     */
    private String goodsId;
    
    /**
     * 商品状态 0：不在售 1：在售
     */
    private String goodsStatus;

    /**
     * 商品库存
     */
    private BigDecimal goodsStock;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
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
    
    
}

package com.milktea.milkteashop.vo;

import java.math.BigDecimal;

/**
 * 扣减库存请求参数
 * @author caojia
 *
 */
public class DeductGoodsStockRequestVo {
    
    /**
     * 商品ID
     */
    private String goodsId;
    
    /**
     * 扣减数量
     */
    private BigDecimal volume;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

}

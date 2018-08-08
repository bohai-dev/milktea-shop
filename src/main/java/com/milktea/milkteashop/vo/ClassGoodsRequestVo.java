package com.milktea.milkteashop.vo;

/**
 * 分类商品请求参数
 * @author caojia
 *
 */
public class ClassGoodsRequestVo {
    
    /**
     * 店铺编号
     */
    private String storeNo;
    
    /**
     * 分类类型 0：普通分类 1：特色分类
     */
    private String classType;
    
    /**
     * 语言 zh:中文  en:英文
     */
    private String lang;

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

}

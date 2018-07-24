package com.milktea.milkteashop.vo;

import java.util.List;

public class ClassInfoVo {

    /**
     * 分类Id
     */
    private String classId;

    /**
     * 分类名称（中文）
     */
    private String cnClassName;

    /**
     * 分类logo
     */
    private String cnClassLogo;

    /**
     * 分类名称（英文）
     */
    private String usClassName;

    /**
     * 分类logo
     */
    private String usClassLogo;

    /**
     * 分类类型
     */
    private String classType;

    /**
     * 顺序
     */
    private Short indexNo;
    
    /**
     * 商品信息
     */
    private List<GoodsInfoVo> goods;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getCnClassName() {
        return cnClassName;
    }

    public void setCnClassName(String cnClassName) {
        this.cnClassName = cnClassName;
    }

    public String getCnClassLogo() {
        return cnClassLogo;
    }

    public void setCnClassLogo(String cnClassLogo) {
        this.cnClassLogo = cnClassLogo;
    }

    public String getUsClassName() {
        return usClassName;
    }

    public void setUsClassName(String usClassName) {
        this.usClassName = usClassName;
    }

    public String getUsClassLogo() {
        return usClassLogo;
    }

    public void setUsClassLogo(String usClassLogo) {
        this.usClassLogo = usClassLogo;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public Short getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Short indexNo) {
        this.indexNo = indexNo;
    }

    public List<GoodsInfoVo> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsInfoVo> goods) {
        this.goods = goods;
    }
    
}

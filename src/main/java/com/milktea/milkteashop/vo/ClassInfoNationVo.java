package com.milktea.milkteashop.vo;

import java.util.List;

public class ClassInfoNationVo {

    /**
     * 分类Id
     */
    private String classId;

    /**
     * 分类名称
     */
    private String ClassName;

    /**
     * 分类logo
     */
    private String ClassLogo;

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
    private List<GoodsInfoNationVo> goods;

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getClassLogo() {
        return ClassLogo;
    }

    public void setClassLogo(String classLogo) {
        ClassLogo = classLogo;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
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

    public List<GoodsInfoNationVo> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsInfoNationVo> goods) {
        this.goods = goods;
    }
    
}

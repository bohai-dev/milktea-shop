package com.milktea.milkteashop.vo;

import java.util.Date;

public class TeaClassInfoNationVo {
    private String classId;

    private String ClassName;

    private String ClassLogo;

    private String classType;

    private Short indexNo;

    private String deleteFlag;

    private Date updateTime;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

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

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType == null ? null : classType.trim();
    }

    public Short getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Short indexNo) {
        this.indexNo = indexNo;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
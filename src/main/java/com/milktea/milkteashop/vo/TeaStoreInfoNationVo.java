package com.milktea.milkteashop.vo;

import java.math.BigDecimal;
import java.util.Date;

public class TeaStoreInfoNationVo {

    private String storeNo;

    private String StoreName;

    private String StoreCity;

    private String StoreAddress;

    private String StorePicture;

    private String StoreIntroduction;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String storePhone;

    private String storeUserName;

    private String storePasswd;
    
    //店铺状态 0：非营业  1：营业
    private String storeStatus;

    //是否默认店铺 0：否 1：是
    private String isDefault;
    
    //是否外送 0：不支持 1：支持
    private String isSend;

    private String reserveBeginTime;

    private String reserveEndTime;

    private String deleteFlag;

    private Date updateTime;

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public String getStoreCity() {
        return StoreCity;
    }

    public void setStoreCity(String storeCity) {
        StoreCity = storeCity;
    }

    public String getStoreAddress() {
        return StoreAddress;
    }

    public void setStoreAddress(String storeAddress) {
        StoreAddress = storeAddress;
    }

    public String getStorePicture() {
        return StorePicture;
    }

    public void setStorePicture(String storePicture) {
        StorePicture = storePicture;
    }

    public String getStoreIntroduction() {
        return StoreIntroduction;
    }

    public void setStoreIntroduction(String storeIntroduction) {
        StoreIntroduction = storeIntroduction;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public String getStoreUserName() {
        return storeUserName;
    }

    public void setStoreUserName(String storeUserName) {
        this.storeUserName = storeUserName;
    }

    public String getStorePasswd() {
        return storePasswd;
    }

    public void setStorePasswd(String storePasswd) {
        this.storePasswd = storePasswd;
    }

    public String getStoreStatus() {
        return storeStatus;
    }

    public void setStoreStatus(String storeStatus) {
        this.storeStatus = storeStatus;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getIsSend() {
        return isSend;
    }

    public void setIsSend(String isSend) {
        this.isSend = isSend;
    }

    public String getReserveBeginTime() {
        return reserveBeginTime;
    }

    public void setReserveBeginTime(String reserveBeginTime) {
        this.reserveBeginTime = reserveBeginTime;
    }

    public String getReserveEndTime() {
        return reserveEndTime;
    }

    public void setReserveEndTime(String reserveEndTime) {
        this.reserveEndTime = reserveEndTime;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
}

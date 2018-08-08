package com.milktea.milkteashop.exception;

/**
 * 错误信息
 * @author caojia
 *
 */
public enum MilkTeaErrorConstant {
    
    SUCCESS("00000", "成功" , "success"),
    
    DATABASE_ACCESS_FAILURE("00001", "数据库访问失败" , "Database access failure"),
    
    PARAMETER_REQUIRED("00002", "请求参数不能为空", "Parameter required"),
    
    CN_CLASS_NAME_REQUIRED("00003", "分类名称（中文）不能为空", "Class name required"),
    
    US_CLASS_NAME_REQUIRED("00004", "分类名称（英文）不能为空", "Class name required"),
    
    CN_CLASS_LOGO_REQUIRED("00005", "分类logo（中文）不能为空", "Class logo required"),
    
    US_CLASS_LOGO_REQUIRED("00006", "分类logo（英文）不能为空", "Class logo required"),
    
    CLASS_TYPE_REQUIRED("00007", "分类类型不能为空", "Class type required"),
    
    CLASS_ID_REQUIRED("00008", "分类ID不能为空", "Class id required"),
    
    CN_ATTR_NAME_REQUIRED("00009", "商品属性名称（中文）不能为空", "Attribute name required"),
    
    US_ATTR_NAME_REQUIRED("00010", "商品属性名称（英文）不能为空", "Attribute name required"),
    
    ATTR_TYPE_REQUIRED("00011", "商品属性类型不能为空", "Attribute type required"),
    
    ATTR_ID_REQUIRED("00012", "商品属性ID不能为空", "Attribute id required"),
    
    CN_GOODS_NAME_REQUIRED("00013", "商品名称（中文）不能为空", "Goods name required"),
    
    US_GOODS_NAME_REQUIRED("00014", "商品名称（英文）不能为空", "Goods name required"),
    
    GOODS_ID_REQUIRED("00015", "商品ID不能为空", "Goods id required"),
    
    GOODS_CLASS_REQUIRED("00016", "商品分类不能为空", "Goods class required"),
    
    GOODS_ATTR_REQUIRED("00017", "商品属性不能为空", "Goods attribute required"),
    
    STORE_NO_REQUIRED("00018", "店铺编号不能为空", "StoreNo required"),
    
    CN_PROMOTION_NAME_REQUIRED("00019", "活动名称（中文）不能为空", "Promotion name required"),
    
    US_PROMOTION_NAME_REQUIRED("00020", "活动名称（英文）不能为空", "Promotion name required"),
    
    PROMOTION_ID_REQUIRED("00021", "活动ID不能为空", "Promotion id required"),
    
    CN_FIGURE_ADDRESS_REQUIRED("00022", "轮播图地址（中文）不能为空", "Figure address required"),
    
    US_FIGURE_ADDRESS_REQUIRED("00023", "轮播图地址（英文）不能为空", "Figure address required"),
    
    FIGURE_ID_REQUIRED("00024", "轮播图ID不能为空", "Figure id required"),
    
    USER_NAME_REQUIRED("00025", "用户名不能为空", "User name required"),
    
    PASSWORD_REQUIRED("00026", "密码不能为空", "Password required"),
    
    USER_NAME_OR_PASSWORD_WRONG("00027", "用户名或密码错误", "User name or password wrong"),
    
    CN_STORE_NAME_REQUIRED("00028", "店铺名称（中文）不能为空", "Store name required"),
    
    US_STORE_NAME_REQUIRED("00029", "店铺名称（英文）不能为空", "Store name required"),
    
    STORE_USER_NAME_REQUIRED("00030", "店铺管理员用户名不能为空", "Store user name required"),
    
    STORE_PASSWORD_REQUIRED("00031", "店铺管理员密码不能为空", "Store password required"),
    
    STORE_STATUS_REQUIRED("00032", "店铺状态不能为空", "Store status required"),
    
    CN_GOODS_NAME_EXISTS("00033", "商品名称(中文)重复", "Goods name already exists"),
    
    US_GOODS_NAME_EXISTS("00034", "商品名称(英文)重复", "Goods name already exists"),
    
    VOLUME_ILLEGAL("00035", "非法的数量", "Volume illegal"),
    
    LACK_OF_STOCK("00036", "库存不足", "Lack of stock"),
    
    GOODS_NOT_EXISTS("00037", "商品不存在", "Goods not exists"),
    
    GOODS_NOT_ON_SALE("00038", "商品不在售", "Goods not on sale"),
    
    GOODS_STOCK_UNMAINTAINED("00039", "商品库存未维护", "Goods stock unmaintained"),
    
    CN_STORE_NAME_EXISTS("00040", "店铺名称(中文)重复", "Store name already exists"),
    
    US_STORE_NAME_EXISTS("00041", "店铺名称(英文)重复", "Store name already exists"),
    
    CN_ATTR_NAME_EXISTS("00042", "属性名称(中文)重复", "Attr name already exists"),
    
    US_ATTR_NAME_EXISTS("00043", "属性名称(英文)重复", "Attr name already exists"),
    
    UNKNOW_EXCEPTION("10000", "程序内部异常" , "Something wrong with program");
    
    final String errorCode;
    
    final String cnErrorMsg;
    
    final String usErrorMsg;
    
    private MilkTeaErrorConstant(String errorCode , String cnErrorMsg, String usErrorMsg){
        this.errorCode = errorCode;
        this.cnErrorMsg = cnErrorMsg;
        this.usErrorMsg = usErrorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getCnErrorMsg() {
        return cnErrorMsg;
    }

    public String getUsErrorMsg() {
        return usErrorMsg;
    }

}

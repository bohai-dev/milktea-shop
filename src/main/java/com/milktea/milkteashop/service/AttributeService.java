package com.milktea.milkteashop.service;

import java.util.List;

import com.milktea.milkteashop.domain.TeaAttributesInfo;
import com.milktea.milkteashop.exception.MilkTeaException;

/**
 * 属性接口
 * @author caojia
 *
 */
public interface AttributeService {

    /**
     * 添加商品属性
     * @param attributesInfo
     * @throws MilkTeaException
     */
    public void addAttribute(TeaAttributesInfo attributesInfo) throws MilkTeaException;
    
    /**
     * 删除商品属性
     * @param attrId
     * @throws MilkTeaException
     */
    public void removeAttribute(String attrId) throws MilkTeaException;
    
    /**
     * 修改商品属性
     * @param attributesInfo
     * @throws MilkTeaException
     */
    public void modifyAttribute(TeaAttributesInfo attributesInfo) throws MilkTeaException;
    
    /**
     * 根据商品Id查询商品属性
     * @param goodsId
     * @return
     * @throws MilkTeaException
     */
    public List<TeaAttributesInfo> queryAttributesByGoodsId(String goodsId) throws MilkTeaException;
    
    /**
     * 查询所有属性
     * @return
     * @throws MilkTeaException
     */
    public List<TeaAttributesInfo> queryAttributesInfo() throws MilkTeaException;
    
    
}

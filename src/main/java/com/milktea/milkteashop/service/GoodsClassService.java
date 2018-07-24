package com.milktea.milkteashop.service;

import java.util.List;

import com.milktea.milkteashop.domain.TeaClassInfo;
import com.milktea.milkteashop.exception.MilkTeaException;

/**
 * 商品分类接口
 * @author caojia
 *
 */
public interface GoodsClassService {
    
    /**
     * 添加商品分类
     * @param classInfo
     * @throws MilkTeaException
     */
    public void addClass(TeaClassInfo classInfo) throws MilkTeaException;
    
    /**
     * 删除商品分类
     * @param classId
     * @throws MilkTeaException
     */
    public void removeClass(String classId) throws MilkTeaException;
    
    /**
     * 修改商品分类
     * @param classInfo
     * @throws MilkTeaException
     */
    public void modifyClass(TeaClassInfo classInfo) throws MilkTeaException;
    
    /**
     * 查询所有商品分类
     * @return
     * @throws MilkTeaException
     */
    public List<TeaClassInfo> queryClassInfo() throws MilkTeaException;
    
    /**
     * 根据分类Id查询商品分类
     * @param classId
     * @return
     * @throws MilkTeaException
     */
    public TeaClassInfo queryClassInfo(String classId) throws MilkTeaException;

}

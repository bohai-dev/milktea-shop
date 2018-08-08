package com.milktea.milkteashop.service;

import java.util.List;

import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.vo.ClassGoodsRequestVo;
import com.milktea.milkteashop.vo.ClassInfoVo;
import com.milktea.milkteashop.vo.DeductGoodsStockRequestVo;
import com.milktea.milkteashop.vo.GoodsInfoVo;
import com.milktea.milkteashop.vo.GoodsStockAndStatusRequestVo;

/**
 * 商品信息接口
 * @author caojia
 *
 */
public interface GoodsService {

    /**
     * 添加商品信息
     * @param goodsInfo
     * @throws MilkTeaException
     */
    public void addGoodsInfo(GoodsInfoVo infoVo) throws MilkTeaException;
    
    /**
     * 修改商品信息
     * @param infoVo
     * @throws MilkTeaException
     */
    public void modifyGoodsInfo(GoodsInfoVo infoVo) throws MilkTeaException;
    
    /**
     * 删除商品信息
     * @param goodsId
     * @throws MilkTeaException
     */
    public void removeGoodsInfo(String goodsId) throws MilkTeaException;
    
    /**
     * 根据店铺编号查询商品列表
     * @return
     * @throws MilkTeaException
     */
    public List<GoodsInfoVo> queryGoodsInfo(GoodsInfoVo infoVo) throws MilkTeaException;
    
    /**
     * 查询商品详情
     * @param goodsId
     * @return
     * @throws MilkTeaException
     */
    public GoodsInfoVo queryGoodsDetail(String goodsId) throws MilkTeaException;
    
    /**
     * 查询分类商品信息
     * @param requestVo
     * @return
     * @throws MilkTeaException
     */
    public List<ClassInfoVo> queryClassGoods(ClassGoodsRequestVo requestVo) throws MilkTeaException;
    
    /**
     * 更新商品状态和库存
     * @param infoVo
     * @throws MilkTeaException
     */
    public void updateGoodsStockAndStatus(GoodsStockAndStatusRequestVo requestVo) throws MilkTeaException;
    
    /**
     * 扣减商品库存
     * @param requestVo
     * @throws MilkTeaException
     */
    public void deductGoodsStock(DeductGoodsStockRequestVo requestVo) throws MilkTeaException;
}

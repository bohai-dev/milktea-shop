package com.milktea.milkteashop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.GoodsService;
import com.milktea.milkteashop.vo.ClassGoodsRequestVo;
import com.milktea.milkteashop.vo.ClassInfoVo;
import com.milktea.milkteashop.vo.GoodsInfoVo;
import com.milktea.milkteashop.vo.GoodsStockAndStatusRequestVo;
import com.milktea.milkteashop.vo.PageRequestVo;
import com.milktea.milkteashop.vo.PageResponseVo;
import com.milktea.milkteashop.vo.ResponseBody;
import com.milktea.milkteashop.vo.ResponseHeader;

@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    
    @RequestMapping(value="saveGoods", method=RequestMethod.POST)
    public ResponseHeader saveGoods(GoodsInfoVo infoVo) throws MilkTeaException{
        ResponseHeader header = new ResponseHeader();
        this.goodsService.addGoodsInfo(infoVo);
        return header;
    }
    
    @RequestMapping(value="modiftGoods", method=RequestMethod.POST)
    public ResponseHeader modiftGoods(GoodsInfoVo infoVo) throws MilkTeaException{
        ResponseHeader header = new ResponseHeader();
        this.goodsService.modifyGoodsInfo(infoVo);
        return header;
    }
    
    @RequestMapping(value="removeGoods/{goodsId}", method=RequestMethod.DELETE)
    public ResponseHeader removeGoods(@PathVariable String goodsId) throws MilkTeaException{
        ResponseHeader header = new ResponseHeader();
        this.goodsService.removeGoodsInfo(goodsId);
        return header;
    }
    
    /**
     * 分页查询商品信息
     * @param requestVo
     * @return
     */
    @RequestMapping(value="queryPagedGoodsInfo", method=RequestMethod.GET)
    public PageResponseVo<GoodsInfoVo> queryPagedGoodsInfo(PageRequestVo<GoodsInfoVo> requestVo) throws MilkTeaException{
        
        return null;
    }
    
    /**
     * 查询商品信息
     * @param infoVo
     * @return
     */
    @RequestMapping(value="queryGoodsInfo", method=RequestMethod.GET)
    public ResponseBody<List<GoodsInfoVo>> queryGoodsInfo(GoodsInfoVo infoVo) throws MilkTeaException{
        ResponseBody<List<GoodsInfoVo>> responseBody = new ResponseBody<>();
        List<GoodsInfoVo> data = this.goodsService.queryGoodsInfo(infoVo);
        responseBody.setData(data);
        return null;
    }
    
    /**
     * 查询分类商品
     * @param requestVo
     * @return
     * @throws MilkTeaException
     */
    @RequestMapping(value="queryClassGoods", method=RequestMethod.GET)
    public ResponseBody<List<ClassInfoVo>> queryClassGoods(ClassGoodsRequestVo requestVo) throws MilkTeaException{
        ResponseBody<List<ClassInfoVo>> responseBody = new ResponseBody<>();
        List<ClassInfoVo> data = this.goodsService.queryClassGoods(requestVo);
        responseBody.setData(data);
        return responseBody;
    }
    
    /**
     * 查询商品详情
     * @param goodsId
     * @return
     * @throws MilkTeaException
     */
    @RequestMapping(value="queryGoodsDetail/{goodsId}", method=RequestMethod.GET)
    public ResponseBody<GoodsInfoVo> queryGoodsDetail(@PathVariable String goodsId) throws MilkTeaException{
        ResponseBody<GoodsInfoVo> responseBody = new ResponseBody<>(); 
        responseBody.setData(this.goodsService.queryGoodsDetail(goodsId));
        return responseBody;
    }
    
    /**
     * 更新商品库存和状态
     * @param requestVo
     * @return
     * @throws MilkTeaException
     */
    @RequestMapping(value="updateGoodsStockAndStatus", method=RequestMethod.POST)
    public ResponseHeader updateGoodsStockAndStatus(GoodsStockAndStatusRequestVo requestVo) throws MilkTeaException{
        ResponseHeader header = new ResponseHeader();
        this.goodsService.updateGoodsStockAndStatus(requestVo);
        return header;
    }
}

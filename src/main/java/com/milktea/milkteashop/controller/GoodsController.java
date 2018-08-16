package com.milktea.milkteashop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.GoodsService;
import com.milktea.milkteashop.vo.ClassGoodsRequestVo;
import com.milktea.milkteashop.vo.ClassInfoNationVo;
import com.milktea.milkteashop.vo.ClassInfoVo;
import com.milktea.milkteashop.vo.DeductGoodsStockRequestVo;
import com.milktea.milkteashop.vo.GoodsInfoVo;
import com.milktea.milkteashop.vo.GoodsStockAndStatusRequestVo;
import com.milktea.milkteashop.vo.PageRequestVo;
import com.milktea.milkteashop.vo.PageResponseVo;
import com.milktea.milkteashop.vo.ResponseBody;
import com.milktea.milkteashop.vo.ResponseHeader;

@RestController
@CrossOrigin
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    
    @RequestMapping(value="saveGoods", method=RequestMethod.POST)
    public ResponseHeader saveGoods(@RequestBody(required=false) GoodsInfoVo infoVo) throws MilkTeaException{
        ResponseHeader header = new ResponseHeader();
        this.goodsService.addGoodsInfo(infoVo);
        return header;
    }
    
    @RequestMapping(value="modiftGoods", method=RequestMethod.POST)
    public ResponseHeader modiftGoods(@RequestBody(required=false) GoodsInfoVo infoVo) throws MilkTeaException{
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
    @RequestMapping(value="queryPagedGoodsInfo", method=RequestMethod.POST)
    public PageResponseVo<GoodsInfoVo> queryPagedGoodsInfo(PageRequestVo<GoodsInfoVo> requestVo) throws MilkTeaException{
        
        return null;
    }
    
    /**
     * 查询商品信息
     * @param infoVo
     * @return
     */
    @RequestMapping(value="queryGoodsInfo", method=RequestMethod.POST)
    public ResponseBody<List<GoodsInfoVo>> queryGoodsInfo(@RequestBody GoodsInfoVo infoVo) throws MilkTeaException{
        ResponseBody<List<GoodsInfoVo>> responseBody = new ResponseBody<>();
        List<GoodsInfoVo> data = this.goodsService.queryGoodsInfo(infoVo);
        responseBody.setData(data);
        return responseBody;
    }
    
    /**
     * 查询分类商品
     * @param requestVo
     * @return
     * @throws MilkTeaException
     */
    @RequestMapping(value="queryClassGoods", method=RequestMethod.POST)
    public ResponseBody<List<ClassInfoVo>> queryClassGoods(@RequestBody ClassGoodsRequestVo requestVo) throws MilkTeaException{
        ResponseBody<List<ClassInfoVo>> responseBody = new ResponseBody<>();
        List<ClassInfoVo> data = this.goodsService.queryClassGoods(requestVo);
        responseBody.setData(data);
        return responseBody;
    }
    
    /**
     * 查询分类商品(支持国际化)
     * @param requestVo
     * @return
     * @throws MilkTeaException
     */
    @RequestMapping(value="queryClassGoodsNational", method=RequestMethod.POST)
    public ResponseBody<List<ClassInfoNationVo>> queryClassGoodsNational(@RequestBody ClassGoodsRequestVo requestVo) throws MilkTeaException{
        ResponseBody<List<ClassInfoNationVo>> responseBody = new ResponseBody<>();
        responseBody.setData(this.goodsService.queryClassGoodsNation(requestVo));
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
    public ResponseHeader updateGoodsStockAndStatus(@RequestBody GoodsStockAndStatusRequestVo requestVo) throws MilkTeaException{
        ResponseHeader header = new ResponseHeader();
        this.goodsService.updateGoodsStockAndStatus(requestVo);
        return header;
    }
    
    /**
     * 扣减商品库存
     * @param requestVo
     * @return
     * @throws MilkTeaException
     */
    @RequestMapping(value="deductGoodsStock", method=RequestMethod.POST)
    public ResponseHeader deductGoodsStock(@RequestBody DeductGoodsStockRequestVo requestVo) throws MilkTeaException{
        ResponseHeader header = new ResponseHeader();
        this.goodsService.deductGoodsStock(requestVo);
        return header;
    }


}

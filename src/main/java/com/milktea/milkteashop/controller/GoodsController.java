package com.milktea.milkteashop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.GoodsService;
import com.milktea.milkteashop.vo.GoodsInfoVo;
import com.milktea.milkteashop.vo.ResponseHeader;

@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    
    @RequestMapping(value="saveGoods", method=RequestMethod.POST)
    public ResponseHeader saveGoods(GoodsInfoVo infoVo){
        ResponseHeader header = new ResponseHeader();
        try {
            this.goodsService.addGoodsInfo(infoVo);
        } catch (MilkTeaException e) {
            header.setRspCode(e.getErrorCode());
            header.setCnErrorMsg(e.getCnErrorMsg());
            header.setUsErrorMsg(e.getUsErrorMsg());
        }
        return header;
    }
    
    @RequestMapping(value="modiftGoods", method=RequestMethod.POST)
    public ResponseHeader modiftGoods(GoodsInfoVo infoVo){
        ResponseHeader header = new ResponseHeader();
        try {
            this.goodsService.modifyGoodsInfo(infoVo);
        } catch (MilkTeaException e) {
            header.setRspCode(e.getErrorCode());
            header.setCnErrorMsg(e.getCnErrorMsg());
            header.setUsErrorMsg(e.getUsErrorMsg());
        }
        return header;
    }
    
    @RequestMapping(value="removeGoods/{goodsId}", method=RequestMethod.DELETE)
    public ResponseHeader removeGoods(@PathVariable String goodsId){
        ResponseHeader header = new ResponseHeader();
        try {
            this.goodsService.removeGoodsInfo(goodsId);
        } catch (MilkTeaException e) {
            header.setRspCode(e.getErrorCode());
            header.setCnErrorMsg(e.getCnErrorMsg());
            header.setUsErrorMsg(e.getUsErrorMsg());
        }
        return header;
    }
}

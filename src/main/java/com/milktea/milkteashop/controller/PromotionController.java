package com.milktea.milkteashop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.PromotionService;
import com.milktea.milkteashop.vo.PromotionVo;
import com.milktea.milkteashop.vo.ResponseBody;
import com.milktea.milkteashop.vo.ResponseHeader;

@RestController
public class PromotionController {
    
    @Autowired
    private PromotionService promotionService;
    
    @RequestMapping(value="savePromotion", method=RequestMethod.POST)
    public ResponseHeader savePromotion(PromotionVo promotionVo) throws MilkTeaException{
        ResponseHeader header = new ResponseHeader();
        this.promotionService.addPromotion(promotionVo);
        return header;
    }
    
    @RequestMapping(value="removePromotion/{promotionId}", method=RequestMethod.DELETE)
    public ResponseHeader removePromotion(@PathVariable String promotionId) throws MilkTeaException{
        ResponseHeader header = new ResponseHeader();
        this.promotionService.removePromotion(promotionId);
        return header;
    }
    
    @RequestMapping(value="modifyPromotion", method=RequestMethod.POST)
    public ResponseHeader modifyPromotion(PromotionVo promotionVo) throws MilkTeaException{
        ResponseHeader header = new ResponseHeader();
        this.promotionService.modifyPromotion(promotionVo);
        return header;
    }
    
    @RequestMapping(value="queryPromotion", method=RequestMethod.POST)
    public ResponseBody<List<PromotionVo>> queryPromotion(PromotionVo promotionVo) throws MilkTeaException{
        ResponseBody<List<PromotionVo>> responseBody = new ResponseBody<>();
        responseBody.setData(this.promotionService.queryPromotions());
        return responseBody;
    }

}

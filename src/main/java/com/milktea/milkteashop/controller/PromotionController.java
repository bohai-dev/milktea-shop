package com.milktea.milkteashop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.PromotionService;
import com.milktea.milkteashop.vo.PromotionNationVo;
import com.milktea.milkteashop.vo.PromotionVo;
import com.milktea.milkteashop.vo.QueryEffectPromotionRequstVo;
import com.milktea.milkteashop.vo.QueryPromotionByStoreNoNationRequestVo;
import com.milktea.milkteashop.vo.ResponseBody;
import com.milktea.milkteashop.vo.ResponseHeader;

@RestController
@CrossOrigin
public class PromotionController {
    
    @Autowired
    private PromotionService promotionService;
    
    @RequestMapping(value="savePromotion", method=RequestMethod.POST)
    public ResponseHeader savePromotion(@RequestBody(required=false) PromotionVo promotionVo) throws MilkTeaException{
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
    public ResponseHeader modifyPromotion(@RequestBody(required=false) PromotionVo promotionVo) throws MilkTeaException{
        ResponseHeader header = new ResponseHeader();
        this.promotionService.modifyPromotion(promotionVo);
        return header;
    }
    
    @RequestMapping(value="queryPromotion", method=RequestMethod.POST)
    public ResponseBody<List<PromotionVo>> queryPromotion(@RequestBody(required=false) PromotionVo promotionVo) throws MilkTeaException{
        ResponseBody<List<PromotionVo>> responseBody = new ResponseBody<>();
        responseBody.setData(this.promotionService.queryPromotions());
        return responseBody;
    }
    
    /**
     * 根据店铺编号查询有效的活动
     * @param storeNo
     * @return
     * @throws MilkTeaException
     */
    @RequestMapping(value="queryPromotionByStoreNo/{storeNo}", method=RequestMethod.GET)
    public ResponseBody<List<PromotionVo>> queryPromotionByStoreNo(@PathVariable String storeNo) throws MilkTeaException{
        ResponseBody<List<PromotionVo>> responseBody = new ResponseBody<>();
        responseBody.setData(this.promotionService.queryPromotions(storeNo));
        return responseBody;
    }
    
    /**
     * 根据店铺编号查询有效的活动(支持国际化)
     * @param storeNo
     * @return
     * @throws MilkTeaException
     */
    @RequestMapping(value="queryPromotionByStoreNoNation", method=RequestMethod.POST)
    public ResponseBody<List<PromotionNationVo>> queryPromotionByStoreNoNation(@RequestBody(required=false) QueryPromotionByStoreNoNationRequestVo requestVo) throws MilkTeaException{
        ResponseBody<List<PromotionNationVo>> responseBody = new ResponseBody<>();
        responseBody.setData(this.promotionService.queryPromotionsNation(requestVo));
        return responseBody;
    }
    
    /**
     * 根据店铺编号和活动ID查询有效活动详情
     * @param promotionId
     * @param storeNo
     * @return
     * @throws MilkTeaException
     */
    @RequestMapping(value="queryEffectPromotion", method=RequestMethod.POST)
    public ResponseBody<PromotionVo> queryEffectPromotion(@RequestBody(required=false) QueryEffectPromotionRequstVo requstVo) throws MilkTeaException{
        ResponseBody<PromotionVo> responseBody = new ResponseBody<>();
        responseBody.setData(this.promotionService.queryPromotion(requstVo.getPromotionId(), requstVo.getStoreNo()));
        return responseBody;
    }
}

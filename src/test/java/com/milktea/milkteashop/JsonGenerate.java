package com.milktea.milkteashop;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.milktea.milkteashop.domain.TeaCarouselFigure;
import com.milktea.milkteashop.vo.DeductGoodsStockRequestVo;
import com.milktea.milkteashop.vo.PromotionVo;
import com.milktea.milkteashop.vo.ResponseBody;

public class JsonGenerate {
    
    public static void main(String[] args) {
        
        
        ResponseBody<PromotionVo> requestVo = new ResponseBody<PromotionVo>();
        PromotionVo promotionVo = new PromotionVo();
        requestVo.setData(promotionVo);
        
        //TeaCarouselFigure responseBody = new TeaCarouselFigure();
        System.out.println(JSON.toJSONString(requestVo,SerializerFeature.WriteMapNullValue));
    }

}

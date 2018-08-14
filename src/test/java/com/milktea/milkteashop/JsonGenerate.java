package com.milktea.milkteashop;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.milktea.milkteashop.domain.TeaCarouselFigure;
import com.milktea.milkteashop.vo.DeductGoodsStockRequestVo;
import com.milktea.milkteashop.vo.OrderDetailsNationVo;
import com.milktea.milkteashop.vo.OrderNationVo;
import com.milktea.milkteashop.vo.PageRequestVo;
import com.milktea.milkteashop.vo.PageResponseVo;
import com.milktea.milkteashop.vo.PromotionVo;
import com.milktea.milkteashop.vo.QueryOrdersRequestVo;
import com.milktea.milkteashop.vo.ResponseBody;
import com.milktea.milkteashop.vo.TeaAttributesInfoNationVo;

public class JsonGenerate {
    
    public static void main(String[] args) {
        
        
        ResponseBody<OrderNationVo> requestVo = new ResponseBody<>();
        OrderNationVo nationVo = new OrderNationVo();
        List<OrderDetailsNationVo> orderDetails = new ArrayList<>();
        OrderDetailsNationVo detailsNationVo = new OrderDetailsNationVo();
        List<TeaAttributesInfoNationVo> attrs = new ArrayList<>();
        TeaAttributesInfoNationVo attributesInfoNationVo = new TeaAttributesInfoNationVo();
        attrs.add(attributesInfoNationVo);
        detailsNationVo.setAttrs(attrs);
        orderDetails.add(detailsNationVo);
        nationVo.setOrderDetails(orderDetails);
        List<OrderNationVo> l = new ArrayList<>();
        l.add(nationVo);
        requestVo.setData(nationVo);
        //TeaCarouselFigure responseBody = new TeaCarouselFigure();
        
        System.out.println(JSON.toJSONString(nationVo,SerializerFeature.WriteMapNullValue));
    }

}

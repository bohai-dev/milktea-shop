package com.milktea.milkteashop;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.milktea.milkteashop.domain.TeaCarouselFigure;
import com.milktea.milkteashop.domain.TeaContact;
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
        
        
        ResponseBody<List<TeaContact>> requestVo = new ResponseBody<>();
        List<TeaContact> list = new ArrayList<>();
        TeaContact contact = new TeaContact();
        list.add(contact);
        //TeaCarouselFigure responseBody = new TeaCarouselFigure();
        requestVo.setData(list);
        
        TeaContact nationVo = new TeaContact();
        System.out.println(JSON.toJSONString(requestVo,SerializerFeature.WriteMapNullValue));
    }

}

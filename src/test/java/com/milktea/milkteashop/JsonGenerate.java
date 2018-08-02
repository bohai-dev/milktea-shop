package com.milktea.milkteashop;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.milktea.milkteashop.domain.TeaCarouselFigure;
import com.milktea.milkteashop.vo.PromotionVo;
import com.milktea.milkteashop.vo.ResponseBody;

public class JsonGenerate {
    
    public static void main(String[] args) {
        
        ResponseBody<List<TeaCarouselFigure>> responseBody = new ResponseBody<>();
        List<TeaCarouselFigure> list = new ArrayList<>();
        TeaCarouselFigure info = new TeaCarouselFigure();
        list.add(info);
        responseBody.setData(list);
        
        //TeaCarouselFigure responseBody = new TeaCarouselFigure();
        System.out.println(JSON.toJSONString(responseBody,SerializerFeature.WriteMapNullValue));
    }

}

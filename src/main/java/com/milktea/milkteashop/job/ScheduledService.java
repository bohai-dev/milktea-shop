package com.milktea.milkteashop.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.OrderService;
import com.milktea.milkteashop.vo.OrderNationVo;
import com.milktea.milkteashop.vo.QueryOrdersRequestVo;
import com.milktea.milkteashop.websocket.WebsocketHandler;

@Service
public class ScheduledService {
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    WebsocketHandler websocketHandler;
    
    static Logger log = LoggerFactory.getLogger(ScheduledService.class);
    
    @Scheduled(cron = "0 0/1 * * * *")
    public void scheduled(){
        
        QueryOrdersRequestVo requestVo = new QueryOrdersRequestVo();
        requestVo.setOrderType("0");
        requestVo.setPayStatus("1");
        requestVo.setOrderStatus("0");
        List<OrderNationVo> list = null;
        try {
            list = this.orderService.queryDueOrder(requestVo);
        } catch (MilkTeaException e) {
            log.error("查询预约单失败",e);
        }
        
        if(list == null || list.size() < 1){
            return;
        }
        for (OrderNationVo orderNationVo : list) {
            orderNationVo.setMessageType("4");
            websocketHandler.sendMessage(orderNationVo.getStoreNo(), JSON.toJSONString(orderNationVo));
        }
        log.info("=====>>>>>使用cron  {}{}",System.currentTimeMillis(),"每隔一分钟执行");
    }
    
    /*@Scheduled(fixedRate = 5000)
    public void scheduled1() {
        log.info("=====>>>>>使用fixedRate{}", System.currentTimeMillis());
    }
    @Scheduled(fixedDelay = 5000)
    public void scheduled2() {
        log.info("=====>>>>>fixedDelay{}",System.currentTimeMillis());
    }*/
}

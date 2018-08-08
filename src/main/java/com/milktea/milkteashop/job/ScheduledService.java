package com.milktea.milkteashop.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledService {
    
    static Logger log = LoggerFactory.getLogger(ScheduledService.class);
    
    @Scheduled(cron = "0 0/1 * * * *")
    public void scheduled(){
        
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

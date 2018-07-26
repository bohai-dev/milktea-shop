package com.milktea.milkteashop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.milktea.milkteashop.domain.TeaStoreInfo;
import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.StoreService;
import com.milktea.milkteashop.vo.ResponseBody;
import com.milktea.milkteashop.vo.ResponseHeader;

@RestController
public class StoreController {
    
    @Autowired
    private StoreService storeService;
    
    @RequestMapping(value="saveStoreInfo", method=RequestMethod.POST)
    public ResponseHeader saveStoreInfo(TeaStoreInfo storeInfo) throws MilkTeaException{
        
        ResponseHeader header = new ResponseHeader();
        this.storeService.addStoreInfo(storeInfo);
        return header;
    }
    
    @RequestMapping(value="removeStoreInfo/{storeNo}", method=RequestMethod.DELETE)
    public ResponseHeader removeStoreInfo(@PathVariable String storeNo) throws MilkTeaException{
        ResponseHeader header = new ResponseHeader();
        this.storeService.removeStore(storeNo);
        return header;
    }
    
    @RequestMapping(value="modifyStoreInfo", method=RequestMethod.POST)
    public ResponseHeader modifyStoreInfo(TeaStoreInfo storeInfo) throws MilkTeaException{
        
        ResponseHeader header = new ResponseHeader();
        this.storeService.modifyStoreInfo(storeInfo);
        return header;
    }
    
    @RequestMapping(value="queryStores" ,method=RequestMethod.GET)
    public ResponseBody<List<TeaStoreInfo>> queryStores(TeaStoreInfo storeInfo) throws MilkTeaException{
        
        ResponseBody<List<TeaStoreInfo>> responseBody = new ResponseBody<>();
        responseBody.setData(this.storeService.queryStoreInfo());
        return responseBody;
        
    }

}

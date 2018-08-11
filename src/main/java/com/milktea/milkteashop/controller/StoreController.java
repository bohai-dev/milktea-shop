package com.milktea.milkteashop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.milktea.milkteashop.domain.TeaStoreInfo;
import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.StoreService;
import com.milktea.milkteashop.vo.ResponseBody;
import com.milktea.milkteashop.vo.ResponseHeader;
import com.milktea.milkteashop.vo.TeaStoreInfoNationVo;

@RestController
@CrossOrigin
public class StoreController {
    
    @Autowired
    private StoreService storeService;
    
    @RequestMapping(value="saveStoreInfo", method=RequestMethod.POST)
    public ResponseHeader saveStoreInfo(@RequestBody TeaStoreInfo storeInfo) throws MilkTeaException{
        
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
    public ResponseHeader modifyStoreInfo(@RequestBody TeaStoreInfo storeInfo) throws MilkTeaException{
        
        ResponseHeader header = new ResponseHeader();
        this.storeService.modifyStoreInfo(storeInfo);
        return header;
    }
    
    @RequestMapping(value="queryStores" ,method=RequestMethod.GET)
    public ResponseBody<List<TeaStoreInfo>> queryStores(TeaStoreInfo storeInfo) throws MilkTeaException{
        
        ResponseBody<List<TeaStoreInfo>> responseBody = new ResponseBody<>();
        responseBody.setData(this.storeService.queryStoreInfo(storeInfo));
        return responseBody;
    }
    
    @RequestMapping(value="queryStores/{lang}", method=RequestMethod.GET)
    public ResponseBody<List<TeaStoreInfoNationVo>> queryStores(@PathVariable String lang) throws MilkTeaException{
        ResponseBody<List<TeaStoreInfoNationVo>> responseBody = new ResponseBody<>();
        responseBody.setData(this.storeService.queryStoreInfoByLang(lang));
        return responseBody;
    }
    
    @RequestMapping(value="queryStoreInfo/{storeNo}",method=RequestMethod.GET)
    public ResponseBody<TeaStoreInfo> queryStoreInfo(@PathVariable String storeNo)  throws MilkTeaException{
        ResponseBody<TeaStoreInfo> responseBody = new ResponseBody<>();
        responseBody.setData(this.storeService.queryStoreInfo(storeNo));
        return responseBody;
    }

}

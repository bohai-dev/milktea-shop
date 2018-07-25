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
    public ResponseHeader saveStoreInfo(TeaStoreInfo storeInfo){
        
        ResponseHeader header = new ResponseHeader();
        try {
            this.storeService.addStoreInfo(storeInfo);
        } catch (MilkTeaException e) {
            header.setRspCode(e.getErrorCode());
            header.setCnErrorMsg(e.getCnErrorMsg());
            header.setUsErrorMsg(e.getUsErrorMsg());
        }
        return header;
    }
    
    @RequestMapping(value="removeStoreInfo/{storeNo}", method=RequestMethod.DELETE)
    public ResponseHeader removeStoreInfo(@PathVariable String storeNo){
        ResponseHeader header = new ResponseHeader();
        try {
            this.storeService.removeStore(storeNo);
        } catch (MilkTeaException e) {
            header.setRspCode(e.getErrorCode());
            header.setCnErrorMsg(e.getCnErrorMsg());
            header.setUsErrorMsg(e.getUsErrorMsg());
        }
        return header;
    }
    
    @RequestMapping(value="modifyStoreInfo", method=RequestMethod.POST)
    public ResponseHeader modifyStoreInfo(TeaStoreInfo storeInfo){
        
        ResponseHeader header = new ResponseHeader();
        try {
            this.storeService.modifyStoreInfo(storeInfo);
        } catch (MilkTeaException e) {
            header.setRspCode(e.getErrorCode());
            header.setCnErrorMsg(e.getCnErrorMsg());
            header.setUsErrorMsg(e.getUsErrorMsg());
        }
        return header;
    }
    
    @RequestMapping(value="queryStores" ,method=RequestMethod.GET)
    public ResponseBody<List<TeaStoreInfo>> queryStores(TeaStoreInfo storeInfo){
        
        ResponseBody<List<TeaStoreInfo>> responseBody = new ResponseBody<>();
        try {
            responseBody.setData(this.storeService.queryStoreInfo());
        } catch (MilkTeaException e) {
            responseBody.setRspCode(e.getErrorCode());
            responseBody.setCnErrorMsg(e.getCnErrorMsg());
            responseBody.setUsErrorMsg(e.getUsErrorMsg());
        }
        return responseBody;
        
    }

}

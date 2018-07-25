package com.milktea.milkteashop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.milktea.milkteashop.domain.TeaAttributesInfo;
import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.AttributeService;
import com.milktea.milkteashop.vo.ResponseBody;
import com.milktea.milkteashop.vo.ResponseHeader;

@RestController
public class AttributeController {

    @Autowired
    private AttributeService attributeService;
    
    @RequestMapping(value="saveAttribute", method=RequestMethod.POST)
    public ResponseHeader saveAttribute(TeaAttributesInfo attributesInfo){
        ResponseHeader header = new ResponseHeader();
        try {
            this.attributeService.addAttribute(attributesInfo);
        } catch (MilkTeaException e) {
            header.setRspCode(e.getErrorCode());
            header.setCnErrorMsg(e.getCnErrorMsg());
            header.setUsErrorMsg(e.getUsErrorMsg());
        }
        return header;
    }
    
    @RequestMapping(value="modifyAttribute", method=RequestMethod.POST)
    public ResponseHeader modifyAttribute(TeaAttributesInfo attributesInfo){
        ResponseHeader header = new ResponseHeader();
        try {
            this.attributeService.modifyAttribute(attributesInfo);
        } catch (MilkTeaException e) {
            header.setRspCode(e.getErrorCode());
            header.setCnErrorMsg(e.getCnErrorMsg());
            header.setUsErrorMsg(e.getUsErrorMsg());
        }
        return header;
    }
    
    @RequestMapping(value="removeAttribute/{attrId}", method=RequestMethod.DELETE)
    public ResponseHeader removeAttribute(@PathVariable String attrId){
        ResponseHeader header = new ResponseHeader();
        try {
            this.attributeService.removeAttribute(attrId);
        } catch (MilkTeaException e) {
            header.setRspCode(e.getErrorCode());
            header.setCnErrorMsg(e.getCnErrorMsg());
            header.setUsErrorMsg(e.getUsErrorMsg());
        }
        return header;
    }
    
    @RequestMapping(value="queryAttributes", method=RequestMethod.GET)
    public ResponseBody<List<TeaAttributesInfo>> queryAttributes(){
        ResponseBody<List<TeaAttributesInfo>> responseBody = new ResponseBody<>();
        try {
            responseBody.setData(this.attributeService.queryAttributesInfo());
        } catch (MilkTeaException e) {
            responseBody.setRspCode(e.getErrorCode());
            responseBody.setCnErrorMsg(e.getCnErrorMsg());
            responseBody.setUsErrorMsg(e.getUsErrorMsg());
        }
        return responseBody;
    }
}

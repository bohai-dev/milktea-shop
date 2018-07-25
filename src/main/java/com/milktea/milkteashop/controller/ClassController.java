package com.milktea.milkteashop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.milktea.milkteashop.domain.TeaClassInfo;
import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.GoodsClassService;
import com.milktea.milkteashop.vo.ResponseBody;
import com.milktea.milkteashop.vo.ResponseHeader;

@RestController
public class ClassController {
    
    @Autowired
    private GoodsClassService classService;
    
    @RequestMapping(value="saveClass", method=RequestMethod.POST)
    public ResponseHeader saveClass(TeaClassInfo classInfo){
        ResponseHeader header = new ResponseHeader();
        try {
            this.classService.addClass(classInfo);
        } catch (MilkTeaException e) {
            header.setRspCode(e.getErrorCode());
            header.setCnErrorMsg(e.getCnErrorMsg());
            header.setUsErrorMsg(e.getUsErrorMsg());
        }
        return header;
    }
    
    @RequestMapping(value="modifyClass", method=RequestMethod.POST)
    public ResponseHeader modifyClass(TeaClassInfo classInfo){
        ResponseHeader header = new ResponseHeader();
        try {
            this.classService.modifyClass(classInfo);
        } catch (MilkTeaException e) {
            header.setRspCode(e.getErrorCode());
            header.setCnErrorMsg(e.getCnErrorMsg());
            header.setUsErrorMsg(e.getUsErrorMsg());
        }
        return header;
    }
    
    @RequestMapping(value="removeClass/{classId}", method=RequestMethod.DELETE)
    public ResponseHeader removeClass(@PathVariable String classId){
        ResponseHeader header = new ResponseHeader();
        try {
            this.classService.removeClass(classId);
        } catch (MilkTeaException e) {
            header.setRspCode(e.getErrorCode());
            header.setCnErrorMsg(e.getCnErrorMsg());
            header.setUsErrorMsg(e.getUsErrorMsg());
        }
        return header;
    }
    
    @RequestMapping(value="queryClasses", method=RequestMethod.GET)
    public ResponseBody<List<TeaClassInfo>> queryClasses(){
        ResponseBody<List<TeaClassInfo>> responseBody = new ResponseBody<>();
        try {
            responseBody.setData(this.classService.queryClassInfo());
        } catch (MilkTeaException e) {
            responseBody.setRspCode(e.getErrorCode());
            responseBody.setCnErrorMsg(e.getCnErrorMsg());
            responseBody.setUsErrorMsg(e.getUsErrorMsg());
        }
        return responseBody;
    }

}

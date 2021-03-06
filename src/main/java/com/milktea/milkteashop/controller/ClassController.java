package com.milktea.milkteashop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.milktea.milkteashop.domain.TeaClassInfo;
import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.GoodsClassService;
import com.milktea.milkteashop.vo.ResponseBody;
import com.milktea.milkteashop.vo.ResponseHeader;

@RestController
@CrossOrigin
public class ClassController {
    
    @Autowired
    private GoodsClassService classService;
    
    @RequestMapping(value="saveClass", method=RequestMethod.POST)
    public ResponseHeader saveClass(@RequestBody TeaClassInfo classInfo) throws MilkTeaException{
        ResponseHeader header = new ResponseHeader();
        this.classService.addClass(classInfo);
        return header;
    }
    
    @RequestMapping(value="modifyClass", method=RequestMethod.POST)
    public ResponseHeader modifyClass(@RequestBody TeaClassInfo classInfo) throws MilkTeaException{
        ResponseHeader header = new ResponseHeader();
        this.classService.modifyClass(classInfo);
        return header;
    }
    
    @RequestMapping(value="removeClass/{classId}", method=RequestMethod.DELETE)
    public ResponseHeader removeClass(@PathVariable String classId) throws MilkTeaException{
        ResponseHeader header = new ResponseHeader();
        this.classService.removeClass(classId);
        return header;
    }
    
    @RequestMapping(value="queryClasses", method=RequestMethod.GET)
    public ResponseBody<List<TeaClassInfo>> queryClasses() throws MilkTeaException{
        ResponseBody<List<TeaClassInfo>> responseBody = new ResponseBody<>();
        responseBody.setData(this.classService.queryClassInfo());
        return responseBody;
    }

    @RequestMapping(value="queryClassInfo/{classId}", method=RequestMethod.GET)
    public ResponseBody<TeaClassInfo> queryClassInfo(@PathVariable String classId) throws MilkTeaException{
        ResponseBody<TeaClassInfo> responseBody = new ResponseBody<>();
        responseBody.setData(this.classService.queryClassInfo(classId));
        return responseBody;
    }
}

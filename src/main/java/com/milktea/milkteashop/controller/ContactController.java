package com.milktea.milkteashop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.milktea.milkteashop.domain.TeaContact;
import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.ContactService;
import com.milktea.milkteashop.vo.ResponseBody;
import com.milktea.milkteashop.vo.ResponseHeader;

@RestController
public class ContactController {
    
    @Autowired
    private ContactService contactService;
    
    @RequestMapping(value="saveContact", method=RequestMethod.POST)
    public ResponseHeader saveContact(@RequestBody(required=false)TeaContact contact) throws MilkTeaException{
        this.contactService.save(contact);
        return new ResponseHeader();
    }
    
    
    @RequestMapping(value="queryContact", method=RequestMethod.GET)
    public ResponseBody<List<TeaContact>> queryContact() throws MilkTeaException{
        ResponseBody<List<TeaContact>> responseBody = new ResponseBody<>();
        responseBody.setData(this.contactService.queryContacts());
        return responseBody;
    }

}

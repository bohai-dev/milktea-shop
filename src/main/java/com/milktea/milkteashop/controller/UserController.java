package com.milktea.milkteashop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.milktea.milkteashop.domain.TeaUserInfo;
import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.UserService;
import com.milktea.milkteashop.vo.PageRequestVo;
import com.milktea.milkteashop.vo.PageResponseVo;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    
    @RequestMapping(value="queryUsersPage" ,method=RequestMethod.POST)
    private PageResponseVo<TeaUserInfo> queryUsersPage(@RequestBody(required=false) PageRequestVo<TeaUserInfo> requestVo) throws MilkTeaException{
        
        return this.userService.queryUsersPage(requestVo);
        
    }
}

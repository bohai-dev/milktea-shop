package com.milktea.milkteashop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.milktea.milkteashop.exception.MilkTeaException;
import com.milktea.milkteashop.service.UserService;
import com.milktea.milkteashop.vo.ResponseBody;
import com.milktea.milkteashop.vo.UserLoginRequestVo;
import com.milktea.milkteashop.vo.UserLoginResponseVo;

@RestController
public class LoginController {

    static Logger logger = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    private UserService userService;
    
    /**
     * 用户登录
     * @param requestVo
     * @return
     * @throws MilkTeaException
     */
    @RequestMapping(value="login", method=RequestMethod.POST)
    public ResponseBody<UserLoginResponseVo> login(UserLoginRequestVo requestVo) throws MilkTeaException{
        
        ResponseBody<UserLoginResponseVo> responseBody = new ResponseBody<>();
        responseBody.setData(this.userService.checkUser(requestVo));
        return responseBody;
    }
    
    
}

package com.milktea.milkteashop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ViewController {

   @RequestMapping("/sockettest")
   public String socketTest(){
       return "websocketTest";
   }
}

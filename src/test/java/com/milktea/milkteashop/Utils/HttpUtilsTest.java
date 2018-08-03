package com.milktea.milkteashop.Utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.milktea.milkteashop.utils.HttpUtil;
import com.milktea.milkteashop.utils.JsonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class HttpUtilsTest {

   @Test
   public void  getTest(){
       String url="http://localhost:8080/contactUs/findOne";
       try {
           String content=HttpUtil.get(url);
           CompanyUser user=JsonUtil.parseStrToClass(content,CompanyUser.class);
           System.out.println(user);
       } catch (IOException e) {
           e.printStackTrace();
       }

   }

    @Test
    public void  jsonToListTest(){

        String url="http://localhost:8080/contactUs/findList";  //返回jsonArray   List<CompanyUser> findList()
        try {
            String content=HttpUtil.get(url);
            List<CompanyUser> userList=JsonUtil.parseStrToList(content,CompanyUser.class);
            userList.forEach(user->System.out.println(user.getUserName()+":"+user.getPassword()));
        } catch (IOException e) {
            e.printStackTrace();
        }

   }
}

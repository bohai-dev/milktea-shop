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
        JsonArray jsonArray=new JsonArray();
        for (int i = 0; i < 3; i++) {
            CompanyUser user=new CompanyUser("Tom","123456");
            jsonArray.add(user.toString());
        }

        String arrayStr=jsonArray.toString();
        //String转为list
        List<CompanyUser> list=JsonUtil.parseStrToList(arrayStr,CompanyUser.class);
        Assert.assertEquals(3L,list.size());

   }
}

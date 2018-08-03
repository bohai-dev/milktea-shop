package com.milktea.milkteashop.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    /**
     *json字符串转换为指定类类型,该类类型必须包含无参构造函数
      * @param jsonStr   json字符串
     * @param classOfT   类类型
     * @param <T>   泛型
     * @return
     */
   public static <T> T parseStrToClass(String jsonStr,Class<T> classOfT){

       Gson gson=new Gson();
       T obj=gson.fromJson(jsonStr,classOfT);
       return obj;

   }

    /**
     *JsonArray字符串转换为对象List
     * @param jsonStr  jsonArray字符串
     * @param classOfT 要转换的对象
     * @param <T>      泛型类
     * @return         转换成功的list
     */
    public static <T> List<T> parseStrToList(String jsonStr,Class<T> classOfT){
        Gson gson=new Gson();
        Type type = new TypeToken<ArrayList<JsonObject>>(){}.getType();
        List<JsonObject>  jsonObjects = gson.fromJson(jsonStr,type);

        List<T> list=new ArrayList<>();
        jsonObjects.forEach(jsonObject -> {
            list.add(gson.fromJson(jsonObject,classOfT));

        });
        return  list;

    }




}

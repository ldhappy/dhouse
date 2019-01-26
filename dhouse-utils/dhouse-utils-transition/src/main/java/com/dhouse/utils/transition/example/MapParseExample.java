package com.dhouse.utils.transition.example;

import com.dhouse.utils.transition.exception.info.MapImportExceptionInfo;
import com.dhouse.utils.transition.parse.MapParse;

import java.beans.IntrospectionException;
import java.util.HashMap;
import java.util.Map;

/**
 * map解析样例
 * 梁聃 2018/3/13 13:55
 */
public class MapParseExample {
    public static void main(String[] args) throws IntrospectionException {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","梁聃");
        map.put("age",28);
        map.put("No","No.1");
        map.put("sex","女");
        Student student = new Student();
        MapParse<Student,MapImportExceptionInfo> mp = new MapParse<Student,MapImportExceptionInfo>(map,student);
        mp.setMustValidate(true);
        mp.setValidate(null);
        mp.parse();
        System.out.println(student);
    }


}





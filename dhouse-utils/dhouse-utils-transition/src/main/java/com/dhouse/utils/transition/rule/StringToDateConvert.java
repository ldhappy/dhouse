package com.dhouse.utils.transition.rule;

import com.dhouse.utils.transition.exception.ConvertException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 字符串转换为Date
 * 支持格式"yyyy/MM/dd HH:mm:ss"
 * "yyyy年MM月dd日"
 */
public class StringToDateConvert implements ConvertRule {
    private String errorInfo = "";
    //执行过match的标志
    private boolean activated = false;
    private boolean success = false;
    private static List<SimpleDateFormat> sdfList = new ArrayList<>();
    static {
        sdfList.add(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
        sdfList.add(new SimpleDateFormat("yyyy年MM月dd日"));
    }
    public Object convert(Object source) {
        String s = source.toString().trim();
        activated = true;
        for(SimpleDateFormat sdf:sdfList){
            try {
                Date date = sdf.parse(s);
                success = true;
                return date;
            } catch (ParseException e) {
            }
        }
        errorInfo = "提供格式无法支持当前字符串“"+s+"”转换为Date";
        success = false;
        return null;

    }

    public boolean isSuccess() {
        return success;
    }

    public String errorInfo() {
        if(!activated){
            throw new ConvertException("当前规则未被转换过，请先执行convert方法");
        }
        return errorInfo;
    }
}

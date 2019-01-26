package com.dhouse.utils.transition.example;

import com.dhouse.utils.transition.exception.ConvertException;
import com.dhouse.utils.transition.rule.ConvertRule;

import java.util.Date;

public class DateConvert implements ConvertRule {
    private String errorInfo = "";
    //执行过match的标志
    private boolean activated = false;
    private boolean success = false;
    public Object convert(Object source) {
        activated = true;
        String s = source.toString();
        success = true;
        return new Date();
//        if(StringUtils.isNotBlank(s)){
//            if (s.equals("男")){
//                success = true;
//
//            }else if(s.equals("女")){
//                success = true;
//                return 2;
//            }
//        }else {
//            errorInfo = "信息不能被转换";
//        }
//        return null;
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

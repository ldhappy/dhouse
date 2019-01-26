package com.dhouse.utils.transition.example;

import com.dhouse.utils.transition.exception.ConvertException;
import com.dhouse.utils.transition.rule.ConvertRule;
import org.apache.commons.lang.StringUtils;

public class BooleanConvert implements ConvertRule {
    private String errorInfo = "";
    //执行过match的标志
    private boolean activated = false;
    private boolean success = false;
    public Object convert(Object source) {
        activated = true;
        String s = source.toString();
        if(StringUtils.isNotBlank(s)){
            if (s.equals("0")){
                success = true;
                return false;
            }else if(s.equals("1")){
                success = true;
                return true;
            }
        }else {
            errorInfo = "信息不能被转换";
        }
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

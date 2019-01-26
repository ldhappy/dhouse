package com.dhouse.utils.transition.rule;

import com.dhouse.utils.transition.exception.ConvertException;

/**
 * 字符串转换为数字
 */
public class StringToIntegerConvert implements ConvertRule {
    private String errorInfo = "";
    //执行过match的标志
    private boolean activated = false;
    private boolean success = false;
    public Object convert(Object source) {
        activated = true;
        String s = source.toString();
        try {
            Integer i = Integer.valueOf(s);
            success = true;
            return i;
        } catch (NumberFormatException e) {
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

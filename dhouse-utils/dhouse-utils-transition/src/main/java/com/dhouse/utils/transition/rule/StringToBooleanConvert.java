package com.dhouse.utils.transition.rule;

import com.dhouse.utils.transition.exception.ConvertException;

/**
 * 字符串转换为布尔
 */
public class StringToBooleanConvert implements ConvertRule {
    private String errorInfo = "";
    //执行过match的标志
    private boolean activated = false;
    private boolean success = false;
    public Object convert(Object source) {
        activated = true;
        String s = source.toString().trim();
        if("是".equals(s) || "true".equals(s)){
            success = true;
            return true;
        }else if("否".equals(s) || "false".equals(s)){
            success = true;
            return false;
        }else {
            errorInfo = "请填写内容：“是”或“否”";
            return null;
        }
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

package com.dhouse.utils.transition.example;

import com.dhouse.utils.transition.exception.RuleException;
import com.dhouse.utils.transition.rule.Rule;
import org.apache.commons.lang.StringUtils;

public class NoRule implements Rule {
    private String errorInfo = "";
    //执行过match的标志
    private boolean activated = false;
    public boolean match(Object source) {
        String s = source.toString();
        activated = true;
        String No1="No.1";
        String No2="No.2";
        if(StringUtils.isNotBlank(s)){
            if (s.equals(No1) || s.equals(No2)){
                return true;
            }else{
                errorInfo = "学号不符合要求";
            }
        }else {
            errorInfo = "输入信息不能为空";
        }
        return false;
    }

    public String errorInfo() {
        if(!activated){
            throw new RuleException("当前规则未被验证过，请先执行match方法");
        }
        return errorInfo;
    }
}

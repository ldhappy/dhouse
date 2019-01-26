package com.dhouse.utils.transition.exception.info;

/**
 * 默认导入异常错误信息提示类
 */
public class DefaultExceptionInfo {
    public static DefaultExceptionInfo defaultExceptionInfo = new DefaultExceptionInfo();
    public String PARSE_FAIL = "解析失败";
    public String SOURCE_NULL="资源文件为空，请传入资源文件";
    public String RESULT_NULL="结果存储对象为空，请传入结果存储对象";
    public String NEW_RULE_EXCEPTION = "创建校验规则异常，提示信息为：";
    public String RULE_EXCEPTION = "校验异常，提示信息为：";
    public String  NEW_RESULT_EXCEPTION = "创建结果对象异常，提示信息为：";
    /**
     * 可由继承类创建
     */
    protected DefaultExceptionInfo(){

    }
}

package com.dhouse.utils.transition.exception.info;

/**
 * 导入文件为map类型的异常错误信息提示类
 */
public class MapImportExceptionInfo extends DefaultExceptionInfo{
    public static MapImportExceptionInfo mapImportExceptionInfo = new MapImportExceptionInfo();
    public String MUST_VALIDATE="已开启必须校验数据开关，请传入校验对象";
    public String MUST_CONVERT="请传入转换对象";
    /**
     * 可由继承类创建
     */
    protected MapImportExceptionInfo(){
    }
}

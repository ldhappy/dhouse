package com.dhouse.utils.transition.rule;

/**
 * 转换规则接口
 * 梁聃 2019/1/5 23:27
 */
public interface ConvertRule {
    /**
     * 转换规则接口
     * @param source
     * @return
     */
    Object convert(Object source);

    /**
     * 转换后获取状态
     * @return
     */
    boolean isSuccess();
    /**
     * 转换后读取错误信息
     * @return
     */
    String errorInfo();
}

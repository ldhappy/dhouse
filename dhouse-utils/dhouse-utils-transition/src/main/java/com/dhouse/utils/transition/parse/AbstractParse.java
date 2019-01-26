package com.dhouse.utils.transition.parse;

import com.dhouse.utils.transition.exception.ImprotException;
import com.dhouse.utils.transition.exception.info.DefaultExceptionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 解析顶层虚类
 * 梁聃 2018/3/13 13:45
 * @param <S> 待解析资源
 * @param <R> 解析后结果
 * @param <E> 异常提示文件
 */
public abstract class AbstractParse<S,R,E extends DefaultExceptionInfo> implements Parse ,ParseData<S,R>{
    private static final Logger log = LoggerFactory.getLogger(AbstractParse.class);
    private S source;
    private R result;
    /**
     * 封装了异常信息提示语的默认类
     */
    private DefaultExceptionInfo exceptionInfo= DefaultExceptionInfo.defaultExceptionInfo;
    /**
     * 解析成功标志
     */
    private boolean success = false;
    public AbstractParse(S source, R result) {
        this.source = source;
        this.result = result;
        initValidate();
    }
    /**
     * 初始化入参有效性验证
     */
    protected void initValidate() {
        sourceIsNotNull();
        resultIsNotNull();
    }
    protected void sourceIsNotNull(){
        if(source == null){
            throw new ImprotException(exceptionInfo.SOURCE_NULL);
        }
    }
    protected void resultIsNotNull(){
        if(result == null){
            throw new ImprotException(exceptionInfo.RESULT_NULL);
        }
    }
    public E getExceptionInfo() {
        return (E) exceptionInfo;
    }

    protected void setExceptionInfo(E exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public S getSource() {
        return source;
    }

    public R getResult() {
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    protected void setSuccess(boolean success) {
        this.success = success;
    }
}

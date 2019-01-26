package com.dhouse.utils.transition.parse;

import com.dhouse.utils.transition.convert.AnnotationConvert;
import com.dhouse.utils.transition.convert.Convert;
import com.dhouse.utils.transition.exception.ImprotException;
import com.dhouse.utils.transition.exception.info.MapImportExceptionInfo;
import com.dhouse.utils.transition.validation.AnnotationValidate;
import com.dhouse.utils.transition.validation.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Map;

/**
 *
 */

/**
 * 入参类型为map，解析结果为对象
 * 梁聃 2018/3/13 14:41
 * @param <R> 解析后结果
 * @param <E> 异常提示文件
 */
public class MapParse<R,E extends MapImportExceptionInfo> extends AbstractParse<Map,R,E>{
    private static final Logger log = LoggerFactory.getLogger(MapParse.class);
    /**
     * 内容验证接口，默认实现注解验证
     */
    private Validate validate = new AnnotationValidate<Map,R>(this);
    /**
     * 内容转换接口，默认实现注解转换
     */
    private Convert convert = new AnnotationConvert<Map,R>(this);
    /**
     * 必须验证的标志
     */
    private boolean mustValidate = false;
    public MapParse(Map source, R result) {
        super(source, result);
        setExceptionInfo((E) MapImportExceptionInfo.mapImportExceptionInfo);
    }

    public MapParse(Map source, R result, Validate validate,Convert convert) {
        this(source,result);
        this.validate = validate;
        this.convert = convert;
    }

    public void parse() {
        log.trace("parse-begin>>>param:"+"");
        try {
            //校验
            validate();
            //转换
            convert();
        }catch (IllegalAccessException e) {
            throw new ImprotException(getExceptionInfo().NEW_RULE_EXCEPTION+e.getMessage());
        } catch (InstantiationException e) {
            throw new ImprotException(getExceptionInfo().NEW_RULE_EXCEPTION+e.getMessage());
        }
        setSuccess(true);
        log.trace("parse-end<<<return:");
    }
    private void convert() throws InstantiationException, IllegalAccessException {
        if(convert != null){
            convert.convert();
        }else{
            throw new ImprotException(getExceptionInfo().MUST_CONVERT);
        }
    }

    private void validate() throws IllegalAccessException, InstantiationException {
        sourceIsNotNull();
        resultIsNotNull();
        if(validate != null){
            validate.validate();
        }else if(mustValidate){
            throw new ImprotException(getExceptionInfo().MUST_VALIDATE);
        }
    }

    public Validate getValidate() {
        return validate;
    }

    public void setValidate(Validate validate) {
        this.validate = validate;
    }

    public Convert getConvert() {
        return convert;
    }

    public void setConvert(Convert convert) {
        this.convert = convert;
    }

    public boolean isMustValidate() {
        return mustValidate;
    }

    public void setMustValidate(boolean mustValidate) {
        this.mustValidate = mustValidate;
    }

    /**
     * 按字段名获取结果对象字段内容
     *
     * @param fieldName 字段名
     * @return
     */
    public Object getResultField(String fieldName) {
        try {
            Field field = getResult().getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            log.error("getResultField方法错误："+fieldName+"读取错误，原因："+e.getMessage());
        }
        return null;
    }

    /**
     * 按字段名获取资源对象字段内容
     *
     * @param fieldName 字段名
     * @return
     */
    public Object getSourceField(String fieldName) {
        Map source = getSource();
        return source.get(fieldName);
    }

    /**
     * 按字段名设置结果对象字段内容
     *
     * @param fieldName 字段名
     * @param value
     * @return
     */
    public void setResultField(String fieldName, Object value) {
        try {
            Field field = getResult().getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(getResult(),value);
        } catch (NoSuchFieldException e) {
            log.error("setResultField方法错误："+fieldName+"设置错误，原因："+e.getMessage());
        } catch (IllegalAccessException e) {
            log.error("setResultField方法错误："+fieldName+"设置错误，原因："+e.getMessage());
        }
    }
}

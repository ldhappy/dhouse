package com.dhouse.utils.transition.convert;

import com.dhouse.utils.transition.annotation.Conversion;
import com.dhouse.utils.transition.exception.ImprotException;
import com.dhouse.utils.transition.parse.ParseData;
import com.dhouse.utils.transition.rule.ConvertRule;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class AnnotationConvert<S,R>  implements Convert{
    private ParseData<S,R> parseData;
    private static ConcurrentMap<String,AnnotationConvertClassReflect> classReflectMap = new ConcurrentHashMap<String,AnnotationConvertClassReflect>();
    public AnnotationConvert(ParseData<S, R> parseData) {
        this.parseData = parseData;
    }

    public void convert() throws IllegalAccessException, InstantiationException {
        S source = parseData.getSource();
        R result = parseData.getResult();

        AnnotationConvertClassReflect classReflect = getClassReflect(result);
        for(FieldConvert fieldConvert:classReflect.getFieldConvertList()){
            String name = fieldConvert.getSourceName();
            String errorTipName = fieldConvert.getErrorTipName();
            Conversion conversion = fieldConvert.getConversion();
            Field field = fieldConvert.getField();
            Object value = parseData.getSourceField(name);
            if (value != null){
                if (conversion != null){
                    Object object = null;
                    try {
                        object = conversion.convertRuleClass().getConstructor().newInstance();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    if (object instanceof ConvertRule){
                        ConvertRule rule = (ConvertRule) object;
                        value = rule.convert(value);
                        if(!rule.isSuccess()){
                            throw new ImprotException(parseData.getExceptionInfo().RULE_EXCEPTION + errorTipName + "---" + rule.errorInfo());
                        }
                    } else {
                        throw new ImprotException(parseData.getExceptionInfo().RULE_EXCEPTION + errorTipName + "---" + "配置的规则类" + conversion.convertRuleClass().getCanonicalName() + "必须实现" + ConvertRule.class.getCanonicalName());
                    }

                }
                field.set(result,value);
            }
        }
    }

    private AnnotationConvertClassReflect getClassReflect(R result) {
        AnnotationConvertClassReflect classReflect = classReflectMap.get(result.getClass().getName());
        if(classReflect == null) {
            classReflectMap.putIfAbsent(result.getClass().getName(),new AnnotationConvertClassReflect(result.getClass()));
            classReflect=classReflectMap.get(result.getClass().getName());
        }
        return classReflect;
    }
}

package com.dhouse.utils.transition.validation;

import com.dhouse.utils.transition.annotation.Regulation;
import com.dhouse.utils.transition.exception.ImprotException;
import com.dhouse.utils.transition.parse.ParseDataGet;
import com.dhouse.utils.transition.rule.Rule;
import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 根据注解校验转换数据
 * @param <S>
 * @param <R>
 */
public class AnnotationValidate<S,R>  implements Validate{
    private ParseDataGet<S,R> parseData;
    private static ConcurrentMap<String,AnnotationValidateClassReflect> classReflectMap = new ConcurrentHashMap<String,AnnotationValidateClassReflect>();

    public AnnotationValidate(ParseDataGet<S, R> parseData) {
        this.parseData = parseData;
    }

    public void validate() throws IllegalAccessException, InstantiationException {
        S source = parseData.getSource();
        R result = parseData.getResult();
        AnnotationValidateClassReflect classReflect = getClassReflect(result);
        for (Map.Entry<String,Regulation> entry:classReflect.getRegulationMap().entrySet()){
            String name = entry.getKey();
            String errorTipName;
            if(classReflect.getErrorTipNameMap().containsKey(name)){
                errorTipName = classReflect.getErrorTipNameMap().get(name);
            }else{
                errorTipName = name;
            }
            //规则注解
            Regulation regulation = entry.getValue();
            if (regulation != null) {
                Object value = parseData.getSourceField(name);
                if (value != null) {
                    if (!regulation.ruleClass().equals(Object.class)) {
                        Object object = regulation.ruleClass().newInstance();
                        if (object instanceof Rule) {
                            Rule rule = (Rule) object;
                            if (rule.match(value)) {
                                continue;
                            } else {
                                throw new ImprotException(parseData.getExceptionInfo().RULE_EXCEPTION + errorTipName + "---" + rule.errorInfo());
                            }
                        } else {
                            throw new ImprotException(parseData.getExceptionInfo().RULE_EXCEPTION + errorTipName + "---" + "配置的规则类" + regulation.ruleClass().getCanonicalName() + "必须实现" + Rule.class.getCanonicalName());
                        }
                    } else if (StringUtils.isNotBlank(regulation.rule())) {
                        if (value.toString().matches(regulation.rule())) {
                            continue;
                        } else {
                            throw new ImprotException(parseData.getExceptionInfo().RULE_EXCEPTION + errorTipName + "---" + regulation.errorInfo());
                        }
                    }
                } else if (regulation.required()) {
                    throw new ImprotException(parseData.getExceptionInfo().RULE_EXCEPTION + "缺少必填字段" + errorTipName);
                }
            }
        }
    }

    private AnnotationValidateClassReflect getClassReflect(R result) {
        AnnotationValidateClassReflect classReflect = classReflectMap.get(result.getClass().getName());
        if(classReflect == null) {
            classReflectMap.putIfAbsent(result.getClass().getName(),new AnnotationValidateClassReflect(result.getClass()));
            classReflect=classReflectMap.get(result.getClass().getName());
        }
        return classReflect;
    }



}

package com.dhouse.utils.transition.parse;

import com.dhouse.utils.transition.exception.ImprotException;
import com.dhouse.utils.transition.exception.info.MapImportExceptionInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * List<Map>类型对象解析
 * @param <R>
 */
public class ListMapParse<R> implements Parse {
    private List<Map<String,Object>> source;
    private List<R> result;
    private Class resultClass;
    private List<Map<String,Object>> errorList = new ArrayList<Map<String, Object>>();
    private String errorInfoField = "listMapParseErrorInfo";
    /**
     * 封装了异常信息提示语的默认类
     */
    private MapImportExceptionInfo exceptionInfo= MapImportExceptionInfo.mapImportExceptionInfo;
    /**
     * 解析成功标志
     */
    private boolean success = false;
    public ListMapParse(List<Map<String, Object>> source, List<R> result,Class resultClass) {
        this.source = source;
        this.result = result;
        this.resultClass = resultClass;
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
    public void parse() {
        sourceIsNotNull();
        resultIsNotNull();
        for(Map<String,Object> map:source){
            try {
                R r = (R) resultClass.newInstance();
                MapParse<R,MapImportExceptionInfo> mp = new MapParse<R,MapImportExceptionInfo>(map,r);
                mp.parse();
                if(mp.isSuccess()){
                    result.add(r);
                }else{
                    map.put(errorInfoField,getExceptionInfo().PARSE_FAIL);
                    errorList.add(map);
                }
            }catch (IllegalAccessException e) {
                map.put(errorInfoField,getExceptionInfo().NEW_RESULT_EXCEPTION+e.getMessage());
                errorList.add(map);
            } catch (InstantiationException e) {
                map.put(errorInfoField,getExceptionInfo().NEW_RESULT_EXCEPTION+e.getMessage());
                errorList.add(map);
            }catch (Exception e) {
                map.put(errorInfoField,e.getMessage());
                errorList.add(map);
            }

        }
        success = true;
    }

    public MapImportExceptionInfo getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(MapImportExceptionInfo exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public List<Map<String, Object>> getSource() {
        return source;
    }

    public List<R> getResult() {
        return result;
    }

    public Class getResultClass() {
        return resultClass;
    }

    public List<Map<String, Object>> getErrorList() {
        return errorList;
    }

    public String getErrorInfoField() {
        return errorInfoField;
    }

    public boolean isSuccess() {
        return success;
    }
}

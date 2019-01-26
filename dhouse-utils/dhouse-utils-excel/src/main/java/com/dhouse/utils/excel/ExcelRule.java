package com.dhouse.utils.excel;

import com.dhouse.utils.excel.exception.ExcelException;
import com.dhouse.utils.excel.reader.content.ContentDefaultReader;
import com.dhouse.utils.excel.reader.content.ContentReader;

import java.util.List;
/**
 *
 */

/**
 * 待校验的excel的规则
 * 梁聃 2019/1/6 9:32
 * @param <T> 解析目标对象类型
 */
public class ExcelRule<T> {
    /**
     * 规则列名List
     */
    private List<ColumnHeader>  ruleColumnHeaderList;
    /**
     * 列头开始行号
     */
    private Integer columnHeaderBeginRowIndex = 0;
    /**
     * 列头结束行号
     */
    private Integer columnHeaderEndRowIndex = 0;
    /**
     * 最大的列索引号
     */
    private Integer maxColumnIndex;
    /**
     * 可解析的最大行数
     */
    private Integer maxRows;
    /**
     * 校验后存储错误信息
     */
    private String errorInfo = "";
    /**
     * 执行过checkColumns的标志
     */
    private boolean activated = false;
    /**
     * 默认行内容读取器（如有特殊需求可以继承扩展）
     */
    private ContentReader<T> contentReader;
    /**
     * 解析目标对象类型，解析的目标对象配置方式见example.Student
     */
    private Class targetClass;

    /**
     * 待校验的excel的规则
     * @param ruleColumnHeaderList 待校验的excel的规则列名
     * @param targetClass 解析目标对象
     * @param maxColumnIndex 最大的列索引号
     */
    public ExcelRule(List<ColumnHeader> ruleColumnHeaderList,Class targetClass,Integer maxColumnIndex) {
        this(ruleColumnHeaderList,targetClass,maxColumnIndex,null);
    }

    /**
     * 待校验的excel的规则
     * @param ruleColumnHeaderList 待校验的excel的规则列名
     * @param targetClass 解析目标对象
     * @param maxColumnIndex 最大的列索引号
     * @param maxRows 可解析最大行
     */
    public ExcelRule(List<ColumnHeader> ruleColumnHeaderList,Class targetClass,Integer maxColumnIndex, Integer maxRows) {
        this(ruleColumnHeaderList,targetClass,maxColumnIndex,maxRows,new ContentDefaultReader<T>());
    }

    /**
     * 待校验的excel的规则
     * @param ruleColumnHeaderList 待校验的excel的规则列名
     * @param targetClass 解析目标对象
     * @param maxColumnIndex 最大的列索引号
     * @param maxRows 可解析最大行
     * @param contentReader 默认行内容读取器（如有特殊需求可以继承扩展）
     */
    public ExcelRule(List<ColumnHeader> ruleColumnHeaderList,Class targetClass,Integer maxColumnIndex, Integer maxRows, ContentReader<T> contentReader) {
        this.ruleColumnHeaderList = ruleColumnHeaderList;
        this.targetClass = targetClass;
        this.maxColumnIndex = maxColumnIndex;
        this.maxRows = maxRows;
        this.contentReader = contentReader;
        this.contentReader.setExcelRule(this);
    }

    /**
     * 查验列名是否符合规则
     * @param columnList
     * @return
     */
    public boolean checkColumns(List<ColumnHeader> columnList){
        boolean isSuccess = false;
        activated = true;
        if(ruleColumnHeaderList == null){
            errorInfo = "未设置规则列名，请先设置";
            return isSuccess;
        }
        if(columnList == null){
            errorInfo = "未设置待校验列名，请先设置";
            return isSuccess;
        }
        Integer count = ruleColumnHeaderList.size();
        if(count != columnList.size()){
            errorInfo = "待校验excel的列数为"+columnList.size()+"列与模板列数"+count+"列不一致";
            return isSuccess;
        }
        for(int i=0;i<count;i++){
            ColumnHeader ruleColumnHeader = ruleColumnHeaderList.get(i);
            ColumnHeader columnHeader = columnList.get(i);
            if(!ruleColumnHeader.equalsValue(columnHeader)){
                errorInfo = "待校验excel第"+(i+1)+"的列名“"+columnHeader.getColumnName()+"”与规则“"+ruleColumnHeader.getColumnName()+"”不一致";
                return isSuccess;
            }
        }
        isSuccess = true;
        return isSuccess;
    }

    public String getErrorInfo() {
        if(!activated){
            throw new ExcelException("当前规则未被校验过，请先执行checkColumns方法");
        }
        return errorInfo;
    }

    public Integer getMaxRows() {
        return maxRows;
    }

    public List<ColumnHeader> getRuleColumnList() {
        return ruleColumnHeaderList;
    }

    public ContentReader<T> getContentReader() {
        return contentReader;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public Integer getColumnHeaderBeginRowIndex() {
        return columnHeaderBeginRowIndex;
    }

    public void setColumnHeaderBeginRowIndex(Integer columnHeaderBeginRowIndex) {
        this.columnHeaderBeginRowIndex = columnHeaderBeginRowIndex;
    }

    public Integer getColumnHeaderEndRowIndex() {
        return columnHeaderEndRowIndex;
    }

    public void setColumnHeaderEndRowIndex(Integer columnHeaderEndRowIndex) {
        this.columnHeaderEndRowIndex = columnHeaderEndRowIndex;
    }

    public Integer getMaxColumnIndex() {
        return maxColumnIndex;
    }
}

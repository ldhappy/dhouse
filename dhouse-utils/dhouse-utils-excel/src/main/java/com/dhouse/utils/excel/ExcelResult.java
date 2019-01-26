package com.dhouse.utils.excel;

import java.io.File;
import java.util.*;

/**
 * excel的解析结果
 * 梁聃 2019/1/6 20:43
 *
 * @param <T> 目标解析结果
 */
public class ExcelResult<T> {
    /**
     * excel解析后错误字段列名
     */
    private String errorInfoField = "ErrorInfo";
    /**
     * excel解析成功标志
     */
    private boolean success = false;
    /**
     * 解析excel失败后存储错误信息，默认为有错且设置默认错误信息，防止空文件或未满足列解析条件时没有提示语的异常
     */
    private String errorInfo = "待校验excel与模板文件不匹配，无法成功解析";
    /**
     * 成功解析行
     */
    private Map<T, Integer> successRows = new HashMap<>();
    /**
     * 成功解析行原始信息
     * TreeMap:key：输入文件行号，value：row的info
     * Map：key：excel的列号，如A,B,C，value：单元格的内容，列号互转可以使用ExcelUtil的转换工具
     */
    private TreeMap<Integer, Map<String, String>> successRowsOriginal = new TreeMap<Integer, Map<String, String>>();
    /**
     * 失败解析行
     * TreeMap:key：输入文件行号，value：row的info
     * Map：key：excel的列号，如A,B,C，value：单元格的内容，列号互转可以使用ExcelUtil的转换工具
     */
    private TreeMap<Integer, Map<String, String>> faultRows = new TreeMap<Integer, Map<String, String>>();

    private Integer totalRowsSize = 0;
    /**
     * excel的解析规则
     */
    private ExcelRule<T> excelRule;

    public ExcelResult(ExcelRule<T> excelRule) {
        this.excelRule = excelRule;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        if(success){
            //校验无异常时，清除默认的错误信息字段
            errorInfo = "";
        }
        this.success = success;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getErrorInfoField() {
        return errorInfoField;
    }

    public void setErrorInfoField(String errorInfoField) {
        this.errorInfoField = errorInfoField;
    }

    public Map<T, Integer> getSuccessRows() {
        return successRows;
    }

    public TreeMap<Integer, Map<String, String>> getSuccessRowsOriginal() {
        return successRowsOriginal;
    }

    public TreeMap<Integer, Map<String, String>> getFaultRows() {
        return faultRows;
    }

    /**
     * 此处考虑分页处理时，有可能successRows已经处理完成移出successRows，所以采用总数-失败数获得成功数
     * @return
     */
    public int getSuccessRowsSize() {
        return totalRowsSize - faultRows.size();
    }

    public int getFaultRowsSize() {
        return faultRows.size();
    }

    public void setTotalRowsSize(Integer totalRowsSize) {
        this.totalRowsSize = totalRowsSize;
    }

    public int getTotalRowsSize() {
        return totalRowsSize;
    }

    public ExcelRule<T> getExcelRule() {
        return excelRule;
    }

    public void setExcelRule(ExcelRule<T> excelRule) {
        this.excelRule = excelRule;
    }

    @Override
    public String toString() {
        return "ExcelResult{" +
                "errorInfoField='" + errorInfoField + '\'' +
                ", success=" + success +
                ", errorInfo='" + errorInfo + '\'' +
                ", successRows=" + successRowsToString() +
                ", faultRows=" + faultRowsToString() +
                ", faultRowsSize=" + getFaultRowsSize() +
                ", columnHeaderList=" + columnNameListToString() +
                '}';
    }

    private String columnNameListToString() {
        StringBuilder sb = new StringBuilder();
        List<ColumnHeader> columnHeaderList = excelRule.getRuleColumnList();
        if(columnHeaderList != null && columnHeaderList.size() != 0){
            columnHeaderList.stream().forEach(columnHeader -> {
                sb.append(columnHeader.getCoordinate());
                sb.append(":");
                sb.append(columnHeader.getColumnName());
                sb.append("(");
                sb.append(columnHeader.getCoverageColumn());
                sb.append(", ");
                sb.append(columnHeader.getCoverageRow());
                sb.append("),");
            });
            sb.delete(sb.lastIndexOf(","), sb.length());
        }
        return sb.toString();
    }

    private String faultRowsToString() {
        StringBuilder sb = new StringBuilder();
        if(faultRows != null && faultRows.size() != 0){
            faultRows.entrySet().stream().forEach(entry -> {
                sb.append("行号：" + entry.getKey() + "{");
                if (entry.getValue() != null && entry.getValue().size() != 0) {
                    for (Map.Entry<String, String> entrySon : entry.getValue().entrySet()) {
                        sb.append(entrySon.getKey() + ":" + entrySon.getValue() + ", ");
                    }

                    sb.delete(sb.lastIndexOf(", "), sb.length());
                }
                sb.append("}");
            });
        }
        return sb.toString();
    }

    private String successRowsToString() {
        StringBuilder sb = new StringBuilder();
        if(successRows != null && successRows.size() != 0){
            successRows.entrySet().stream().forEach(entry -> {
                sb.append("行号：" + entry.getValue());
                sb.append("{" + entry.getKey() + "}");
            });
        }
        return sb.toString();
    }

    /**
     * 设置错误信息
     *
     * @param index
     * @param errorMessage
     * @param currentRow
     */
    public void putFaultRow(int index, String errorMessage, Map<String, String> currentRow) {
        currentRow.put(errorInfoField, errorMessage);
        faultRows.put(index, currentRow);
    }

    /**
     * 将出错的成功解析行转入失败解析行，并记录错误信息
     *
     * @param row
     * @param errorMessage
     */
    public void removeSuccessRows(T row, String errorMessage) {
        Integer rowNo = successRows.get(row);
        successRows.remove(row);
        putFaultRow(rowNo, errorMessage, successRowsOriginal.get(rowNo));
        successRowsOriginal.remove(rowNo);
    }
}

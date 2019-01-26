package com.dhouse.utils.excel;

import com.dhouse.utils.excel.exception.ExcelException;
import com.dhouse.utils.excel.util.ExcelUtil;

/**
 * 列头对象
 * 梁聃 2019/1/21 9:05
 */
public class ColumnHeader {
    /**
     * 列名称
     */
    private String columnName;
    /**
     * 坐标格式必须为“A1”“AA2”
     */
    private String coordinate;
    /**
     * 占列数
     */
    private Integer coverageColumn;
    /**
     * 占行数
     */
    private Integer coverageRow;
    /**
     * 坐标列号，无需设置，getCoordinateColumn()方法中自动解析
     */
    private Integer coordinateColumn = null;
    /**
     * 坐标行号，无需设置，getCoordinateColumn()方法中自动解析
     */
    private Integer coordinateRow = null;

    public ColumnHeader() {
    }

    public ColumnHeader(String columnName, String coordinate) {
        this.columnName = columnName;
        this.coordinate = coordinate;
        this.coverageColumn = 1;
        this.coverageRow = 1;
    }

    public ColumnHeader(String columnName, String coordinate, Integer coverageColumn) {
        this.columnName = columnName;
        this.coordinate = coordinate;
        this.coverageColumn = coverageColumn;
        this.coverageRow = 1;
    }

    public ColumnHeader(String columnName, String coordinate, Integer coverageColumn, Integer coverageRow) {
        this.columnName = columnName;
        this.coordinate = coordinate;
        this.coverageColumn = coverageColumn;
        this.coverageRow = coverageRow;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public Integer getCoverageColumn() {
        return coverageColumn;
    }

    public void setCoverageColumn(Integer coverageColumn) {
        this.coverageColumn = coverageColumn;
    }

    public Integer getCoverageRow() {
        return coverageRow;
    }

    public void setCoverageRow(Integer coverageRow) {
        this.coverageRow = coverageRow;
    }

    public boolean equalsValue(ColumnHeader columnHeader) {
        if(columnHeader == null || columnHeader.getColumnName() == null || columnHeader.getCoordinate() == null){
            return false;
        }
        if(columnName == null || coordinate == null){
            return false;
        }
        if(columnName.equals(columnHeader.getColumnName()) && coordinate.equals(columnHeader.getCoordinate())){
            return true;
        } else {
            return false;
        }
    }

    public Integer getCoordinateColumn() {
        if(coordinateColumn == null){
            calculateCoordinate();
        }
        return coordinateColumn;
    }

    public Integer getCoordinateRow() {
        if(coordinateRow == null){
            calculateCoordinate();
        }
        return coordinateRow;
    }

    /**
     * 计算坐标
     */
    private void calculateCoordinate() {
        if(coordinate.matches("^[A-Z]+[0-9]+$")){
            StringBuilder sb = new StringBuilder();
            for(int i = 0;i<coordinate.length();i++){
                char c = coordinate.charAt(i);
                if('A' <= c && c <= 'Z'){
                    sb.append(c);
                }else{
                    coordinateRow = Integer.valueOf(coordinate.substring(i,coordinate.length())) - 1;
                    break;
                }
            }
            coordinateColumn = ExcelUtil.excelColStrToNum(sb.toString());
        }else{
            throw new ExcelException("当前坐标异常，请检查设置。坐标需满足^[A-Z]+[0-9]+$");
        }
    }
}

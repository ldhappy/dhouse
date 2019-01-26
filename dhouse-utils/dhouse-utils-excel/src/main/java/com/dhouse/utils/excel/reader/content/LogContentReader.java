package com.dhouse.utils.excel.reader.content;

import com.dhouse.utils.excel.ExcelResult;
import com.dhouse.utils.excel.ExcelRule;
import org.apache.poi.xssf.usermodel.XSSFComment;

/**
 * 简单打印日志，用于查看SheetContentsXlsReader内容解析是否正确
 * 梁聃 2019/1/9 10:52
 * @param <T>
 */
public class LogContentReader<T> implements ContentReader<T> {
    /**
     * 行解析开始时调用
     *
     * @param index 当前索引号
     */
    @Override
    public void startRow(int index) {
        System.out.println("startRow-begin>>>param:"+"index = [" + index + "]");
        System.out.println("startRow-end<<<return:");
    }

    /**
     * 行解析结束时调用
     *
     * @param index
     */
    @Override
    public void endRow(int index) {
        System.out.println("endRow-begin>>>param:"+"index = [" + index + "]");
        System.out.println("endRow-end<<<return:");
    }

    /**
     * 每个单元格内容解析时调用
     *
     * @param s           单元格坐标
     * @param s1          单元格内容
     * @param xssfComment
     */
    @Override
    public void cell(String s, String s1, XSSFComment xssfComment) {
        System.out.println("cell-begin>>>param:"+"s = [" + s + "], s1 = [" + s1 + "], xssfComment = [" + xssfComment + "]");
        System.out.println("cell-end<<<return:");
    }

    @Override
    public void headerFooter(String s, boolean b, String s1) {
        System.out.println("headerFooter-begin>>>param:"+"s = [" + s + "], b = [" + b + "], s1 = [" + s1 + "]");
        System.out.println("headerFooter-end<<<return:");
    }

    @Override
    public void setExcelResult(ExcelResult excelResult) {

    }

    @Override
    public void setExcelRule(ExcelRule<T> excelRule) {

    }
}

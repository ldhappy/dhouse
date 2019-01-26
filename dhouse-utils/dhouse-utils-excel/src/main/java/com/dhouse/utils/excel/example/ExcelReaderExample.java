package com.dhouse.utils.excel.example;

import com.dhouse.utils.excel.ColumnHeader;
import com.dhouse.utils.excel.ExcelResult;
import com.dhouse.utils.excel.ExcelRule;
import com.dhouse.utils.excel.reader.ExcelReader;
import com.dhouse.utils.excel.writer.ExcelWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * excel导入样例
 * 梁聃 2019/1/9 17:09
 */
public class ExcelReaderExample {
    public static void main(String[] args) throws IOException {
        File xlsxFile = new File("E:/ld/document/test.xls");
        if (!xlsxFile.exists()) {
            System.err.println("Not found or not a file: " + xlsxFile.getPath());
            return ;
        }
        List<ColumnHeader> ruleColumnList = new ArrayList<>();
        ruleColumnList.add(new ColumnHeader("Num","A1"));
        ruleColumnList.add(new ColumnHeader("name","B1"));
        ruleColumnList.add(new ColumnHeader("age","C1"));
        ruleColumnList.add(new ColumnHeader("sex","D1"));
        ruleColumnList.add(new ColumnHeader("出生日期","E1"));
        ExcelRule<Student> excelRule = new ExcelRule<Student>(ruleColumnList, Student.class,5);
        ExcelResult<Student> excelResult = new ExcelResult<Student>(excelRule);
        ExcelReader.excelSimpleReader(xlsxFile,excelRule,excelResult);
        System.out.println(excelResult);
//        System.out.println(excelResult);
//        if(excelResult.getFaultRowsSize() > 0){
//            File errorFile = new File("D:/document/errorTest.xlsx");
//            if(!errorFile.exists()){
//                errorFile.createNewFile();
//            }
//            excelResult.setErrorFile(errorFile);
//            ExcelWriter.excelResultFaultRowsWriter(excelResult,3);
//        }

    }
}

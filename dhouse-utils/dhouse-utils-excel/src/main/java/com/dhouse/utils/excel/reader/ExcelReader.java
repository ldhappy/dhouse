package com.dhouse.utils.excel.reader;

import com.dhouse.utils.excel.ExcelResult;
import com.dhouse.utils.excel.ExcelRule;
import com.dhouse.utils.excel.reader.hssf.XlsReader;
import com.dhouse.utils.excel.reader.xssf.XlsxReader;

import java.io.File;
import java.io.InputStream;

/**
 * 读取excel
 * 梁聃 2019/1/6 11:06
 */
public class ExcelReader {
    final static private String XLSX_EXT = ".xlsx";
    final static private String XLS_EXT = ".xls";
    /**
     * excel的简单解析，仅读取第一个sheet的内容,所有日期类数据固定转换格式为"yyyy/MM/dd HH:mm:ss"
     * @param file 待解析excel文件
     * @param excelRule 待解析excel的解析规则
     * @param excelResult excel的解析结果
     */
    static public void excelSimpleReader(File file, ExcelRule excelRule,ExcelResult excelResult){
        String filePath = file.getPath();
        //扩展名
        String extString = filePath.substring(filePath.lastIndexOf(".")).toLowerCase();
        if(XLSX_EXT.equals(extString)){
            XlsxReader.xlsxSimpleReader(file,excelRule,excelResult);
        }else if(XLS_EXT.equals(extString)){
            XlsReader.xlsSimpleReader(file,excelRule,excelResult);
        }else{
            //文件扩展名异常无法解析
            excelResult.setSuccess(false);
            excelResult.setErrorInfo("文件扩展名异常无法解析");
        }
    }
    /**
     * excel的简单解析，仅读取第一个sheet的内容,所有日期类数据固定转换格式为"yyyy/MM/dd HH:mm:ss"
     * @param key 阿里的远程文件名
     * @param inputStream 待解析excel文件inputStream
     * @param excelRule 待解析excel的解析规则
     * @param excelResult excel的解析结果
     */
    static public void excelSimpleReader(String key, InputStream inputStream, ExcelRule excelRule, ExcelResult excelResult){
        //扩展名
        String extString = key.substring(key.lastIndexOf(".")).toLowerCase();
        if(XLSX_EXT.equals(extString)){
            XlsxReader.xlsxSimpleReader(inputStream,excelRule,excelResult);
        }else if(XLS_EXT.equals(extString)){
            XlsReader.xlsSimpleReader(inputStream,excelRule,excelResult);
        }else{
            //文件扩展名异常无法解析
            excelResult.setSuccess(false);
            excelResult.setErrorInfo("文件扩展名异常无法解析");
        }
    }

}

package com.dhouse.utils.excel.reader.hssf;

import com.dhouse.utils.excel.ExcelResult;
import com.dhouse.utils.excel.ExcelRule;
import com.dhouse.utils.excel.exception.ExcelException;
import com.dhouse.utils.excel.reader.hssf.listener.FormatTrackingHSSFListener;
import com.dhouse.utils.excel.reader.hssf.listener.SimpleSheetContentsHSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFEventFactory;
import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.apache.poi.hssf.eventusermodel.MissingRecordAwareHSSFListener;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;

/**
 * 读取xls文件
 * 梁聃 2019/1/6 11:19
 */
public class XlsReader {
    /**
     * excel的简单解析，仅读取第一个sheet的内容,所有日期类数据固定转换格式为"yyyy/MM/dd HH:mm:ss"
     * @param file 待解析excel文件
     * @param excelRule 待解析excel的解析规则
     * @param excelResult excel的解析结果
     * @return
     */
    static public void xlsSimpleReader(File file, ExcelRule excelRule,ExcelResult excelResult){
        try {
            xlsSimpleReader(new FileInputStream(file),excelRule,excelResult);
        } catch (FileNotFoundException e) {
            excelResult.setSuccess(false);
            excelResult.setErrorInfo(e.getMessage());
        }
    }
    /**
     * excel的简单解析，仅读取第一个sheet的内容,所有日期类数据固定转换格式为"yyyy/MM/dd HH:mm:ss"
     * @param inputStream 待解析excel文件inputStream
     * @param excelRule 待解析excel的解析规则
     * @param excelResult excel的解析结果
     * @return
     */
    static public void xlsSimpleReader(InputStream inputStream, ExcelRule excelRule, ExcelResult excelResult){
        HSSFEventFactory factory = new HSSFEventFactory();
        HSSFRequest request = new HSSFRequest();
        //1设置解析规则和结果
        excelRule.getContentReader().setExcelResult(excelResult);
        //注册Record处理监听器
        SimpleSheetContentsHSSFListener sheetContentsHSSFListener = new SimpleSheetContentsHSSFListener(excelRule.getContentReader());
        MissingRecordAwareHSSFListener listener = new MissingRecordAwareHSSFListener(
                sheetContentsHSSFListener);
        FormatTrackingHSSFListener formatListener = new FormatTrackingHSSFListener(listener);
        sheetContentsHSSFListener.setFormatListener(formatListener);
        request.addListenerForAllRecords(formatListener);
        try {
            POIFSFileSystem fs = new POIFSFileSystem(inputStream);
            factory.processWorkbookEvents(request, fs);
        } catch (ExcelException e) {
            excelResult.setErrorInfo(e.getMessage());
        }catch (IOException e) {
            excelResult.setSuccess(false);
            excelResult.setErrorInfo(e.getMessage());
        }
    }
}

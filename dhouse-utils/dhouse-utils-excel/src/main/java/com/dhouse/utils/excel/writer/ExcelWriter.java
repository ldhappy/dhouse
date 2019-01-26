package com.dhouse.utils.excel.writer;

import com.dhouse.utils.excel.ExcelResult;
import com.dhouse.utils.excel.ExcelRule;
import com.dhouse.utils.excel.util.ExcelUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelWriter {
    final static public String XLSX_EXT = ".xlsx";
    /**
     * 导出导入时的错误行信息
     * 导出文件为xlsx
     * 采用性能更高的SXSSFWorkbook方式
     * 格式要求：
     * excelResult的TreeMap<Integer,Map<String,String>> faultRows，faultRow的Map的key必须为excel列号如A,B,C,D
     * @param excelResult 导入文件结果
     * @param rowAccessWindowSize 分批次导出文件行数
     */
    static public ByteArrayOutputStream excelResultFaultRowsWriter(ExcelResult<?> excelResult, int rowAccessWindowSize) throws IOException {
        //创建XSSFWorkbook对象(excel的文档对象)
        XSSFWorkbook workbook = new XSSFWorkbook();
        //采用性能更高的SXSSFWorkbook方式
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(workbook, rowAccessWindowSize);
        //建立新的sheet对象（excel的表单）
        Sheet sheet = sxssfWorkbook.createSheet("未成功导入数据");
        ExcelRule<?> excelRule = excelResult.getExcelRule();
        int rowIndex = 0;
        int columnIndex = 0;

        //设置表头
        Map<Integer,Row> columnHeaderRowMap = new HashMap<>(excelRule.getColumnHeaderEndRowIndex()+1);
        for(;rowIndex<=excelRule.getColumnHeaderEndRowIndex();rowIndex++){
            Row row = sheet.createRow(rowIndex);
            columnHeaderRowMap.put(rowIndex,row);
        }
        excelRule.getRuleColumnList().stream().forEach(columnHeader -> {
            CellUtil.createCell(columnHeaderRowMap.get(columnHeader.getCoordinateRow()),columnHeader.getCoordinateColumn(),columnHeader.getColumnName());
            sheet.addMergedRegion(new CellRangeAddress(columnHeader.getCoordinateRow(),columnHeader.getCoordinateRow()+columnHeader.getCoverageRow()-1,columnHeader.getCoordinateColumn(),columnHeader.getCoordinateColumn()+columnHeader.getCoverageColumn()-1));
        });
        //设置错误信息列
        CellUtil.createCell(columnHeaderRowMap.get(rowIndex-1), excelRule.getMaxColumnIndex(), excelResult.getErrorInfoField());
        //设置表格内容
        for(Map.Entry<Integer,Map<String,String>> contentRowEntry:excelResult.getFaultRows().entrySet()){
            Row contentRow=sheet.createRow(rowIndex++);
            Map<String,String> contentRowInfo = contentRowEntry.getValue();
            for(columnIndex = 0;columnIndex < excelRule.getMaxColumnIndex();columnIndex++){
                String columnNo = ExcelUtil.excelColIndexToStr(columnIndex);
                String columnValue = contentRowInfo.get(columnNo);
                if(StringUtils.isNotBlank(columnValue)){
                    CellUtil.createCell(contentRow, columnIndex,contentRowInfo.get(columnNo));
                }
            }
            //设置错误信息列
            CellUtil.createCell(contentRow, columnIndex,contentRowInfo.get(excelResult.getErrorInfoField()));
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        sxssfWorkbook.write(os);
        return os;
    }
}

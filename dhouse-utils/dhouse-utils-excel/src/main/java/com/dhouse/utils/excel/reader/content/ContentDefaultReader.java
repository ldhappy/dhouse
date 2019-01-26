package com.dhouse.utils.excel.reader.content;

import com.dhouse.utils.excel.ColumnHeader;
import com.dhouse.utils.excel.ExcelResult;
import com.dhouse.utils.excel.ExcelRule;
import com.dhouse.utils.excel.exception.ExcelException;
import com.dhouse.utils.excel.exception.info.DefaultExceptionInfo;
import com.dhouse.utils.transition.exception.ImprotException;
import com.dhouse.utils.transition.exception.info.MapImportExceptionInfo;
import com.dhouse.utils.transition.parse.MapParse;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFComment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 默认统一内容读取器
 * 可直接全量读取excel或按照读取规则限制的最大行数读取
 * currentRow，key:excel的列号字符串，如A,B,C,D
 * 解析的目标对象配置方式见example.Student
 * 要求解析目标对象的@Name注解的sourceName = "A"与Map<String, String> currentRow对象的key对应
 * 梁聃 2019/1/6 17:09
 */
public class ContentDefaultReader<T> implements ContentReader<T> {

    /**
     * excel的解析规则
     */
    protected ExcelRule<T> excelRule;
    /**
     * excel的解析结果
     */
    protected ExcelResult excelResult;
    /**
     * 列名List
     */
    protected List<ColumnHeader> columnHeaderList = new ArrayList<>();
    /**
     * 当前索引行号
     */
    protected int currentIndex = 0;
    /**
     * 当前行信息
     * key:excel的列号字符串，如A,B,C,D
     * value:单元格实际内容
     */
    Map<String, String> currentRow;
    /**
     * 内容行开始标志
     */
    protected boolean contentRowBeginFlag = false;

    /**
     * 行解析开始时调用
     * @param index 当前索引号
     */
    @Override
    public void startRow(int index) {
        currentIndex = index;
        //校验不可超过最大行数
        if(excelRule.getMaxRows() != null && excelRule.getMaxRows() < (index+excelRule.getColumnHeaderEndRowIndex())){
            throw new ExcelException(DefaultExceptionInfo.defaultExceptionInfo.OVERFLOW_MAX_ROWS+excelRule.getMaxRows());
        }
        if(contentRowBeginFlag){
            currentRow = new HashMap<>();
        }
    }

    /**
     * 行解析结束时调用
     * @param index 当前索引号
     */
    @Override
    public void endRow(int index) {
        if(index == excelRule.getColumnHeaderEndRowIndex()){
            //列头
            //1.校验列头名称信息
            boolean success = excelRule.checkColumns(columnHeaderList);
            if(!success){
                throw new ExcelException(excelRule.getErrorInfo());
            }
            excelResult.setSuccess(true);
            contentRowBeginFlag = true;
        }else if(contentRowBeginFlag){
            //2.内容行开始，将内容转换为目标对象
            Class clazz = excelRule.getTargetClass();
            MapParse<T,MapImportExceptionInfo> mp = null;
            try {
                T object = (T)clazz.newInstance();
                mp = new MapParse<T,MapImportExceptionInfo>(currentRow,object);
                //设置是否必须要校验，false时不校验，直接转换
                mp.setMustValidate(true);
                //转换
                mp.parse();
                //SuccessRows存储转换后的对象
                excelResult.getSuccessRows().put(object,index);
                //SuccessRowsOriginal存储excel的原内容，在不能通过内容校验时使用
                excelResult.getSuccessRowsOriginal().put(index,currentRow);
            } catch (ImprotException e) {
                excelResult.putFaultRow(index,e.getMessage(),currentRow);
            } catch (InstantiationException e) {
                excelResult.putFaultRow(index,e.getMessage(),currentRow);
            } catch (IllegalAccessException e) {
                excelResult.putFaultRow(index,e.getMessage(),currentRow);
            }
            excelResult.setTotalRowsSize(excelResult.getTotalRowsSize()+1);
        }

    }
    /**
     * 每个单元格内容解析时调用
     * @param coordinate 单元格坐标
     * @param value 单元格内容
     * @param xssfComment
     */
    @Override
    public void cell(String coordinate, String value, XSSFComment xssfComment) {
        //数据不为空时处理数据内容
        if(StringUtils.isNotBlank(value)){
            if(excelRule.getColumnHeaderBeginRowIndex() <= currentIndex && currentIndex <= excelRule.getColumnHeaderEndRowIndex()){
                //列头
                columnHeaderList.add(new ColumnHeader(value,coordinate));
            }else if(contentRowBeginFlag){
                //正式行信息
                coordinate=coordinate.substring(0,coordinate.lastIndexOf((currentIndex+1)+""));
                currentRow.put(coordinate,value);
            }
        }
    }

    @Override
    public void headerFooter(String s, boolean b, String s1) {
    }

    public ExcelRule getExcelRule() {
        return excelRule;
    }

    public void setExcelRule(ExcelRule excelRule) {
        this.excelRule = excelRule;
    }

    public ExcelResult getExcelResult() {
        return excelResult;
    }

    public void setExcelResult(ExcelResult excelResult) {
        this.excelResult = excelResult;
    }
}

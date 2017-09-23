package com.wustrive.util.file;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.wustrive.util.date.DateTimeUtils;
import com.wustrive.util.string.StringUtils;

public class ExcelUtil {
    
    
    public static Workbook openWorkbook(InputStream in,String filename)throws IOException{
        Workbook wb = null;
        if(filename.endsWith(".xlsx")){
            wb = new XSSFWorkbook(in);//Excel 2007
        } else {
            wb = new HSSFWorkbook(in);//Excel 2003
        }
        return wb;
    }
    
    /**
     * 根据类型取excel单元格值
     * @param hssfCell
     * @return
     */
    public static String getValue(Cell hssfCell) {
         if (hssfCell.getCellTypeEnum() == CellType.BOOLEAN) {
             return String.valueOf(hssfCell.getBooleanCellValue());
         } else if (hssfCell.getCellTypeEnum() == CellType.NUMERIC) {
             if(org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(hssfCell)) {
                return DateTimeUtils.formaterDate(hssfCell.getDateCellValue(), "yyyy/MM/dd");
             } else {
                 return String.valueOf(hssfCell.getNumericCellValue());
             }
         } else {
             return StringUtils.trim(String.valueOf(hssfCell.getStringCellValue()));
         } 
     }
}

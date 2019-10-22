package com.utlis;

import com.Dao;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    public void readFile(String path,String type){
        Map<String,Object> map=new HashMap<>();
        map.put("param_type",type);
        int k=1;
        try {
            XSSFWorkbook workbook=new XSSFWorkbook(new FileInputStream(path));
            int len=workbook.getNumberOfSheets();
            for (int i=2;i<len;i++){
                Sheet sheet=workbook.getSheetAt(i);
                int sheetLen=sheet.getLastRowNum();
                for (int l=1;l<=sheetLen;l++) {
                    Row row = sheet.getRow(l);
                    if (row==null){
                        break;
                    }
                    Cell param_en_name=row.getCell(2);
                    Cell param_code=row.getCell(0);
                    if (param_en_name==null){
                        break;
                    }
                    map.put("param_en_name",param_en_name.getStringCellValue());
                    Dao dao=new Dao();
                    Integer id=dao.getData(map);
                    map.put("param_id",id);
                    boolean isMerge = isMergedRegion(sheet, l, param_code.getColumnIndex());
                    //判断是否是合并单元格
                    if (isMerge){
                        map.put("param_code",getMergedRegionValue(sheet, row.getRowNum(), param_code.getColumnIndex()));
                    }else {
                        map.put("param_code",param_code.getStringCellValue());
                    }
                    dao.upData(map);
                    k++;
                    }

            }
            System.out.println("共:"+k+"条");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断指定的单元格是否是合并单元格
     * @param sheet
     * @param row 行下标
     * @param column 列下标
     * @return
     */
    private boolean isMergedRegion(Sheet sheet,int row ,int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取合并单元格的值
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public String getMergedRegionValue(Sheet sheet ,int row , int column){
        int sheetMergeCount = sheet.getNumMergedRegions();

        for(int i = 0 ; i < sheetMergeCount ; i++){
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();

            if(row >= firstRow && row <= lastRow){

                if(column >= firstColumn && column <= lastColumn){
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return getCellValue(fCell) ;
                }
            }
        }

        return null ;
    }

    /**
     * 获取单元格的值
     * @param cell
     * @return
     */
    public String getCellValue(Cell cell){

        if(cell == null) return "";

        if(cell.getCellType() == Cell.CELL_TYPE_STRING){

            return cell.getStringCellValue();

        }else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){

            return String.valueOf(cell.getBooleanCellValue());

        }else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){

            return cell.getCellFormula() ;

        }else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){

            return String.valueOf(cell.getNumericCellValue());

        }
        return "";
    }
}

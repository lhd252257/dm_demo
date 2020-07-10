package com.bifangan.dmDemo.utils;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiUtil {
//	public static String readXls(String path)
//    {
//        String text="";  
//         try 
//         {  
//              FileInputStream is =  new FileInputStream(path);
//              HSSFWorkbook excel=new HSSFWorkbook(is);  
//             //获取第一个sheet  
//             HSSFSheet sheet0=excel.getSheetAt(0);  
//             for (Iterator rowIterator=sheet0.iterator();rowIterator.hasNext();) 
//             {  
//                 HSSFRow row=(HSSFRow) rowIterator.next();  
//                 for (Iterator iterator=row.cellIterator();iterator.hasNext();) 
//                 {  
//                     HSSFCell cell=(HSSFCell) iterator.next();  
//                     //根据单元的的类型 读取相应的结果  
//                     if(cell.getCellType()==CellType.STRING) text+=cell.getStringCellValue()+"\t";  
//                     else if(cell.getCellType()==CellType.NUMERIC) text+=cell.getNumericCellValue()+"\t";  
//                     else if(cell.getCellType()==CellType.FORMULA) text+=cell.getCellFormula()+"\t";  
//                 }  
//               text+="\n";  
//             }          
//         } 
//         catch (Exception e) 
//         {  
//                // TODO Auto-generated catch block  
//                e.printStackTrace();  
//         }  
// 
//         return text; 
//    }
//    public static String readXlsx(String path)
//    {
//         String text="";  
//         try 
//         {  
//              OPCPackage pkg=OPCPackage.open(path);  
//              XSSFWorkbook excel=new XSSFWorkbook(pkg);  
//              //获取第一个sheet  
//              XSSFSheet sheet0=excel.getSheetAt(0);  
//              for (Iterator rowIterator=sheet0.iterator();rowIterator.hasNext();) 
//              {  
//                  XSSFRow row=(XSSFRow) rowIterator.next();  
//                  for (Iterator iterator=row.cellIterator();iterator.hasNext();) 
//                  {  
//                      XSSFCell cell=(XSSFCell) iterator.next();  
//                      //根据单元的的类型 读取相应的结果  
//                      if(cell.getCellType()==CellType.STRING) text+=cell.getStringCellValue()+"\t";  
//                      else if(cell.getCellType()==CellType.NUMERIC) text+=cell.getNumericCellValue()+"\t";  
//                      else if(cell.getCellType()==CellType.FORMULA) text+=cell.getCellFormula()+"\t";  
//                  }  
//                text+="\n";  
//              }          
//         } 
//         catch (Exception e) 
//         {  
//                // TODO Auto-generated catch block  
//                e.printStackTrace();  
//         }  
// 
//         return text;  
//    }

}

package com.qa.api.flip.exelUtil;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ExcelBaseUtil {
        private File src;
        private FileInputStream fileInputStream;
        public static FileOutputStream fileOutputStream;
        static HashMap<String,FileOutputStream> storeFileOutputStream;
        private Workbook workbook;
        private static HashMap<String,Workbook>Excels;
        private static HashMap<String,String>filePaths;
        private Sheet sheet;
        CellStyle style;
        Cell cell;
        ExcelBaseUtil(String fileName,String filePath){
        try{
            this.src=new File(filePath);
            this.fileInputStream=new FileInputStream(this.src);
            if(this.filePaths==null){
                this.filePaths=new HashMap<>();
                this.filePaths.put(fileName,filePath);
            }else{
                this.filePaths.put(fileName,filePath);
            }
            if(getExtension(filePath).equals("xlsx")){
                this.workbook=new XSSFWorkbook(this.fileInputStream);
                if(this.Excels==null){
                    this.Excels=new HashMap<>();
                    System.out.println(" First Time " + fileName);
                    this.Excels.put(fileName,this.workbook);
                }else{
                    System.out.println(" Second Time " + fileName + " ");
                    this.Excels.put(fileName,this.workbook);
                }
            }
        }catch (Exception ae){
            ae.printStackTrace();
        }
        System.out.println("  Size is    "+this.Excels.size());
    }

        public void setExcels(String fileName,String filePath){
        try{
            this.src=new File(filePath);
            this.fileInputStream=new FileInputStream(this.src);
            if(this.filePaths==null){
                this.filePaths=new HashMap<>();
                this.filePaths.put(fileName,filePath);
            }else{
                this.filePaths.put(fileName,filePath);
            }

            if(getExtension(filePath).equals("xlsx")){
                this.workbook=new XSSFWorkbook(this.fileInputStream);
                if(this.Excels==null){
                    this.Excels=new HashMap<>();
                    this.Excels.put(fileName,this.workbook);
                }else{
                    this.Excels.put(fileName,this.workbook);
                }
            }else{
                this.workbook=new HSSFWorkbook(this.fileInputStream);
                if(this.Excels==null){
                    this.Excels=new HashMap<>();
                    this.Excels.put(fileName,this.workbook);
                }else{
                    this.Excels.put(fileName,this.workbook);
                }
            }
            if(this.Excels==null){
                this.Excels=new HashMap<>();
                System.out.println(" First Time " + fileName);
                this.Excels.put(fileName,this.workbook);
            }else{
                System.out.println(" Second Time " + fileName + " ");
                this.Excels.put(fileName,this.workbook);
            }

        }catch (Exception ae){
            ae.printStackTrace();
        }
    }

        public String getExtension(String filePath){
        String extention="";
        try{
            for(int i=filePath.lastIndexOf(".")+1;i<filePath.length();i++){
                extention=extention+filePath.charAt(i);
            }
        }catch (Exception ae){
            ae.printStackTrace();
        }return extention;
    }
        public Workbook getWorkbook(String fileName){
        Workbook workbook=null;
        try{
            for (Map.Entry<String,Workbook> entry: this.Excels.entrySet()) {
                if(entry.getKey().equals(fileName)){
                    workbook=entry.getValue();
                }
            }
        }catch (Exception ae){
            ae.printStackTrace();
        }if(workbook==null){
            throw new NullPointerException("Invalid file Name");
        }else{
            return workbook;
        }
    }
        public FileOutputStream getFileOutputStream(String fileName){
        FileOutputStream fileOutputStream=null;
        try{
            for (Map.Entry<String,FileOutputStream> fileOutputStreamEntry: storeFileOutputStream.entrySet()) {
                if(fileOutputStreamEntry.getKey().equals(fileName)){
                    fileOutputStream=fileOutputStreamEntry.getValue();
                }
            }
        }catch (Exception ae){
            ae.printStackTrace();
        }if(fileOutputStream==null){
            throw new NullPointerException("Invalid file Name");
        }else{
            return fileOutputStream;
        }
    }



        public Sheet getSheet(Workbook workbook,String sheetName){
        Sheet sheet=null;
        try{
            sheet=workbook.getSheet(sheetName);
        }catch (Exception ae){
            ae.printStackTrace();
        }if(sheet==null){
            throw new NullPointerException("Invalid sheetname");
        }else{
            return sheet;
        }
    }

        public Integer getTotalRowCount(Sheet sheet){
        Integer totalRowCount=0;
        try{
            totalRowCount=sheet.getLastRowNum();
        }catch (Exception ae){
            ae.printStackTrace();
        }return totalRowCount;
    }

        public Integer getTotalColumnCount(Sheet sheet,Integer rowNo){
        Integer totalColumnCount=0;
        try{
            totalColumnCount= Integer.valueOf(sheet.getRow(rowNo).getLastCellNum());
        }catch (Exception ae){
            ae.printStackTrace();
        }return totalColumnCount;
    }

        public String getData(Sheet sheet,int rowNo,int colNo){
        String cellValue="";
        CellType cellType;
        try{
            try {
                cellType = sheet.getRow(rowNo).getCell(colNo).getCellType();
            }catch (NullPointerException nullPointerException){
                cellType=CellType.BLANK;
            }
            switch (cellType){
                case STRING:cellValue=sheet.getRow(rowNo).getCell(colNo).getStringCellValue();
                    break;
                case BOOLEAN:cellValue=String.valueOf(sheet.getRow(rowNo).getCell(colNo).getBooleanCellValue());
                    break;
                case NUMERIC:cellValue=String.valueOf(sheet.getRow(rowNo).getCell(colNo).getNumericCellValue());
                    break;
                case BLANK: System.out.println("Cell is blanked");
                    break;
                default:
                    System.out.println("Cell not matched");

            }
        }catch (Exception ae){
            ae.printStackTrace();
        }return cellValue;
    }

        public String getcellValue(Sheet sheet,Integer rowNo,String columnName){
        String cellValue="";
        try {
            for(int i=0;i<sheet.getRow(0).getPhysicalNumberOfCells();i++){
                if(getData(sheet,0,i).equals(columnName));{
                    cellValue=getData(sheet,rowNo,i);
                }
            }
        }catch (Exception ae){
            ae.printStackTrace();
        }return cellValue;
    }

        public void setData(Sheet sheet,int row,int col,String value){
        try{
            sheet.getRow(row).createCell(col).setCellValue(value);
        }catch (NullPointerException exception){
            sheet.createRow(row).createCell(col).setCellValue(value);
        }
    }

        public void setData(Sheet sheet,int row,int col,Boolean value){
        try{
            if(getTotalRowCount(sheet)==row-1){
                sheet.createRow(row).createCell(col).setCellValue(value);
            }else{
                sheet.getRow(row).createCell(col).setCellValue(value);
            }

        }catch (Exception exception){
            exception.printStackTrace();
        }

    }

        public void setData(Sheet sheet,int row,int col,Integer value){
        try{
            if(getTotalRowCount(sheet)==row-1){
                sheet.createRow(row).createCell(col).setCellValue(value);
            }else{
                System.out.println("she " +sheet+ "row " +row+" col " +col+" vals " +value);
                sheet.getRow(row).createCell(col).setCellValue(value);
            }

        }catch (Exception exception){
            exception.printStackTrace();
        }

    }

        public void setData(Sheet sheet,int row,int col,Double value){
        try{
            if(getTotalRowCount(sheet)==row-1){
                sheet.createRow(row).createCell(col).setCellValue(value);
            }else{
                sheet.getRow(row).createCell(col).setCellValue(value);
            }

        }catch (Exception exception){
            exception.printStackTrace();
        }

    }

        public void setD(Sheet sheet,int row,int col,String value){
        try{
            //sheet.getRow(row).createCell(col).setCellFormula(CellType.STRING.toString());
            sheet.getRow(row).createCell(col).setCellValue(value);
            // sheet.getRow(row).createCell(col).setCellFormula(CellType.STRING.toString());
        }catch (NullPointerException exception){
            sheet.createRow(row).createCell(col).setCellValue(value);
        }
    }

        public void openFile(String fileName) throws IOException {
        String filePath=this.filePaths.get(fileName);
        src=new File(filePath);
        this.fileOutputStream=new FileOutputStream(src);
        if(storeFileOutputStream==null){
            storeFileOutputStream=new HashMap<>();
            storeFileOutputStream.put(fileName,fileOutputStream);
        }else {
            storeFileOutputStream.put(fileName,fileOutputStream);
        }

    }

        public void closeFile(String fileName) throws IOException {
        getWorkbook(fileName).write(getFileOutputStream(fileName));
        getWorkbook(fileName).close();
        getFileOutputStream(fileName).close();
    }

        public void setCellBGColor(Workbook workbook,Sheet sheet,int rowNo,int colNo,String bgColor,Cell cell) {
        style = workbook.createCellStyle();
        if (bgColor.equals("GREEN")) {
            style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
        } else if (bgColor.equals("RED")) {
            style.setFillBackgroundColor(IndexedColors.RED.getIndex());
        }
        style.setFillPattern(FillPatternType.BIG_SPOTS);
        cell.setCellStyle(style);
    }

}

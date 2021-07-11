package com.qa.api.flip.exelUtil;

public enum ExcelFilesPath {


    ProductInfoExcel("./src/test/resources/Product_Data.xlsx"),
    ProductRequestExcel("./src/test/resources/requestBodyData.xlsx"),

    ;

    private final String excelFilePath;
    ExcelFilesPath(String path){
        this.excelFilePath=path;
    }

    public String getExcelFilePath(){
        return this.excelFilePath;
    }



}

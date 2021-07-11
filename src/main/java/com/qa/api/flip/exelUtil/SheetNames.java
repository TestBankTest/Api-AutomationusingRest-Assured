package com.qa.api.flip.exelUtil;

public enum SheetNames {

    ProductData_ProductInfoExcel ("ProductInfoExcel"),
    ProductRequestData_ProductInfoExcel ("Product"),
    ;

    private final String sheetName;
    SheetNames(String column){
        this.sheetName=column;
    }

    public String getSheetName(){
        return this.sheetName;
    }


}

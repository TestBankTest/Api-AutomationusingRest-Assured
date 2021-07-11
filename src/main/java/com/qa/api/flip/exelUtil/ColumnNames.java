package com.qa.api.flip.exelUtil;

public enum ColumnNames {

    ;

    private final String columnName;
    ColumnNames(String column){
        this.columnName=column;
    }

    public String getColumnName(){
        return this.columnName;
    }

}

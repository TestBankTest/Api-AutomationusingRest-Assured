package com.qa.api.flip.constants;

public enum FOLDER_PATH_URL {

    ExtentReportsPath (System.getProperty("user.dir")+"/ExtentReports/TestReport.html"),
    ExtentReportsConfigPath (System.getProperty("user.dir")+"/src/test/resources/extentreport.xml"),
    ;

    private final String FOLDER_PATH_URL;
    FOLDER_PATH_URL(String FOLDER_PATH_URL){
        this.FOLDER_PATH_URL=FOLDER_PATH_URL;
    }

    public String getFOLDER_PATH_URL(){
        return this.FOLDER_PATH_URL;
    }




}

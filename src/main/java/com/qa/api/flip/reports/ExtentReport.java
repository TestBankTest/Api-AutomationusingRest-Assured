package com.qa.api.flip.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.api.flip.constants.FOLDER_PATH_URL;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ExtentReport {

    public  static ExtentSparkReporter htmlReporter=null;
    private static ExtentReports extentReports=null;
    private static ExtentTest extentsTest=null;
    private static String timestamp;
    public MediaEntityModelProvider mediaEntityModelProvider;
    FOLDER_PATH_URL extentReportPath;
    FOLDER_PATH_URL extentReportConfigPath;

    public ExtentSparkReporter configureHtmlReporter(){
        try{
            extentReportPath=FOLDER_PATH_URL.ExtentReportsPath;
            extentReportConfigPath=FOLDER_PATH_URL.ExtentReportsConfigPath;
            htmlReporter=new ExtentSparkReporter(new File(extentReportPath.getFOLDER_PATH_URL()));
            htmlReporter.loadConfig(extentReportConfigPath.getFOLDER_PATH_URL());
            this.htmlReporter.config().setDocumentTitle("Demo Automation Report");
            this.htmlReporter.config().setReportName(" Web Automation Report");
            this.htmlReporter.config().setTheme(Theme.DARK);
        }catch (Exception ae){
            ae.printStackTrace();
        }return htmlReporter;
    }
    private ExtentReports configureExtentReport(){
        try{
            extentReports=new ExtentReports();
            extentReports.attachReporter(configureHtmlReporter());
            extentReports.setSystemInfo("HostName","Test");
            extentReports.setSystemInfo("OperatingSystem","OS");
            extentReports.setSystemInfo("DepartmentName","DepartMent");
            extentReports.setSystemInfo("TesterName","Test");
        }catch (Exception ae){
            ae.printStackTrace();
        }return extentReports;
    }

    public void setTestReport(){
        try{
            configureExtentReport();
        }catch (Exception ae){
            ae.printStackTrace();
        }


    }

    public void configureTest(String textName){
        try{
            extentsTest=extentReports.createTest(textName);
        }catch (Exception ae){
            ae.printStackTrace();
        }
    }

    public ExtentTest getExtentsTest(){
        return extentsTest;
    }

    public void configureTestResult(ITestResult testResult){
        try{
            switch (testResult.getStatus()){

                case ITestResult.SUCCESS:
                    extentsTest.log(Status.PASS,"Test Case Passed"+testResult.getName());
                    break;
                case ITestResult.FAILURE:
                    extentsTest.log(Status.FAIL,"Test Case Failed"+testResult.getName());
                    extentsTest.fail(testResult.getThrowable().getMessage());
                    break;
                case ITestResult.SKIP:
                    extentsTest.log(Status.SKIP,"Test Case Skiped"+testResult.getName());
                    break;
                default:
            }
        }catch (Exception ae){
            ae.printStackTrace();
        }
    }

    public void flushReport(){
        try{
            extentReports.flush();
        }catch (Exception ae){
            ae.printStackTrace();
        }
    }

    public String getTimestamp(){
        return new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()).replaceAll(":", "-");
    }

    public String getTestReportFilePath(){
        return this.extentReportPath.getFOLDER_PATH_URL();
    }

















}

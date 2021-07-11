package com.qa.api.flip.testcase.products;

import com.qa.api.flip.constants.API_BASE_URL;
import com.qa.api.flip.constants.API_ENDPOINTS;
import com.qa.api.flip.exelUtil.ReadProductExcel;
import com.qa.api.flip.reports.ExtentManager;
import com.qa.api.flip.response.pojo.product.GetProductDetailsPojo;
import com.qa.api.flip.testbase.BaseTest;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.output.WriterOutputStream;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class RemoveSingleProduct_TestCase extends BaseTest {

    private RequestSpecification requestSpecification;
    private Response response;
    private GetProductDetailsPojo getProductDetailsPojo;
    private ReadProductExcel readProductExcel;


    @BeforeClass(enabled = true)
    public void setupBeforeClass() throws IOException {
        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer), true);
        this.requestSpecification= RestAssured.given().filter(new RequestLoggingFilter(captor)).log().all();
        this.api_base_url= API_BASE_URL.BestBuyAPI;
        this.api_endpoints= API_ENDPOINTS.BestBuyAPI_Products;
        this.requestSpecification.baseUri(this.api_base_url.getAPI_BASE_URL());
        this.requestSpecification.basePath(this.api_endpoints.getAPI_ENDPOINTS()+"/"+id);

        this.response=this.requestSpecification.delete();
        this.testUtil.writeResponseJsonIntoFile(this.response.asByteArray(),"AllProductDetailsPatched");
    }
    @BeforeMethod(enabled = true)
    public void setupBeforeMethod(){
        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer), true);
    }

    @Test(enabled = true,priority = 12)
    public void verifyResponse(){
        SoftAssert softAssert=new SoftAssert();
        this.extentReport.configureTest("verifyResponse");
        ExtentManager.setExtentTest(extentReport.getExtentsTest());
        softAssert.assertEquals(this.response.getStatusCode(),200);
        softAssert.assertEquals(this.response.getStatusLine(),"HTTP/1.1 200 OK");
        softAssert.assertAll();
    }

    @AfterMethod(enabled = true)
    public void setupAfterMethod(ITestResult iTestResult){
        //writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());
        this.extentReport.configureTestResult(iTestResult);
    }
    @AfterClass
    public void setupAfterClass(){

    }
















}

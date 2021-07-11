package com.qa.api.flip.testcase.stores;

import com.qa.api.flip.constants.API_BASE_URL;
import com.qa.api.flip.constants.API_ENDPOINTS;
import com.qa.api.flip.exelUtil.ReadProductExcel;
import com.qa.api.flip.reports.ExtentManager;
import com.qa.api.flip.response.pojo.product.GetProductDetailsPojo;
import com.qa.api.flip.response.pojo.stores.CreateStoreDetailsPojo;
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
import java.util.LinkedList;
import java.util.Map;

public class UpdateSingleStore_TestCase extends BaseTest {


    private RequestSpecification requestSpecification;
    private Response response;
    private ReadProductExcel readProductExcel;
    private Map<String,Object> requestBody;
    private Map<String,Object> services;
    private CreateStoreDetailsPojo createStoreDetailsPojo;

    @BeforeClass
    public void setupBeforeClass() throws IOException {
        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer), true);
        this.requestSpecification= RestAssured.given().filter(new RequestLoggingFilter(captor)).log().all();
        this.api_base_url= API_BASE_URL.BestBuyAPI;
        this.api_endpoints= API_ENDPOINTS.BestBuyAPI_Stores;
        this.requestSpecification.baseUri(this.api_base_url.getAPI_BASE_URL());
        id=6;
        this.requestSpecification.basePath(this.api_endpoints.getAPI_ENDPOINTS()+"/"+id);
        this.requestBody=new LinkedHashMap<>();
        this.requestBody.put("name","Inver Grove Selenium");
        this.requestBody.put("type","wait12");
        this.requestBody.put("address","Virar");
        this.requestBody.put("address2","West");
        this.requestBody.put("city","Mumbai");
        this.requestBody.put("state","MH");
        this.requestBody.put("zip","zp1234");
        this.requestBody.put("lat",0);
        this.requestBody.put("lng",0);
        this.requestBody.put("hours","8:30");
        this.services=new LinkedHashMap<>();
        this.requestBody.put("services",this.services);
        this.requestSpecification.body(this.requestBody);
        this.requestSpecification.headers("Content-Type","application/json");
        this.requestSpecification.contentType(ContentType.JSON);
        this.response=this.requestSpecification.patch();
        this.testUtil.writeResponseJsonIntoFile(this.response.asByteArray(),"Create StoreDetails");
        this.createStoreDetailsPojo=this.objectMapper.readValue(this.response.getBody().asString(),CreateStoreDetailsPojo.class);
    }

    @BeforeMethod(enabled = true)
    public void setupBeforeMethod(){
        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer), true);
    }

    @Test(enabled = true,priority = 0)
    public void verifyRequestResponse(){
        SoftAssert softAssert=new SoftAssert();
        this.extentReport.configureTest("verifyResponse");
        ExtentManager.setExtentTest(extentReport.getExtentsTest());
        softAssert.assertEquals(this.response.getStatusCode(),200);
        softAssert.assertEquals(this.response.getStatusLine(),"HTTP/1.1 200 OK");
        softAssert.assertEquals(this.createStoreDetailsPojo.getName(),"Inver Grove Selenium");
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

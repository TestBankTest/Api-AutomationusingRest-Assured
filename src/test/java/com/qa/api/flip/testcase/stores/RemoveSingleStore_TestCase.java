package com.qa.api.flip.testcase.stores;

import com.qa.api.flip.constants.API_BASE_URL;
import com.qa.api.flip.constants.API_ENDPOINTS;
import com.qa.api.flip.exelUtil.ReadProductExcel;
import com.qa.api.flip.reports.ExtentManager;
import com.qa.api.flip.response.pojo.product.GetProductDetailsPojo;
import com.qa.api.flip.response.pojo.stores.AllStoreDetailsPojo;
import com.qa.api.flip.response.pojo.stores.CreateStoreDetailsPojo;
import com.qa.api.flip.testbase.BaseTest;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.output.WriterOutputStream;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;

public class RemoveSingleStore_TestCase extends BaseTest {


    private RequestSpecification requestSpecification;
    private Response response;
    private ReadProductExcel readProductExcel;
    private CreateStoreDetailsPojo createStoreDetailsPojo;

    @BeforeClass(enabled = true)
    public void setupBeforeClass() throws IOException {
        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer), true);
        this.requestSpecification= RestAssured.given().filter(new RequestLoggingFilter(captor)).log().all();
        this.api_base_url= API_BASE_URL.BestBuyAPI;
        this.api_endpoints= API_ENDPOINTS.BestBuyAPI_Stores;
        this.requestSpecification.baseUri(this.api_base_url.getAPI_BASE_URL());
        id=4;
        this.requestSpecification.basePath(this.api_endpoints.getAPI_ENDPOINTS()+"/"+id);

        this.response=this.requestSpecification.delete();
        this.testUtil.writeResponseJsonIntoFile(this.response.asByteArray(),"AllProductDetailsDelete");
        createStoreDetailsPojo=this.objectMapper.readValue(this.response.getBody().asString(), CreateStoreDetailsPojo.class);
    }

    @BeforeMethod(enabled = true)
    public void setupBeforeMethod(){
        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer), true);
    }
    @Test(enabled = true,priority = 0)
    public void TC01(){
        SoftAssert softAssert=new SoftAssert();
        this.extentReport.configureTest("verifyResponse");
        ExtentManager.setExtentTest(extentReport.getExtentsTest());
        softAssert.assertEquals(this.response.getStatusCode(),200);
        softAssert.assertEquals(this.response.getStatusLine(),"HTTP/1.1 200 OK");
        softAssert.assertAll();

    }

    @Test(enabled = true,priority = 1)
    public void verifyRequestResponse(){
        SoftAssert softAssert=new SoftAssert();
        this.extentReport.configureTest("verifyResponse");
        ExtentManager.setExtentTest(extentReport.getExtentsTest());
        softAssert.assertEquals(this.response.getStatusCode(),201);
        softAssert.assertEquals(this.response.getStatusLine(),"HTTP/1.1 201 Created");
        softAssert.assertEquals(String.valueOf(createStoreDetailsPojo.getId()).length()>1,true,"Id not created");
        softAssert.assertEquals(this.createStoreDetailsPojo.getName(),"Best12");
        softAssert.assertEquals(this.createStoreDetailsPojo.getType(),"wait12");
        softAssert.assertEquals(this.createStoreDetailsPojo.getAddress(),"Virar");
        softAssert.assertEquals(this.createStoreDetailsPojo.getAddress2(),"West");
        softAssert.assertEquals(this.createStoreDetailsPojo.getCity(),"Mumbai");
        softAssert.assertEquals(this.createStoreDetailsPojo.getState(),"MH");
        softAssert.assertEquals(this.createStoreDetailsPojo.getZip(),"zp1234");
        softAssert.assertEquals(this.createStoreDetailsPojo.getLat(),new Integer(0));
        softAssert.assertEquals(this.createStoreDetailsPojo.getLng(),new Integer(0));
        softAssert.assertEquals(this.createStoreDetailsPojo.getHours(),"8:30");
        softAssert.assertAll();
    }

    @AfterMethod
    public void setupAfterMethod(ITestResult iTestResult){
        this.extentReport.configureTestResult(iTestResult);
    }
    @AfterClass
    public void setupAfterClass(){

    }

}

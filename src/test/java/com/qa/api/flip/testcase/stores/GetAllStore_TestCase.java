package com.qa.api.flip.testcase.stores;

import com.qa.api.flip.constants.API_BASE_URL;
import com.qa.api.flip.constants.API_ENDPOINTS;
import com.qa.api.flip.exelUtil.ReadProductExcel;
import com.qa.api.flip.reports.ExtentManager;
import com.qa.api.flip.response.pojo.product.GetProductDetailsPojo;
import com.qa.api.flip.response.pojo.stores.AllStoreDetailsPojo;
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

public class GetAllStore_TestCase extends BaseTest {

    private RequestSpecification requestSpecification;
    private Response response;
    private AllStoreDetailsPojo allStoreDetailsPojo;
    private ReadProductExcel readProductExcel;

    @BeforeClass(enabled = true)
    public void setupBeforeClass() throws IOException {
        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer), true);
        this.requestSpecification= RestAssured.given().filter(new RequestLoggingFilter(captor)).log().all();
        this.api_base_url= API_BASE_URL.BestBuyAPI;
        this.api_endpoints= API_ENDPOINTS.BestBuyAPI_Stores;
        this.requestSpecification.baseUri(this.api_base_url.getAPI_BASE_URL());
        this.requestSpecification.basePath(this.api_endpoints.getAPI_ENDPOINTS());
        this.response=this.requestSpecification.get();
        this.testUtil.writeResponseJsonIntoFile(this.response.asByteArray(),"AllStoresDetails");
        allStoreDetailsPojo=this.objectMapper.readValue(this.response.getBody().asString(), AllStoreDetailsPojo.class);
    }

    @BeforeMethod(enabled = true)
    public void setupBeforeMethod(){
        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer), true);
    }
    @Test(enabled = true)
    public void writeResponseIntoJasonFile(){
        SoftAssert softAssert=new SoftAssert();
        this.extentReport.configureTest("writeResponseIntoReport");
        ExtentManager.setExtentTest(extentReport.getExtentsTest());
        writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());
        //softAssert.assertEquals(Integer.valueOf(this.productDetailsPojo.getTotal().toString()),Integer.valueOf("51971"));
        softAssert.assertAll();
        System.out.println("All body " +this.response.getBody().asPrettyString());

    }
    @AfterMethod(enabled = true)
    public void setupAfterMethod(ITestResult iTestResult){
        this.extentReport.configureTestResult(iTestResult);
    }
    @AfterClass
    public void setupAfterClass(){

    }
}

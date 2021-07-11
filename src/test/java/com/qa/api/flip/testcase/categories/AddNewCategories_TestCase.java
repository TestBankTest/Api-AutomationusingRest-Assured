package com.qa.api.flip.testcase.categories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qa.api.flip.constants.API_BASE_URL;
import com.qa.api.flip.constants.API_ENDPOINTS;
import com.qa.api.flip.exelUtil.ReadProductExcel;
import com.qa.api.flip.reports.ExtentManager;
import com.qa.api.flip.response.pojo.categories.CreateCategoriesDetailsPojo;
import com.qa.api.flip.response.pojo.product.CreateProductDetailsPojo;
import com.qa.api.flip.response.pojo.product.ProductDetailsPojo;
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

public class AddNewCategories_TestCase extends BaseTest {


    private RequestSpecification requestSpecification;
    private Response response;
    private ProductDetailsPojo productDetailsPojo;
    private ReadProductExcel readProductExcel;
    private Map<String,Object> requestBody;
    CreateCategoriesDetailsPojo createCategoriesDetailsPojo;

    @BeforeClass
    public void setupBeforeClass() throws IOException {
        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer), true);
        this.requestSpecification= RestAssured.given().filter(new RequestLoggingFilter(captor)).log().all();
        this.api_base_url= API_BASE_URL.BestBuyAPI;
        this.api_endpoints= API_ENDPOINTS.BestBuyAPI_Categories;
        this.requestSpecification.baseUri(this.api_base_url.getAPI_BASE_URL());
        this.requestSpecification.basePath(this.api_endpoints.getAPI_ENDPOINTS());
        this.requestBody=new LinkedHashMap<>();
        this.requestBody.put("name","Test123");
        this.requestBody.put("id","Selenium123");
        this.requestSpecification.body(this.requestBody);
        this.requestSpecification.headers("Content-Type","application/json");
        this.requestSpecification.contentType(ContentType.JSON);
        this.response=this.requestSpecification.post();
        this.testUtil.writeResponseJsonIntoFile(this.response.asByteArray(),"Create ProductDetails");
        this.createCategoriesDetailsPojo=this.objectMapper.readValue(this.response.getBody().asString(),CreateCategoriesDetailsPojo.class);
    }

    @BeforeMethod(enabled = true)
    public void setupBeforeMethod(){
        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer), true);
    }

    @Test(enabled = true,priority = 6)
    public void writeResponseIntoReports(){
        SoftAssert softAssert=new SoftAssert();
        this.extentReport.configureTest("writeResponseIntoReport");
        ExtentManager.setExtentTest(extentReport.getExtentsTest());
        writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());
        softAssert.assertEquals(this.response.getStatusCode(),201,"Status code not matched");
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

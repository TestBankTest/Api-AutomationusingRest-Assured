package com.qa.api.flip.testcase.categories;

import com.qa.api.flip.constants.API_BASE_URL;
import com.qa.api.flip.constants.API_ENDPOINTS;
import com.qa.api.flip.exelUtil.ReadProductExcel;
import com.qa.api.flip.reports.ExtentManager;
import com.qa.api.flip.response.pojo.categories.AllCategoriesDetailsPojo;
import com.qa.api.flip.response.pojo.product.ProductDetailsPojo;
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

public class GetAllCategories_TestCase extends BaseTest {


    private RequestSpecification requestSpecification;
    private Response response;
    private ReadProductExcel readProductExcel;
    private AllCategoriesDetailsPojo allCategoriesDetailsPojo;

    @BeforeClass
    public void setupBeforeClass() throws IOException {

        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer), true);
        this.requestSpecification= RestAssured.given().filter(new RequestLoggingFilter(captor)).log().all();
        this.api_base_url= API_BASE_URL.BestBuyAPI;
        this.api_endpoints= API_ENDPOINTS.BestBuyAPI_Categories;
        this.requestSpecification.baseUri(this.api_base_url.getAPI_BASE_URL());
        this.requestSpecification.basePath(this.api_endpoints.getAPI_ENDPOINTS());
        this.response=this.requestSpecification.get();
        System.out.println("Body is  " +this.response.getBody().prettyPrint());
        this.testUtil.writeResponseJsonIntoFile(this.response.asByteArray(),"AllProductDetails");
        this.allCategoriesDetailsPojo=this.objectMapper.readValue(this.response.getBody().asString(),AllCategoriesDetailsPojo.class);
    }

    @BeforeMethod (enabled = true)
    public void setupBeforeMethod(){
        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer), true);
    }

    @Test(enabled = true,priority = 0)
    public void writeResponseIntoReport() {
        SoftAssert softAssert=new SoftAssert();
        this.extentReport.configureTest("writeResponseIntoReport");
        ExtentManager.setExtentTest(extentReport.getExtentsTest());
        writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());
        softAssert.assertEquals(Integer.valueOf(this.allCategoriesDetailsPojo.getTotal().toString()),Integer.valueOf("51971"));
        softAssert.assertAll();
    }
    @Test
    public void TC01(){
        for(int i=0;i<this.allCategoriesDetailsPojo.getData().size();i++){
            for(int j=0;j<this.allCategoriesDetailsPojo.getData().get(i).getSubCategories().size();j++){
                System.out.println(" B " +this.allCategoriesDetailsPojo.getData().get(i).getSubCategories().get(j).getName());
            }
        }
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

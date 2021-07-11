package com.qa.api.flip.testcase.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qa.api.flip.constants.API_BASE_URL;
import com.qa.api.flip.constants.API_ENDPOINTS;
import com.qa.api.flip.exelUtil.ReadProductExcel;
import com.qa.api.flip.response.pojo.product.ProductDetailsPojo;
import com.qa.api.flip.response.pojo.services.AllServicesDetailsPojo;
import com.qa.api.flip.testbase.BaseTest;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.output.WriterOutputStream;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;

public class GetAllService_TestCase extends BaseTest {

    private RequestSpecification requestSpecification;
    private Response response;
    private AllServicesDetailsPojo allServicesDetailsPojo;
    private ReadProductExcel readProductExcel;


    @BeforeClass(enabled = true)
    public void setupBeforeClass() throws IOException {
        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer), true);
        this.requestSpecification= RestAssured.given().filter(new RequestLoggingFilter(captor)).log().all();
        this.api_base_url= API_BASE_URL.BestBuyAPI;
        this.api_endpoints= API_ENDPOINTS.BestBuyAPI_Services;
        this.requestSpecification.baseUri(this.api_base_url.getAPI_BASE_URL());
        this.requestSpecification.basePath(this.api_endpoints.getAPI_ENDPOINTS());
        this.response=this.requestSpecification.get();
        this.testUtil.writeResponseJsonIntoFile(this.response.asByteArray(),"AllProductDetails");
        this.allServicesDetailsPojo=this.objectMapper.readValue(this.response.getBody().asString(),AllServicesDetailsPojo.class);

    }
    @BeforeMethod (enabled = true)
    public void setupBeforeMethod(){
        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer), true);
    }
    @Test
    public void TC01(){
     // Test case is pending.
        System.out.println("bobo " +this.response.getBody().asPrettyString());
        for(int i=0;i<this.allServicesDetailsPojo.getData().size();i++){
            System.out.println(" Name is " +this.allServicesDetailsPojo.getData().get(i).getName());
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

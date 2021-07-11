package com.qa.api.flip.testcase.stores;

import com.qa.api.flip.constants.API_BASE_URL;
import com.qa.api.flip.constants.API_ENDPOINTS;
import com.qa.api.flip.exelUtil.ReadProductExcel;
import com.qa.api.flip.response.pojo.product.GetProductDetailsPojo;
import com.qa.api.flip.testbase.BaseTest;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.output.WriterOutputStream;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;

public class GetSingleStore_TestCase extends BaseTest {

    private RequestSpecification requestSpecification;
    private Response response;
    private ReadProductExcel readProductExcel;

    @BeforeClass
    public void setupBeforeClass() throws IOException {

        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer), true);
        this.requestSpecification= RestAssured.given().filter(new RequestLoggingFilter(captor)).log().all();
        this.api_base_url= API_BASE_URL.BestBuyAPI;
        this.api_endpoints= API_ENDPOINTS.BestBuyAPI_Products;
        this.requestSpecification.baseUri(this.api_base_url.getAPI_BASE_URL());
        id=8924;
        this.requestSpecification.basePath(this.api_endpoints.getAPI_ENDPOINTS()+"/"+id);
        this.response=this.requestSpecification.get();
        this.testUtil.writeResponseJsonIntoFile(this.response.asByteArray(),"AllProductDetailsFinal");
        //this.getProductDetailsPojo=this.objectMapper.readValue(this.response.getBody().asString(), GetProductDetailsPojo.class);

    }

    @BeforeMethod
    public void setupBeforeMethod(){

    }
    @Test
    public void TC01(){
    System.out.println("Body is " +this.response.getBody().asPrettyString());
    }
    @AfterMethod
    public void setupAfterMethod(){

    }
    @AfterClass
    public void setupAfterClass(){

    }
}

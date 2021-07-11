package com.qa.api.flip.testcase.categories;

import com.qa.api.flip.constants.API_BASE_URL;
import com.qa.api.flip.constants.API_ENDPOINTS;
import com.qa.api.flip.exelUtil.ReadProductExcel;
import com.qa.api.flip.response.pojo.categories.AllCategoriesDetailsPojo;
import com.qa.api.flip.response.pojo.categories.CreateCategoriesDetailsPojo;
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

public class RemoveSingleCategories_TestCase extends BaseTest {

    private RequestSpecification requestSpecification;
    private Response response;
    private ReadProductExcel readProductExcel;
    private CreateCategoriesDetailsPojo createCategoriesDetailsPojo;

    @BeforeClass
    public void setupBeforeClass() throws IOException {
        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer), true);
        this.requestSpecification= RestAssured.given().filter(new RequestLoggingFilter(captor)).log().all();
        this.api_base_url= API_BASE_URL.BestBuyAPI;
        this.api_endpoints= API_ENDPOINTS.BestBuyAPI_Categories;
        this.requestSpecification.baseUri(this.api_base_url.getAPI_BASE_URL());
        id="abcat0010000";
        this.requestSpecification.basePath(this.api_endpoints.getAPI_ENDPOINTS()+"/"+id);
        this.response=this.requestSpecification.delete();
        this.testUtil.writeResponseJsonIntoFile(this.response.asByteArray(),"AllProductDetailsPatched");
        this.createCategoriesDetailsPojo=this.objectMapper.readValue(this.response.getBody().asString(), CreateCategoriesDetailsPojo.class);
    }

    @BeforeMethod (enabled = true)
    public void setupBeforeMethod(){
        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer), true);
    }

    @Test
    public void TC01(){
     System.out.println("nam is " +this.createCategoriesDetailsPojo.getName());
    }
    @AfterMethod
    public void setupAfterMethod(){

    }
    @AfterClass
    public void setupAfterClass(){

    }


}

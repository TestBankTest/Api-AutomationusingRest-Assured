package com.qa.api.flip.testcase.categories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qa.api.flip.constants.API_BASE_URL;
import com.qa.api.flip.constants.API_ENDPOINTS;
import com.qa.api.flip.exelUtil.ReadProductExcel;
import com.qa.api.flip.response.pojo.categories.CreateCategoriesDetailsPojo;
import com.qa.api.flip.response.pojo.product.GetProductDetailsPojo;
import com.qa.api.flip.testbase.BaseTest;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.output.WriterOutputStream;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class UpdateSingleCategories_TestCase extends BaseTest {

    private RequestSpecification requestSpecification;
    private Response response;
    private ReadProductExcel readProductExcel;
    private Map<String,Object> requestBody;
    private CreateCategoriesDetailsPojo createCategoriesDetailsPojo;


    @BeforeClass
    public void setupBeforeClass() throws IOException {
        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer), true);
        this.requestSpecification= RestAssured.given().filter(new RequestLoggingFilter(captor)).log().all();
        this.api_base_url= API_BASE_URL.BestBuyAPI;
        this.api_endpoints= API_ENDPOINTS.BestBuyAPI_Categories;
        this.requestSpecification.baseUri(this.api_base_url.getAPI_BASE_URL());
        id="pcmcat138100050024";
        this.requestSpecification.basePath(this.api_endpoints.getAPI_ENDPOINTS()+"/"+id);
        this.requestBody=new LinkedHashMap<>();
        this.requestBody.put("id","pcmcat138100050024");
        this.requestBody.put("name","Test Selenium");
        this.requestSpecification.body(this.requestBody);
        this.requestSpecification.headers("Content-Type","application/json");
        this.requestSpecification.contentType(ContentType.JSON);

        this.response=this.requestSpecification.patch();
        this.testUtil.writeResponseJsonIntoFile(this.response.asByteArray(),"AllProductDetailsPatched");
        this.createCategoriesDetailsPojo=this.objectMapper.readValue(this.response.getBody().asString(), CreateCategoriesDetailsPojo.class);


    }

    @BeforeMethod(enabled = true)
    public void setupBeforeMethod(){
        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer), true);
    }
    @Test
    public void TC01(){
        System.out.println("Updated name is " +this.createCategoriesDetailsPojo.getName());

    }
    @AfterMethod
    public void setupAfterMethod(){

    }
    @AfterClass
    public void setupAfterClass(){

    }



}

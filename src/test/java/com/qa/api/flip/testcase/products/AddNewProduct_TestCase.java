package com.qa.api.flip.testcase.products;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.qa.api.flip.constants.API_BASE_URL;
import com.qa.api.flip.constants.API_ENDPOINTS;
import com.qa.api.flip.exelUtil.ReadProductExcel;
import com.qa.api.flip.reports.ExtentManager;
import com.qa.api.flip.response.pojo.product.CreateProductDetailsPojo;
import com.qa.api.flip.response.pojo.product.ProductDetailsPojo;
import com.qa.api.flip.testbase.BaseTest;
import com.qa.api.flip.utils.TestDataUtil_Product;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.apache.commons.io.output.WriterOutputStream;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class AddNewProduct_TestCase extends BaseTest {

    private RequestSpecification requestSpecification;
    private QueryableRequestSpecification queryableRequestSpecification;
    private Response response;
    private HashMap<String,Object> requestBody;
    private CreateProductDetailsPojo createProductDetailsPojo;
    TestDataUtil_Product testDataUtil_product;
    Integer productID=0;

    @BeforeClass(enabled = true)
    public void setupBeforeClass() throws IOException {
        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer), true);
        this.testDataUtil_product=new TestDataUtil_Product();
        this.requestSpecification= RestAssured.given().filter(new RequestLoggingFilter(captor)).log().all();
        this.api_base_url= API_BASE_URL.BestBuyAPI;
        this.api_endpoints= API_ENDPOINTS.BestBuyAPI_Products;
        this.requestSpecification.baseUri(this.api_base_url.getAPI_BASE_URL());
        this.requestSpecification.basePath(this.api_endpoints.getAPI_ENDPOINTS());
        //=======================================================================================
        this.requestBody=this.testDataUtil_product.generateRequestBodyForProduct(5,true,5,true,3,2,4,true,5,true,5,true,6,true,5,true,6,true);
        this.requestSpecification.body(this.requestBody);
        this.requestSpecification.headers("Content-Type","application/json");
        this.requestSpecification.contentType(ContentType.JSON);
        this.queryableRequestSpecification= SpecificationQuerier.query(this.requestSpecification);
        this.response=this.requestSpecification.post();
        this.createProductDetailsPojo=this.objectMapper.readValue(this.response.getBody().asString(),CreateProductDetailsPojo.class);
    }
    @BeforeMethod(enabled = false)
    public void setupBeforeMethod(){
        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer), true);
    }


    @Test(enabled = true,priority = 0)
    public void verifyStatusLine() throws IOException {
        SoftAssert softAssert=new SoftAssert();
        this.extentReport.configureTest("verifyStatusLine");
        ExtentManager.setExtentTest(extentReport.getExtentsTest());
        softAssert.assertEquals(this.response.getStatusLine(),"HTTP/1.1 201 Created"," Status Line not matched");
        softAssert.assertEquals(this.response.getStatusCode(),201," Status Code not matched");
        softAssert.assertEquals(this.response.getContentType(),"application/json; charset=utf-8"," Content Type not matched");
        softAssert.assertAll();
    }

    @Test(enabled = true,priority = 1)
    public void verifyHeaders(){
        SoftAssert softAssert=new SoftAssert();
        this.extentReport.configureTest("verifyHeaders");
        ExtentManager.setExtentTest(extentReport.getExtentsTest());
        softAssert.assertEquals(this.response.getHeader("Allow"),"GET,POST,PUT,PATCH,DELETE","Allow header not matched");
        softAssert.assertEquals(this.response.getHeader("Content-Type"),"application/json; charset=utf-8","Content-Type header not matched");
        softAssert.assertAll();
    }

    @Test(enabled = true,priority = 2)
    public void verifyRequestBody() throws IOException {
        SoftAssert softAssert=new SoftAssert();
        this.extentReport.configureTest("verifyRequestBody");
        ExtentManager.setExtentTest(extentReport.getExtentsTest());
        productID=this.createProductDetailsPojo.getId();
        softAssert.assertTrue(productID>0," productID not created");

        for(Map.Entry<String,Object> Entry : this.requestBody.entrySet()) {
            if(Entry.getKey().equals("image")){
                System.out.println(" Actual image : " + Entry.getValue()+ " Expected image: "+this.createProductDetailsPojo.getImage());
                 softAssert.assertEquals(Entry.getValue(),this.createProductDetailsPojo.getImage()," Image Name not matched");
            }else if(Entry.getKey().equals("shipping")){
                System.out.println(" Actual shipping : " + Entry.getValue()+ " Expected shipping: "+this.createProductDetailsPojo.getShipping());
                softAssert.assertEquals(Entry.getValue(),this.createProductDetailsPojo.getShipping()," Shipping name not matched");
            }else if(Entry.getKey().equals("price")){
                System.out.println(" Actual price : " + Entry.getValue()+ " Expected price: "+this.createProductDetailsPojo.getPrice());
                softAssert.assertEquals(Entry.getValue(),this.createProductDetailsPojo.getPrice()," Price not matched");
            }else if(Entry.getKey().equals("name")){
                System.out.println(" Actual name : " + Entry.getValue()+ " Expected name: "+this.createProductDetailsPojo.getName());
                softAssert.assertEquals(Entry.getValue(),this.createProductDetailsPojo.getName()," Name not matched");
            }else if(Entry.getKey().equals("upc")){
                System.out.println(" Actual upc : " + Entry.getValue()+ " Expected upc: "+this.createProductDetailsPojo.getUpc());
                softAssert.assertEquals(Entry.getValue(),this.createProductDetailsPojo.getUpc()," Upc not matched");
            }else if(Entry.getKey().equals("description")){
                System.out.println(" Actual description : " + Entry.getValue()+ " Expected description: "+this.createProductDetailsPojo.getDescription());
                softAssert.assertEquals(Entry.getValue(),this.createProductDetailsPojo.getDescription()," Description not matched");
            }else if(Entry.getKey().equals("model")){
                System.out.println(" Actual model : " + Entry.getValue()+ " Expected model: "+this.createProductDetailsPojo.getModel());
                softAssert.assertEquals(Entry.getValue(),this.createProductDetailsPojo.getModel()," Model not matched");
            }else if(Entry.getKey().equals("type")){
                System.out.println(" Actual type : " + Entry.getValue()+ " Expected type: "+this.createProductDetailsPojo.getType());
                softAssert.assertEquals(Entry.getValue(),this.createProductDetailsPojo.getType()," Type not matched");
            }else if(Entry.getKey().equals("url")){
                System.out.println(" Actual url : " + Entry.getValue()+ " Expected url: "+this.createProductDetailsPojo.getUrl());
                softAssert.assertEquals(Entry.getValue(),this.createProductDetailsPojo.getUrl()," Url not matched");
            }else if(Entry.getKey().equals("manufacturer")){
                System.out.println(" Actual manufacturer : " + Entry.getValue()+ " Expected manufacturer: "+this.createProductDetailsPojo.getManufacturer());
                softAssert.assertEquals(Entry.getValue(),this.createProductDetailsPojo.getManufacturer()," Manufacturer not matched");
            }
        }
        softAssert.assertAll();
    }

    @Test(enabled = true,priority = 3)
    public void verifyRequest(){
        System.out.println(" " +this.queryableRequestSpecification.getBasePath());
        System.out.println(" " +this.queryableRequestSpecification.getBaseUri());
        System.out.println(" " +this.queryableRequestSpecification.getContentType());
        System.out.println(" " +this.queryableRequestSpecification.getURI());
        System.out.println(" " +this.queryableRequestSpecification.getMethod());
    }


    @AfterMethod(enabled = false)
    public void setupAfterMethod(ITestResult iTestResult) throws IOException {
        this.testUtil.writeResponseJsonIntoFile(this.response.asByteArray(),"Create ProductDetails");
        writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());
        this.extentReport.configureTestResult(iTestResult);
    }
    @AfterClass(enabled = false)
    public void setupAfterClass(){
        ExtentManager.setExtentTest(extentReport.getExtentsTest());
        writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());
    }
}

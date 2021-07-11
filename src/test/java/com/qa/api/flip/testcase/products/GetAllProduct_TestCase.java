package com.qa.api.flip.testcase.products;

import com.qa.api.flip.constants.API_BASE_URL;
import com.qa.api.flip.constants.API_ENDPOINTS;
import com.qa.api.flip.exelUtil.ExcelFilesNames;
import com.qa.api.flip.exelUtil.ExcelFilesPath;
import com.qa.api.flip.exelUtil.ReadProductExcel;
import com.qa.api.flip.exelUtil.SheetNames;
import com.qa.api.flip.reports.ExtentManager;
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

public class GetAllProduct_TestCase extends BaseTest {

    private RequestSpecification requestSpecification;
    private Response response;
    private ProductDetailsPojo productDetailsPojo;
    private ReadProductExcel readProductExcel;

    @BeforeClass(enabled = true)
    public void setupBeforeClass() throws IOException {
        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer), true);
        this.requestSpecification= RestAssured.given().filter(new RequestLoggingFilter(captor)).log().all();
        this.api_base_url=API_BASE_URL.BestBuyAPI;
        this.api_endpoints=API_ENDPOINTS.BestBuyAPI_Products;
        this.requestSpecification.baseUri(this.api_base_url.getAPI_BASE_URL());
        this.requestSpecification.basePath(this.api_endpoints.getAPI_ENDPOINTS());
        this.response=this.requestSpecification.get();
        this.testUtil.writeResponseJsonIntoFile(this.response.asByteArray(),"AllProductDetails");
        this.productDetailsPojo=this.objectMapper.readValue(this.response.getBody().asString(),ProductDetailsPojo.class);
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
        softAssert.assertEquals(Integer.valueOf(this.productDetailsPojo.getTotal().toString()),Integer.valueOf("51979"));
        System.out.println(" Body is " +this.response.getBody().prettyPrint());
        softAssert.assertAll();
    }
    @Test(enabled = false,priority = 1)
    public void verifyTotalCount() {
        SoftAssert softAssert=new SoftAssert();
        this.extentReport.configureTest("verifyTotalCount");
        ExtentManager.setExtentTest(extentReport.getExtentsTest());
        softAssert.assertEquals(Integer.valueOf(this.productDetailsPojo.getTotal().toString()),Integer.valueOf("51979"));
        softAssert.assertAll();
    }
    @Test(enabled = false,priority = 2)
    public void verifyLimit(){
        SoftAssert softAssert=new SoftAssert();
        this.extentReport.configureTest("verifyLimit");
        ExtentManager.setExtentTest(extentReport.getExtentsTest());
        softAssert.assertEquals(Integer.valueOf(this.productDetailsPojo.getLimit().toString()),Integer.valueOf("10"));
        softAssert.assertAll();
    }
    @Test(enabled = false,priority = 3)
    public void verifySkip(){
        SoftAssert softAssert=new SoftAssert();
        this.extentReport.configureTest("verifySkip");
        ExtentManager.setExtentTest(extentReport.getExtentsTest());
        softAssert.assertEquals(Integer.valueOf(this.productDetailsPojo.getSkip().toString()),Integer.valueOf("0"));
        softAssert.assertAll();
    }
    @Test(enabled = false,priority = 4)
    public void verifyTotalSize(){
        SoftAssert softAssert=new SoftAssert();
        this.extentReport.configureTest("verifyTotalSize");
        ExtentManager.setExtentTest(extentReport.getExtentsTest());
        softAssert.assertEquals(Integer.valueOf(this.productDetailsPojo.getTotal()),Integer.valueOf("51979"));
        softAssert.assertAll();
    }
    @Test(enabled = false,priority = 5,dataProvider = "ProductDetails")
    public void verifyProductDetails(String id,String name,String type,String price,String upc,String shipping,String description,String manufacturer,String model,String url,String image,String createdAt,String updatedAt) {
        SoftAssert softAssert=new SoftAssert();
        this.extentReport.configureTest("verifyProductDetails");
        ExtentManager.setExtentTest(extentReport.getExtentsTest());
        //==== Verify Product Details ===========================

        for(int i=0;i<productDetailsPojo.getData().size();i++){
            if(productDetailsPojo.getData().get(i).getId().toString().equals(id)){
                softAssert.assertEquals(productDetailsPojo.getData().get(i).getName(),name);
                softAssert.assertEquals(productDetailsPojo.getData().get(i).getType(),type);
                softAssert.assertEquals(productDetailsPojo.getData().get(i).getPrice(),price);
                softAssert.assertEquals(productDetailsPojo.getData().get(i).getUpc(),upc);
                softAssert.assertEquals(productDetailsPojo.getData().get(i).getShipping(),shipping);
                softAssert.assertEquals(productDetailsPojo.getData().get(i).getDescription(),description);
                softAssert.assertEquals(productDetailsPojo.getData().get(i).getManufacturer(),manufacturer);
                softAssert.assertEquals(productDetailsPojo.getData().get(i).getModel(),model);
                softAssert.assertEquals(productDetailsPojo.getData().get(i).getUrl(),url);
                softAssert.assertEquals(productDetailsPojo.getData().get(i).getImage(),image);
                softAssert.assertEquals(productDetailsPojo.getData().get(i).getCreatedAt(),createdAt);
                softAssert.assertEquals(productDetailsPojo.getData().get(i).getUpdatedAt(),updatedAt);
                for(int j=0;j<productDetailsPojo.getData().get(i).getCategories().size();j++){
                    if(productDetailsPojo.getData().get(i).getCategories().get(j).getId().equals("abcat0208002")){
                       softAssert.assertEquals(productDetailsPojo.getData().get(i).getCategories().get(j).getName(),"Alkaline Batteries");
                        softAssert.assertEquals(productDetailsPojo.getData().get(i).getCategories().get(j).getCreatedAt(),"2016-11-17T17:57:04.285Z");
                        softAssert.assertEquals(productDetailsPojo.getData().get(i).getCategories().get(j).getUpdatedAt(),"2016-11-17T17:57:04.285Z");
                    }
                }
            }
        }
      softAssert.assertAll();
    }
    @DataProvider(name = "ProductDetails")
    public Object[][] getProductDetails(){
        ExcelFilesPath excelFilesPath=ExcelFilesPath.ProductInfoExcel;
        SheetNames sheetName=SheetNames.ProductData_ProductInfoExcel;
        this.readProductExcel=new ReadProductExcel(ExcelFilesNames.Product_Data.toString(),excelFilesPath.getExcelFilePath());
        return this.readProductExcel.getProductDetails(ExcelFilesNames.Product_Data.toString(),sheetName.getSheetName(),1);
    }
    @AfterMethod(enabled = true)
    public void setupAfterMethod(ITestResult iTestResult){
        writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());
        this.extentReport.configureTestResult(iTestResult);
    }
    @AfterClass(enabled = true)
    public void setupAfterClass(){

    }
}

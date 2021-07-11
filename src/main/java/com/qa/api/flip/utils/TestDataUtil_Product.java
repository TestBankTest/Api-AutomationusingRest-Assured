package com.qa.api.flip.utils;

import com.google.gson.Gson;
import com.qa.api.flip.exelUtil.ExcelFilesNames;
import com.qa.api.flip.exelUtil.ExcelFilesPath;
import com.qa.api.flip.exelUtil.ReadProductExcel;
import com.qa.api.flip.exelUtil.SheetNames;
import com.sun.mail.iap.ByteArray;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class TestDataUtil_Product extends TestDataGenerateUtil{

    HashMap<String,Object> productRequestBody;
    Gson gson;
    TestUtil testUtil;
    ReadProductExcel readProductExcel;
    ExcelFilesPath excelFilesPath;
    SheetNames sheetNames;

    public TestDataUtil_Product(){
        this.productRequestBody=new HashMap<>();
        this.gson=new Gson();
        this.testUtil=new TestUtil();
    }

    private String createProductName(int length,Boolean isUpper){
        return this.createStringValueUsingFaker(5,isUpper);
    }

    private String createProductType(int length,Boolean isUpper){
        return this.createStringValueUsingFaker(5,isUpper);
    }

    private Integer createProductPrice(int length){
        return this.createNumberValueUsingFaker(3);
    }

    private Integer createProductShipping(int length){
        return this.createNumberValueUsingFaker(length);
    }

    private String createProductUpc(int length,Boolean isUpper){
        return this.createStringValueUsingFaker(length,isUpper);
    }

    private String createProductDescription(int length,Boolean isUpper){
        return this.createStringValueUsingFaker(length,isUpper)+ " " +this.createStringValueUsingFaker(length,isUpper);
    }

    private String createProductManufacturer(int length,Boolean isUpper){
        return this.createStringValueUsingFaker(length,isUpper)+ " " +this.createStringValueUsingFaker(length,isUpper);
    }

    private String createProductModel(int length,Boolean isUpper){
        return this.createStringValueUsingFaker(length,isUpper)+ " " +this.createStringValueUsingFaker(length,isUpper);
    }

    private String createProductUrl(int length,Boolean isUpper){
        return "www."+this.createStringValueUsingFaker(5,isUpper)+".com";
    }

    private String createProductImage(int length,Boolean isUpper){
        return "www."+this.createStringValueUsingFaker(5,isUpper)+".com";
    }

    public HashMap<String,Object> generateRequestBodyForProduct (Integer productNameLen,Boolean isUpperProductName,Integer productTypeLen,Boolean isUpperProductType,Integer productPriceLen,Integer productShippingLen,Integer productUpcLen,Boolean isUpperProductUpc,Integer productDescriptionLen,Boolean isUpperProductDescription,Integer productManufacturerLen,Boolean isUpperProductManufacturer,Integer productModelLen,Boolean isUpperProductModel,Integer productUrlLen,Boolean isUpperProductUrl,Integer productImageLen,Boolean isUpperProductImage) throws IOException {

        this.productRequestBody.put("name",this.createProductName(productNameLen,isUpperProductName));
        this.productRequestBody.put("type",this.createProductType(productTypeLen,isUpperProductType));
        this.productRequestBody.put("price",this.createProductPrice(productPriceLen));
        this.productRequestBody.put("shipping",this.createProductShipping(productShippingLen));
        this.productRequestBody.put("upc",this.createProductUpc(productUpcLen,isUpperProductUpc));
        this.productRequestBody.put("description",this.createProductDescription(productDescriptionLen,isUpperProductDescription));
        this.productRequestBody.put("manufacturer",this.createProductManufacturer(productManufacturerLen,isUpperProductManufacturer));
        this.productRequestBody.put("model",this.createProductModel(productModelLen,isUpperProductModel));
        this.productRequestBody.put("url",this.createProductImage(productUrlLen,isUpperProductUrl));
        this.productRequestBody.put("image",this.createProductImage(productImageLen,isUpperProductImage));

        this.testUtil.writeRequestJasonIntoFile(this.gson.toJson(this.productRequestBody).getBytes(Charset.defaultCharset()),"Product");
        this.excelFilesPath=ExcelFilesPath.ProductRequestExcel;
        this.readProductExcel=new ReadProductExcel(ExcelFilesNames.Product_Request_Data.toString(), this.excelFilesPath.getExcelFilePath());
        this.readProductExcel.openFile(ExcelFilesNames.Product_Request_Data.toString());
        this.sheetNames=SheetNames.ProductRequestData_ProductInfoExcel;
        this.readProductExcel.storeProductDetails(ExcelFilesNames.Product_Request_Data.toString(), this.sheetNames.getSheetName(),String.valueOf(this.productRequestBody.get("name")),String.valueOf(this.productRequestBody.get("type")),Double.valueOf(String.valueOf(this.productRequestBody.get("price"))),Double.valueOf(String.valueOf(this.productRequestBody.get("shipping"))),String.valueOf(this.productRequestBody.get("upc")),String.valueOf(this.productRequestBody.get("description")),String.valueOf(this.productRequestBody.get("manufacturer")),String.valueOf(this.productRequestBody.get("model")),String.valueOf(this.productRequestBody.get("url")),String.valueOf(this.productRequestBody.get("image")));
        this.readProductExcel.closeFile(ExcelFilesNames.Product_Request_Data.toString());

        return this.productRequestBody;
    }

    /*
    public static void main(String args []) throws IOException {
        TestDataUtil_Product product=new TestDataUtil_Product();
        TestUtil util=new TestUtil();
        product.generateRequestBodyForProduct(5,true,5,true,3,2,4,true,5,true,5,true,6,true,5,true,6,true);
    }
     */
}

package com.qa.api.flip.exelUtil;

import org.apache.poi.ss.usermodel.Sheet;

public class ReadProductExcel extends  ExcelBaseUtil{

    public ReadProductExcel(String fileName, String filePath) {
        super(fileName, filePath);
    }

    public Object[][] getProductDetails(String filename,String sheetName,Integer rowNo){
        Object[][] ProductInfo=null;
        try{
            Sheet sheet=this.getSheet(this.getWorkbook(filename),sheetName);
            Integer totalRowCount=this.getTotalRowCount(sheet)-1;
            Integer totalColumnCount=this.getTotalColumnCount(sheet,rowNo);
            ProductInfo=new Object[totalRowCount][totalColumnCount];

            for(int i=0;i<totalRowCount;i++){
                for(int j=0;j<this.getTotalColumnCount(sheet,i);j++){
                    System.out.println("Test " +this.getData(sheet,(i+1),j));
                    ProductInfo[i][j]=this.getData(sheet,(i+1),j);
                }
            }
        }catch (Exception ae){
            ae.printStackTrace();
        }return ProductInfo;
    }

    public void storeProductDetails(String filename,String sheetName,String name,String type,Double price,Double shipping,String upc,String description,String manufacturer,String model,String url,String image){
        Sheet sheet=this.getSheet(this.getWorkbook(filename),sheetName);
        int totalRowCount=getTotalRowCount(sheet);
        int totalColCount=getTotalColumnCount(sheet,0);
        int srNo;

        if(getData(sheet,totalRowCount,0).toLowerCase().equals("Sr No".toLowerCase())){
          srNo=1;
        }else {
          srNo=Integer.valueOf(getData(sheet,totalRowCount,0))+1;
        }
        totalRowCount=totalRowCount+1;
        for(int i=0;i<totalColCount;i++){
            if(getData(sheet,0,i).equals("Sr No")){
                setData(sheet,totalRowCount,i,String.valueOf(srNo));
            }else if(getData(sheet,0,i).equals("Name")){
                setData(sheet,totalRowCount,i,name);
            }else if(getData(sheet,0,i).equals("Type")){
                setData(sheet,totalRowCount,i,type);
            }else if(getData(sheet,0,i).equals("Price")){
                setData(sheet,totalRowCount,i,String.valueOf(price));
            }else if(getData(sheet,0,i).equals("Shipping")){
                setData(sheet,totalRowCount,i,String.valueOf(shipping));
            }else if(getData(sheet,0,i).equals("Upc")){
                setData(sheet,totalRowCount,i,upc);
            }else if(getData(sheet,0,i).equals("Description")){
                setData(sheet,totalRowCount,i,description);
            }else if(getData(sheet,0,i).equals("Manufacturer")){
                setData(sheet,totalRowCount,i,manufacturer);
            }else if(getData(sheet,0,i).equals("Model")){
                setData(sheet,totalRowCount,i,model);
            }else if(getData(sheet,0,i).equals("Url")){
                setData(sheet,totalRowCount,i,url);
            }else if(getData(sheet,0,i).equals("Image")){
                setData(sheet,totalRowCount,i,image);
            }

        }




















    }


















}

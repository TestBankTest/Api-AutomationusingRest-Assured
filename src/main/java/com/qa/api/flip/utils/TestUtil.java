package com.qa.api.flip.utils;

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.sun.mail.iap.ByteArray;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;

public class TestUtil {

    private File targetFileForByteArray;

    public void writeRequestJasonIntoFile(byte [] responseBody,String fileName) throws IOException {
        targetFileForByteArray=new File(System.getProperty("user.dir") + "/Output/Requests/"+fileName+".json");
        Files.write(responseBody,targetFileForByteArray);

    }

    public void writeResponseJsonIntoFile(byte [] responseBody,String fileName) throws IOException {
        targetFileForByteArray=new File(System.getProperty("user.dir") + "/Output/Responses/"+fileName+".json");
        Files.write(responseBody,targetFileForByteArray);
    }








}

package com.qa.api.flip.utils;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.util.Locale;
import java.util.Random;

public class TestDataGenerateUtil {


    //                Data generate from random class ========================================//
    public static Random random;
    Faker fake;
    Locale locale;
    FakeValuesService fakeValuesService;
    public TestDataGenerateUtil(){
        this.random=new Random();
        this.locale=new Locale("en-IND");
        fake=new Faker(this.locale);
        this.fakeValuesService=new FakeValuesService(this.locale,new RandomService());
    }
    protected static Integer generateRandomIntegerNo(Integer range){
        System.out.println("Test Result is "+random);
        return random.nextInt(range);
    }
    protected static String generateRandomString(Integer length){
        String text ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(length);
        for( int i = 0; i < length; i++ )
            sb.append( text.charAt( random.nextInt(text.length()) ) );
        return sb.toString();
    }
    protected static String generateRandomNumericString(int length) {
        String textnumber ="0123456789";
        StringBuilder sb = new StringBuilder(length);
        for( int i = 0; i < length; i++ )
            sb.append( textnumber.charAt( random.nextInt(textnumber.length()) ) );
        return sb.toString();

    }
    //=====================================================================
    protected static String getFullName(){
        return generateRandomString(3)+" "+generateRandomString(3);
    }
    protected static String getEmailID(){
        return generateRandomString(3)+String.valueOf(generateRandomIntegerNo(2))+"@gmail.com";
    }
    protected static String getGender(){
        return "Male";
    }
    protected static String getStatus(){
        return "Active";
    }
    protected static String getCreatedUp(){
        return "2020-09-26T03:50:03.817+05:30";
    }
    protected static String getUpdatedUp(){
        return "2020-09-26T03:50:03.817+05:30";
    }
//====================================================================================================================//
    // Test Data generate from Faker Api =============================================================================//
    // Pending.
    protected Integer createNumberValueUsingFaker(int noLength){
        String noLen="";
        for(int i=0;i<noLength;i++){
            noLen=noLen+"#";
        }
        return Integer.valueOf(this.fakeValuesService.numerify(noLen));
    }

    protected String createStringValueUsingFaker(int noLength,Boolean IsAllCapital){
        String noLen="";
        for(int i=0;i<noLength;i++){
            noLen=noLen+"?";
        }
        if(IsAllCapital) {
            return this.fakeValuesService.letterify(noLen,true);
        } else {
            return this.fakeValuesService.letterify(noLen,false);
        }
    }

    protected String createAlphaNumericValueUsingFaker(int noLength,Boolean isUpper){
        String lenNo="";
        Boolean status=true;
        for(int i=0;i<noLength;i++){
            if(status) {
                lenNo = lenNo + "#";
                status=false;
            }else {
                lenNo = lenNo + "?";
                status=true;
            }
        }
        if(isUpper) {
            return fakeValuesService.bothify(lenNo,true);
        }else {
            return fakeValuesService.bothify(lenNo,false);
        }
    }

    protected String createAlphaNumericRegixValueUsingFaker(int lenght){
        return this.fakeValuesService.regexify("[a-z0-9]{"+lenght+"}");
    }

}

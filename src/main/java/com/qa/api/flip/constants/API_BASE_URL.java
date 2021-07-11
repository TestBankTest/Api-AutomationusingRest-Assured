package com.qa.api.flip.constants;

public enum API_BASE_URL {


    BestBuyAPI ("http://localhost:3030"),
    ;

    private final String API_BASE_URL;
    API_BASE_URL(String API_BASE_URL){
        this.API_BASE_URL=API_BASE_URL;
    }

    public String getAPI_BASE_URL(){
        return this.API_BASE_URL;
    }

}

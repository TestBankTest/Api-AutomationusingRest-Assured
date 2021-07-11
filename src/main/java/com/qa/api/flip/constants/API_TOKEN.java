package com.qa.api.flip.constants;

public enum API_TOKEN {

    BestBuyAPI_TOKEN (""),
    ;

    private final String API_TOKEN;
    API_TOKEN(String API_TOKEN){
        this.API_TOKEN=API_TOKEN;
    }

    public String getAPI_TOKEN(){
        return this.API_TOKEN;
    }



}

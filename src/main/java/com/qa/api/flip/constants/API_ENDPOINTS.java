package com.qa.api.flip.constants;

public enum API_ENDPOINTS {

    BestBuyAPI_Products("/products"),
    BestBuyAPI_Stores("/stores"),
    BestBuyAPI_Services("/services"),
    BestBuyAPI_Categories("/categories"),
    BestBuyAPI_Version("/version"),
    BestBuyAPI_HealthCheck("/healthcheck"),
    ;

    private final String API_ENDPOINTS;
    API_ENDPOINTS(String API_ENDPOINTS){
        this.API_ENDPOINTS=API_ENDPOINTS;
    }

    public String getAPI_ENDPOINTS(){
        return this.API_ENDPOINTS;
    }



}

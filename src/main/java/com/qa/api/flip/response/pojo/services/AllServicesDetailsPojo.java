package com.qa.api.flip.response.pojo.services;

import java.util.ArrayList;

public class AllServicesDetailsPojo {

    Integer total;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public ArrayList<ServiceDetails> getData() {
        return data;
    }

    public void setData(ArrayList<ServiceDetails> data) {
        this.data = data;
    }

    Integer limit;
    Integer skip;
    ArrayList<ServiceDetails> data;

}

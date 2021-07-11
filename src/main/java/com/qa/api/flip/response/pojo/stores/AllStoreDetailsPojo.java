package com.qa.api.flip.response.pojo.stores;

import java.util.List;

public class AllStoreDetailsPojo {

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

    public List<StoreDetails> getdata() {
        return data;
    }

    public void setdata(List<StoreDetails> data) {
        this.data = data;
    }

    Integer limit;
    Integer skip;
    List<StoreDetails> data;



}

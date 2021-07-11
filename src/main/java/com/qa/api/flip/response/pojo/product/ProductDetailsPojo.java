package com.qa.api.flip.response.pojo.product;

import java.util.List;

public class ProductDetailsPojo {

    Integer total;
    Integer limit;
    Integer skip;
    List<ProductDetails> data;

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



    public List<ProductDetails> getData() {
        return data;
    }
    public void setData(List<ProductDetails> data) {
        this.data = data;
    }

}

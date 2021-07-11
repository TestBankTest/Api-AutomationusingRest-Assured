package com.qa.api.flip.response.pojo.categories;

import java.util.ArrayList;

public class AllCategoriesDetailsPojo {

    Integer total;
    Integer limit;
    Integer skip;

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

    public ArrayList<CategoriesDetails> getData() {
        return data;
    }

    public void setData(ArrayList<CategoriesDetails> data) {
        this.data = data;
    }

    ArrayList<CategoriesDetails> data;
}

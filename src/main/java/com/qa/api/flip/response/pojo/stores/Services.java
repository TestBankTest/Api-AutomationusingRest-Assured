package com.qa.api.flip.response.pojo.stores;

import java.util.List;

public class Services {

    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


    String name;
    String createdAt;
    String updatedAt;

    public StoreServices getStoreservices() {
        return storeservices;
    }

    public void setStoreservices(StoreServices storeservices) {
        this.storeservices = storeservices;
    }

    StoreServices storeservices;
}

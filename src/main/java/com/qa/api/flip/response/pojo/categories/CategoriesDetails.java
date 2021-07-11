package com.qa.api.flip.response.pojo.categories;

import java.util.ArrayList;

public class CategoriesDetails {


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String  id;
    String name;
    String createdAt;
    String updatedAt;



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

    public ArrayList<SubCategories> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(ArrayList<SubCategories> subCategories) {
        this.subCategories = subCategories;
    }

    ArrayList<SubCategories> subCategories;

    public ArrayList<CategoryPath> getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(ArrayList<CategoryPath> categoryPath) {
        this.categoryPath = categoryPath;
    }

    ArrayList<CategoryPath> categoryPath;





}

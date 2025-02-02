package com.ecommerce.project.model;

public class Category {
    private long categoryId;
    private String categoryName;
    // Jackson only requires a default constructor and setter getters
    // otherwise need to use @JsonCreator
    public Category() {
    }
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}

package com.ecommerce.project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    private String categoryName;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

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

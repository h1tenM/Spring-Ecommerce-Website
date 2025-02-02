package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImplementation implements CategoryService{
    List<Category> categories = new ArrayList<>();
    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategories(Category category) {
        categories.add(category);
    }
}

package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImplementation implements CategoryService{
    @Autowired()
    CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategories(Category category) {
        categoryRepository.save(category); // id is managed by the database
    }
    @Override
    public String deleteCategory(Long categoryId) {
        // could have used a for each loop but this is better
        List<Category> categories = categoryRepository.findAll();
        Category category = categories.stream()
                .filter(c -> c.getCategoryId() == categoryId)
                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found")); //HttpStatus is an enum
        categoryRepository.delete(category);
        return "Category with CategoryId: " + categoryId + " is deleted";
    }
    @Override
    public Category updateCategory(Category category, long categoryId){
        // could have done it using orElseThrow, but wanted to learn optional
        List<Category> categories = categoryRepository.findAll();
        Optional<Category> optionalCategory = categories.stream().filter(c -> c.getCategoryId() == categoryId).findFirst();
        if(optionalCategory.isPresent()){
           Category ifExistCategory = optionalCategory.get();
           ifExistCategory.setCategoryName(category.getCategoryName());
           categoryRepository.save(ifExistCategory);
           return ifExistCategory; // ??
       } else {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
       }
    }
}

package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImplementation implements CategoryService{
    List<Category> categories = new ArrayList<>();
    private long id = 1L; // L suffix ensure that 1 is treated not as int but as Long
    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategories(Category category) {
        category.setCategoryId(id++); // updates the attribute too
        categories.add(category);
    }
    @Override
    public String deleteCategory(Long categoryId) {
        // could have used a for each loop but this is better
        Category category = categories.stream()
                .filter(c -> c.getCategoryId() == categoryId)
                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found")); //HttpStatus is an enum
        categories.remove(category);
        return "Category with CategoryId: " + categoryId + " is deleted";
    }
    @Override
    public Category updateCategory(Category category, long categoryId){
        // could have done it using orElseThrow, but wanted to learn optional
       Optional<Category> optionalCategory = categories.stream().filter(c -> c.getCategoryId() == categoryId).findFirst();
       if(optionalCategory.isPresent()){
           Category ifExistCategory = optionalCategory.get();
           ifExistCategory.setCategoryName(category.getCategoryName());
           return ifExistCategory;
       } else {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
       }
    }
}

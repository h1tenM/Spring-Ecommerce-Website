package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
// all methods' output here is serialized to JSON
// This class handles all the http Responses and Requests
@RequestMapping("/api")
// Sets a URL pattern common across all the methods in the controller class
public class CategoryController {
    private CategoryService categoryService;

    // injecting Service bean, could have used @Autowired + setter or use field injections with @Value
    // if and when i have 2 implementation of categoryService i will either need a @Qualifier, @Primary, @profile annotations
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/public/categories")
//  @RequestMapping(value = "/public/categories",method = RequestMethod.GET) another way to do it
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @PostMapping("/public/categories")
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        categoryService.createCategories(category);
        return new ResponseEntity<>("Resourse created", HttpStatus.CREATED);
    }
    @DeleteMapping("/admin/categories/{catagoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long catagoryId){
        // don't need to specify that function throws an exception bcz ResponseStatusException is unchecked
        try {
            String status = categoryService.deleteCategory(catagoryId);
            return new ResponseEntity<>(status, HttpStatus.OK);
//            return ResponseEntity.ok(status);
//            return ResponseEntity.status(status, HttpStatus.OK);
//            all of these are a valid way to use ResponseEntity
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getMessage(),e.getStatusCode());
        }
    }
    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category, @PathVariable long categoryId){
        try{
            Category updatedCategory = categoryService.updateCategory(category,categoryId);
            return new ResponseEntity<>("Updated Category with Id:" + updatedCategory.getCategoryId(), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }

    }
}


package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
// all methods' output here is serialized to JSON
// This class handles all the http Responses and Requests
public class CategoryController {
    private CategoryService categoryService;

    // injecting Service bean, could have used @Autowired + setter or use field injections with @Component + @Value
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("api/public/categories")
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }
    @PostMapping("api/public/categories")
    public String createCategory(@RequestBody Category category) {
        categoryService.createCategories(category);
        return "code: 200";
    }
}


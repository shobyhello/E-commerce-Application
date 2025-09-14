package dev.dharam.productservice.controller;


import dev.dharam.productservice.dto.CategoryRequestDto;
import dev.dharam.productservice.dto.CategoryResponseDto;
import dev.dharam.productservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    @Qualifier("categoryService")
    private CategoryService categoryService;



    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories(){
        List<CategoryResponseDto> categoryList = categoryService.getAllCategories();
        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable("id") Long categoryId){
        CategoryResponseDto savedCategory = categoryService.getCategory(categoryId);
        return ResponseEntity.ok(savedCategory);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto categoryRequestDto){
        CategoryResponseDto savedCategory = categoryService.createCategory(categoryRequestDto);

        return ResponseEntity.ok(savedCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@RequestBody  CategoryRequestDto categoryRequestDto,@PathVariable("id") Long categoryId){
        CategoryResponseDto updatedCategory = categoryService.updateCategory(categoryRequestDto,categoryId);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId){
        String response = categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/totalPrice/{categoryId}")
    public ResponseEntity<Double> getTotalPriceForAllProducts(@PathVariable("categoryId") Long categoryId){

        return ResponseEntity.ok(categoryService.getTotalPriceForCategory(categoryId));
    }


}

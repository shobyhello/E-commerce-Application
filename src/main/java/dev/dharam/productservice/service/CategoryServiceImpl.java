package dev.dharam.productservice.service;


import dev.dharam.productservice.dto.CategoryRequestDto;
import dev.dharam.productservice.dto.CategoryResponseDto;
import dev.dharam.productservice.entities.Category;
import dev.dharam.productservice.entities.Product;
import dev.dharam.productservice.exceptions.CategoryNotFoundException;
import dev.dharam.productservice.mapper.DtoMapper;
import dev.dharam.productservice.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<CategoryResponseDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDto> categoryResponseDtos = new ArrayList<>();
        for(Category category: categories){
            CategoryResponseDto categoryResponseDto = DtoMapper.convertCategoryToCategoryResponseDto(category);
            categoryResponseDtos.add(categoryResponseDto);

        }
        return categoryResponseDtos;
    }

    @Override
    public CategoryResponseDto getCategory(Long categoryId) {
        Category savedCategory = categoryRepository.findById(categoryId).orElseThrow(
                ()-> new CategoryNotFoundException("Category not found with id: "+categoryId)
        );


        return DtoMapper.convertCategoryToCategoryResponseDto(savedCategory);
    }


    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDTO) {
        Category category  = new Category();
        category.setName(categoryRequestDTO.getCategoryName());
        category = categoryRepository.save(category);
        return DtoMapper.convertCategoryToCategoryResponseDto(category);
    }

    @Override
    public CategoryResponseDto updateCategory(CategoryRequestDto categoryRequestDTO, Long categoryId) {
        Category savedCategory = categoryRepository.findById(categoryId).orElseThrow(
                ()-> new CategoryNotFoundException("Category not found with id: "+categoryId)
        );

        savedCategory.setName(categoryRequestDTO.getCategoryName());
        savedCategory = categoryRepository.save(savedCategory);
        return DtoMapper.convertCategoryToCategoryResponseDto(savedCategory);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);

        return "Deleted Successfully..";
    }

    @Override
    public double getTotalPriceForCategory(Long categoryId) {
        Category savedCategory = categoryRepository.findById(categoryId).orElseThrow(
                ()-> new CategoryNotFoundException("Category not found with id: "+categoryId)
        );

        if(savedCategory.getProducts().isEmpty()){
            return 0.0;
        }else{
            double sum = 0;
            for(Product product: savedCategory.getProducts()){
                sum += product.getPrice();
            }
            return sum;
        }
    }
}

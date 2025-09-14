package dev.dharam.productservice.service;



import dev.dharam.productservice.dto.CategoryRequestDto;
import dev.dharam.productservice.dto.CategoryResponseDto;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<CategoryResponseDto> getAllCategories();
    CategoryResponseDto getCategory(Long categoryId);

    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDTO);
    CategoryResponseDto updateCategory(CategoryRequestDto categoryRequestDTO, Long categoryId);
    String deleteCategory(Long categoryId);
    double getTotalPriceForCategory(Long categoryId);
}

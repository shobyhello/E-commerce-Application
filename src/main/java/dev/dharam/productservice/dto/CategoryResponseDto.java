package dev.dharam.productservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CategoryResponseDto {
    private Long categoryId;
    private String categoryName;
    private List<ProductResponseDto> products;
}

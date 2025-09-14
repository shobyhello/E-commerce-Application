package dev.dharam.productservice.dto;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductRequestDto {
    private String title;
    private double price;
    private String description;
    private String imageURL;
    private ProductRatingDto rating;
    @Nullable
    private Long categoryId;

}


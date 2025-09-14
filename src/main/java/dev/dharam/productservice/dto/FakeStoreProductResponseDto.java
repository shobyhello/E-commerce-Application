package dev.dharam.productservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductResponseDto {
    private long id;
    private String title;
    private String description;
    private double price;
    private String category;
    private String image;
    private FakeStoreProductRatingDto rating;

}

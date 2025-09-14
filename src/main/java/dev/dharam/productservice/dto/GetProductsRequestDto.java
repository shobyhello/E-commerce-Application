package dev.dharam.productservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProductsRequestDto {
    private int numberOfResults;
    private int offSet;
}

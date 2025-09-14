package dev.dharam.productservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductRatingDto implements Serializable {
    private double rate;
    private int count;
}

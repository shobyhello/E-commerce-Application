package dev.dharam.productservice.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProductRating extends BaseModel{
    private double rate;
    private int count;
}

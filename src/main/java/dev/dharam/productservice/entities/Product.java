package dev.dharam.productservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String title;
    private double price;
    private String description;
    private int quantity;
    @ManyToOne
    @JoinColumn

    private Category category;
    private String ImageURL;
    @ManyToOne
    private ProductRating productRating;

}

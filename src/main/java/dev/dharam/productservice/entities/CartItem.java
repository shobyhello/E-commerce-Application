package dev.dharam.productservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CartItem extends BaseModel{
    @ManyToOne
    private Product product;
    private int quantity;
    private double price;
}

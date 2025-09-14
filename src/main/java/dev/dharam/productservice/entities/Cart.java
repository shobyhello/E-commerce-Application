package dev.dharam.productservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

import java.util.List;

@Getter
@Setter
@Entity
public class Cart extends BaseModel {
    private Long userId;
    @OneToMany
    private List<CartItem> cartItems;
    private double totalPrice;
}

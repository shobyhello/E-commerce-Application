package dev.dharam.productservice.dto;

import dev.dharam.productservice.entities.CartItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
    private Long productId;
    private String productName;
    private int quantity;
    private double price;

    public static CartItemDto from(CartItem cartItem){
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setPrice(cartItem.getPrice());
        cartItemDto.setProductId(cartItem.getProduct().getId());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setProductName(cartItem.getProduct().getTitle());
        return cartItemDto;
    }

}

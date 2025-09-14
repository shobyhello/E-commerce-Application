package dev.dharam.productservice.dto;

import dev.dharam.productservice.entities.CartItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartResposeDto {
    private Long cartId;
    private List<CartItemDto> cartItemDtoList;
}

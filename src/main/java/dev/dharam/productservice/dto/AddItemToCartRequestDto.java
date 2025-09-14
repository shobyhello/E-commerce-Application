package dev.dharam.productservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddItemToCartRequestDto {
        private long userId;
        private long productId;
        private int quantity;
}

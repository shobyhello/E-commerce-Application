package dev.dharam.productservice.service;

import dev.dharam.productservice.dto.AddItemToCartRequestDto;
import dev.dharam.productservice.dto.AddItemToCartResponseDto;
import dev.dharam.productservice.dto.CartResposeDto;

public interface CartService {
    AddItemToCartResponseDto addItemToCart( AddItemToCartRequestDto request);
    CartResposeDto getCartByUserId(Long userId);
}

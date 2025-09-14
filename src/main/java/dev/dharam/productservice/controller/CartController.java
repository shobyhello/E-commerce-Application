package dev.dharam.productservice.controller;


import dev.dharam.productservice.dto.AddItemToCartRequestDto;
import dev.dharam.productservice.dto.AddItemToCartResponseDto;
import dev.dharam.productservice.dto.CartResposeDto;
import dev.dharam.productservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/")
    public ResponseEntity<AddItemToCartResponseDto> addItemInCart(@RequestBody AddItemToCartRequestDto request){

        return ResponseEntity.ok(cartService.addItemToCart(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResposeDto> getCartByUserId(@PathVariable("id") Long userId){
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }



}

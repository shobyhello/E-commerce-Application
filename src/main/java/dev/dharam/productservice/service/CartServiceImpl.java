package dev.dharam.productservice.service;

import dev.dharam.productservice.dto.AddItemToCartRequestDto;
import dev.dharam.productservice.dto.AddItemToCartResponseDto;
import dev.dharam.productservice.dto.CartItemDto;
import dev.dharam.productservice.dto.CartResposeDto;
import dev.dharam.productservice.entities.Cart;
import dev.dharam.productservice.entities.CartItem;
import dev.dharam.productservice.entities.Product;
import dev.dharam.productservice.exceptions.InsufficientQuantityException;
import dev.dharam.productservice.exceptions.OutOfStockException;
import dev.dharam.productservice.exceptions.ProductNotFoundException;
import dev.dharam.productservice.repositories.CartItemReposotiry;
import dev.dharam.productservice.repositories.CartRepository;
import dev.dharam.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartItemReposotiry cartItemReposotiry;
    @Override
    public AddItemToCartResponseDto addItemToCart(AddItemToCartRequestDto request) {
        Optional<Cart> savedCartOptional= cartRepository.findByUserId(request.getUserId());

        Cart savedCart = savedCartOptional.get();

        Product product = productRepository.findById(request.getProductId()).orElseThrow(
                ()-> new ProductNotFoundException("Product not found!")
        );

        if(product.getQuantity() ==0){
            throw new OutOfStockException("Product is out of stock..!");
        }
        if(product.getQuantity() < request.getQuantity()){
            throw new InsufficientQuantityException("Insufficient quantity!!");
        }

        Optional<CartItem> cartItemOptional = savedCart.getCartItems().stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(product.getId()))
                .findFirst();


        CartItem cartItem;
        if(cartItemOptional.isPresent()){
            cartItem = cartItemOptional.get();
            cartItem.setQuantity(cartItem.getQuantity()+ request.getQuantity());
            cartItem.setPrice(cartItem.getQuantity()* product.getPrice());
        }else{
            cartItem = new CartItem();
            cartItem.setQuantity(request.getQuantity());
            cartItem.setPrice(request.getQuantity()* product.getPrice());
            cartItem.setProduct(product);
            cartItemReposotiry.save(cartItem);

        }

        savedCart.getCartItems().add(cartItem);
        Double totalPrice = 0.00;
        for(CartItem item : savedCart.getCartItems()){
            totalPrice+= item.getPrice();
        }

        savedCart.setTotalPrice(totalPrice);

        //Deduction in product quantity
        product.setQuantity(product.getQuantity()-request.getQuantity());
        productRepository.save(product);

        //Update in Cart
        cartRepository.save(savedCart);

        AddItemToCartResponseDto addItemToCartResponseDto = new AddItemToCartResponseDto();
        addItemToCartResponseDto.setCartId(savedCart.getId());
        return addItemToCartResponseDto;
    }

    @Override
    public CartResposeDto getCartByUserId(Long userId) {
        CartResposeDto cartRespose = new CartResposeDto();

        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);
        if(cartOptional.isEmpty()){
            Cart cart = new Cart();
            cart.setUserId(userId);
            Cart savedCart = cartRepository.save(cart);
            cartRespose.setCartId(savedCart.getId());
        }else {
            cartRespose.setCartId(cartOptional.get().getId());
            List<CartItemDto> cartItemDtoList = new ArrayList<>();
            for(CartItem cartItem: cartOptional.get().getCartItems()){
                cartItemDtoList.add(CartItemDto.from(cartItem));
            }
            cartRespose.setCartItemDtoList(cartItemDtoList);
        }

        return cartRespose;
    }
}

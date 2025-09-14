package dev.dharam.productservice.service;



import dev.dharam.productservice.dto.ProductRequestDto;
import dev.dharam.productservice.dto.ProductResponseDto;
import dev.dharam.productservice.entities.Product;
import dev.dharam.productservice.exceptions.ProductNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ProductService {
     List<ProductResponseDto> getAllProducts();
     ProductResponseDto getProductById(Long productId) throws ProductNotFoundException;
     ProductResponseDto createProduct(ProductRequestDto product);
     ProductResponseDto updateProduct(Long productId, ProductRequestDto productToUpdate );
     String deleteProduct(Long productId);
     Page<Product> getProducts(int numberOfProducts, int offset);
}

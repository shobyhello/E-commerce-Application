package dev.dharam.productservice.service;


import dev.dharam.productservice.dto.ProductRequestDto;
import dev.dharam.productservice.dto.ProductResponseDto;
import dev.dharam.productservice.entities.Category;
import dev.dharam.productservice.entities.Product;
import dev.dharam.productservice.exceptions.CategoryNotFoundException;
import dev.dharam.productservice.exceptions.ProductNotFoundException;
import dev.dharam.productservice.mapper.DtoMapper;
import dev.dharam.productservice.repositories.CategoryRepository;
import dev.dharam.productservice.repositories.ProductRatingRepository;
import dev.dharam.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("productService")
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRatingRepository productRatingRepository;
    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Product product: products){
            ProductResponseDto productResponseDto = DtoMapper.convertProductToProductResponseDto(product);
            productResponseDtoList.add(productResponseDto);
        }
        return productResponseDtoList;
    }

    @Override
    public ProductResponseDto getProductById(Long productId) throws ProductNotFoundException {
        Product savedProduct = productRepository.findById(productId).orElseThrow(
                ()-> new ProductNotFoundException("Product not found with id: "+productId)
        );

        ProductResponseDto productResponseDto = DtoMapper.convertProductToProductResponseDto(savedProduct);
        return productResponseDto;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = DtoMapper.convertProductRequestDtoToProduct(productRequestDto);
        Category savedCategory = categoryRepository.findById(productRequestDto.getCategoryId()).orElseThrow(
                ()-> new CategoryNotFoundException("Category not found with id: "+productRequestDto.getCategoryId())
        );
        product.setCategory(savedCategory);
        productRatingRepository.save(product.getProductRating());
        product = productRepository.save(product);

        ProductResponseDto productResponseDto = DtoMapper.convertProductToProductResponseDto(product);
        return productResponseDto;
    }

    @Override
    public ProductResponseDto updateProduct(Long productId ,ProductRequestDto productToUpdate ) {
        Product savedProduct = productRepository.findById(productId).orElseThrow(
                ()-> new ProductNotFoundException("Product not found with id: "+productId)
        );

        savedProduct.setTitle(productToUpdate.getTitle());
        savedProduct.setPrice(productToUpdate.getPrice());
        savedProduct.setImageURL(productToUpdate.getImageURL());
        savedProduct.setDescription(productToUpdate.getDescription());

        savedProduct= productRepository.save(savedProduct);
        ProductResponseDto updatedProctResponseDto = DtoMapper.convertProductToProductResponseDto(savedProduct);

        return updatedProctResponseDto;
    }

    @Override
    public String deleteProduct(Long productId) {

        productRepository.deleteById(productId);
        return "Deleted Successfully..";
    }

//    @Override
//    public Page<Product> getProducts(int numberOfProducts, int offset){
//        Page<Product> products = productRepository.findAll(
//                PageRequest.of((offset/numberOfProducts),
//                                numberOfProducts,
//                                Sort.by("price").descending()
//                                        .and(
//                                                Sort.by("title").ascending()
//                                        )
//
//                )
//        );
//        return products;
//    }
@Override
public Page<Product> getProducts(int pageNumber, int pageSize){
    Page<Product> products = productRepository.findAll(
            PageRequest.of(pageNumber,pageSize, Sort.by("price")));
    return products;
}
}

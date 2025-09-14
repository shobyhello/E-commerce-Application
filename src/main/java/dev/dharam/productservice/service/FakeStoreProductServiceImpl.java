package dev.dharam.productservice.service;


import dev.dharam.productservice.client.FakeStoreClient;
import dev.dharam.productservice.dto.FakeStoreProductRequestDto;
import dev.dharam.productservice.dto.FakeStoreProductResponseDto;
import dev.dharam.productservice.dto.ProductRequestDto;
import dev.dharam.productservice.dto.ProductResponseDto;
import dev.dharam.productservice.entities.Product;
import dev.dharam.productservice.exceptions.NoProductPresentException;
import dev.dharam.productservice.exceptions.ProductNotFoundException;
import dev.dharam.productservice.mapper.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("fakeStoreProductService")
public class FakeStoreProductServiceImpl implements ProductService {
    @Autowired
    private FakeStoreClient fakeStoreClient;

    //private Map<Long,Object> fakeStoreProducts = new HashMap<>(); => In memory Cache


    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<FakeStoreProductResponseDto> fakeStoreProducts =fakeStoreClient.getAllProducts();
        if (fakeStoreProducts == null) {
            throw new NoProductPresentException("No products are found!");
        }

        List<ProductResponseDto> products = new ArrayList<>();
        for(FakeStoreProductResponseDto fakeStoreProduct: fakeStoreProducts){
            ProductResponseDto product = DtoMapper.convertFakeStoreResponseDtoToProductResponseDto(fakeStoreProduct);
            products.add(product);

        }
        return products;

    }


    @Override
    public ProductResponseDto getProductById(Long productId)throws ProductNotFoundException {
        //Returning from In memory
//        if(fakeStoreProducts.containsKey(productId)){
//            return (ProductResponseDto) fakeStoreProducts.get(productId);
//        }
        FakeStoreProductResponseDto fakeStoreProduct =fakeStoreClient.getProductById(productId);
        if(fakeStoreProduct == null){
            throw new ProductNotFoundException("Product not found with id: "+productId);
        }

        ProductResponseDto product = DtoMapper.convertFakeStoreResponseDtoToProductResponseDto(fakeStoreProduct);
        //fakeStoreProducts.put(productId, product); storing in memory
        return product;
    }


    @Override
    public ProductResponseDto createProduct(ProductRequestDto product) {
        FakeStoreProductRequestDto fakeStoreProductRequestDto = DtoMapper.convertProductRequestDtoToFakeStoreProductRequestDto(product);

        FakeStoreProductResponseDto createdFakeStoreProduct = fakeStoreClient.createProduct(fakeStoreProductRequestDto);

        ProductResponseDto createdProduct = DtoMapper.convertFakeStoreResponseDtoToProductResponseDto(createdFakeStoreProduct);

        return createdProduct;
    }

    @Override

    public ProductResponseDto updateProduct(Long productId, ProductRequestDto productToUpdate) {
        return null;
    }


    @Override
    public String deleteProduct(Long productId) {
        return "SUCCESS";
    }

    @Override
    public Page<Product> getProducts(int numberOfProducts, int offset) {
        return null;
    }
}

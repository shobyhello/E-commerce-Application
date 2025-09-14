package dev.dharam.productservice.mapper;

import dev.dharam.productservice.dto.*;
import dev.dharam.productservice.entities.Category;
import dev.dharam.productservice.entities.Product;
import dev.dharam.productservice.entities.ProductRating;

import java.util.ArrayList;
import java.util.List;


public class DtoMapper {
    public static Product convertProductRequestDtoToProduct(ProductRequestDto productRequestDto){
        Product product = new Product();
        //product.setCategory(productRequestDto.getCategoryId());
        product.setDescription(productRequestDto.getDescription());
        product.setTitle(productRequestDto.getTitle());
        product.setPrice(productRequestDto.getPrice());
        product.setImageURL(productRequestDto.getImageURL());
        if(productRequestDto.getRating() == null){
            product.setProductRating(null);
        }else {
            ProductRating productRating = new ProductRating();
            productRating.setCount(productRequestDto.getRating().getCount());
            productRating.setRate(productRequestDto.getRating().getRate());
            product.setProductRating(productRating);
        }


        return product;
    }

    public static ProductResponseDto convertProductToProductResponseDto(Product product){
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setProductId(product.getId());
        responseDto.setCategoryName(product.getCategory().getName());
        responseDto.setDescription(product.getDescription());
        responseDto.setTitle(product.getTitle());
        responseDto.setPrice(product.getPrice());
        responseDto.setImageURL(product.getImageURL());
        if(product.getProductRating()== null){
            ProductRatingDto productRatingDto = new ProductRatingDto();
            productRatingDto.setCount(0);
            productRatingDto.setRate(0);
            responseDto.setRating(productRatingDto);
        }else{
            ProductRatingDto productRatingDto = new ProductRatingDto();
            productRatingDto.setCount(product.getProductRating().getCount());
            productRatingDto.setRate(product.getProductRating().getRate());
            responseDto.setRating(productRatingDto);
            responseDto.setCreatedAt(product.getCreatedAt());
            responseDto.setUpdatedAt(product.getUpdatedAt());
        }

        return responseDto;
    }

    public static ProductResponseDto convertFakeStoreResponseDtoToProductResponseDto(FakeStoreProductResponseDto product){
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setProductId(product.getId());
        responseDto.setCategoryName(product.getCategory());
        responseDto.setDescription(product.getDescription());
        responseDto.setPrice(product.getPrice());
        responseDto.setImageURL(product.getImage());
        if(product.getRating() == null){
            responseDto.setRating(new ProductRatingDto());
        }else{
            ProductRatingDto productRatingDto = new ProductRatingDto();
            productRatingDto.setRate(product.getRating().getRate());
            productRatingDto.setCount(product.getRating().getCount());
            responseDto.setRating(productRatingDto);
        }

        responseDto.setTitle(product.getTitle());

        return responseDto;
    }

    public static FakeStoreProductRequestDto convertProductRequestDtoToFakeStoreProductRequestDto(ProductRequestDto product){
        FakeStoreProductRequestDto requestDto = new FakeStoreProductRequestDto();

        //requestDto.setCategory(product.getCategory());
        requestDto.setDescription(product.getDescription());
        requestDto.setPrice(product.getPrice());
        requestDto.setImage(product.getImageURL());
        requestDto.setTitle(product.getTitle());

        return requestDto;
    }

    public static CategoryResponseDto convertCategoryToCategoryResponseDto(Category category){
        List<Product> products = category.getProducts();
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        if(products != null){
            for(Product product: products){
                ProductResponseDto productResponseDto = convertProductToProductResponseDto(product);
                productResponseDtoList.add(productResponseDto);
            }

        }


        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setCategoryId(category.getId());
        categoryResponseDto.setCategoryName(category.getName());
        categoryResponseDto.setProducts(productResponseDtoList);
        return categoryResponseDto;

    }
}

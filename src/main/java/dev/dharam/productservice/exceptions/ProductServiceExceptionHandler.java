package dev.dharam.productservice.exceptions;


import dev.dharam.productservice.dto.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductServiceExceptionHandler {
    @ExceptionHandler({ProductNotFoundException.class, NoProductPresentException.class})
    public ResponseEntity handleNoProductFoundException(ProductNotFoundException pe){
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                pe.getMessage(),
                404
        );

        return new ResponseEntity(exceptionResponseDto, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity handleInvalidInputException(InvalidInputException pe){
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                pe.getMessage(),
                400
        );

        return new ResponseEntity(exceptionResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity handleOutOfStockException(OutOfStockException outOfStockException){
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                outOfStockException.getMessage(),
                400
        );

        return new ResponseEntity(exceptionResponseDto, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InsufficientQuantityException.class)
    public ResponseEntity handleInsufficientQuantityException(InsufficientQuantityException insufficientQuantityException){
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                insufficientQuantityException.getMessage(),
                400
        );

        return new ResponseEntity(exceptionResponseDto, HttpStatus.INSUFFICIENT_STORAGE);
    }
}

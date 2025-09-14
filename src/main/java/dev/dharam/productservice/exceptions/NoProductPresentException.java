package dev.dharam.productservice.exceptions;

public class NoProductPresentException extends RuntimeException{
    public NoProductPresentException(String message) {
        super(message);
    }
}

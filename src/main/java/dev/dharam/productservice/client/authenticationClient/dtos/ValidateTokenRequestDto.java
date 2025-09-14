package dev.dharam.productservice.client.authenticationClient.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ValidateTokenRequestDto {
    private Long userId;// if there is a possibility to create two same random token string, then we need userId also to identify
    private String token;

}

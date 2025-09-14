package dev.dharam.productservice.client.authenticationClient;


import dev.dharam.productservice.client.authenticationClient.dtos.ValidateTokenRequestDto;
import dev.dharam.productservice.client.authenticationClient.dtos.ValidateTokenResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



@Component
public class AuthenticationClient {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;


    public ValidateTokenResponseDto validate(String token, Long userID){
        RestTemplate restTemplate = restTemplateBuilder.build();

        ValidateTokenRequestDto requestDto = new ValidateTokenRequestDto();
        requestDto.setToken(token);
        requestDto.setUserId(userID);
        ResponseEntity<ValidateTokenResponseDto> res =
                restTemplate.postForEntity("http://localhost:8080/auth/validate",
                        requestDto,
                        ValidateTokenResponseDto.class);

        return (res.getBody());
    }

}

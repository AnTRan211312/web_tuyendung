package com.TranAn.BackEnd_Works.dto.response.auth;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.TranAn.BackEnd_Works.dto.response.user.UserSessionResponseDto;


@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonPropertyOrder({"user", "accessToken"})
public class AuthTokenResponseDto {

    @JsonProperty("user")
    private UserSessionResponseDto userSessionResponseDto;
    private String accessToken;

}

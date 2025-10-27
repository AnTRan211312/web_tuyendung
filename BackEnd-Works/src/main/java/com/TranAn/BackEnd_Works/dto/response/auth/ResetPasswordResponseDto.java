package com.TranAn.BackEnd_Works.dto.response.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordResponseDto {
    private boolean success;
    private String message;
}

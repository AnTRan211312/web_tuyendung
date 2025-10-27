package com.TranAn.BackEnd_Works.controller;

import com.TranAn.BackEnd_Works.annotation.ApiMessage;
import com.TranAn.BackEnd_Works.dto.request.auth.ForgotPasswordRequestDto;
import com.TranAn.BackEnd_Works.dto.request.auth.ResetPasswordRequestDto;
import com.TranAn.BackEnd_Works.dto.request.auth.VerifyOtpRequestDto;
import com.TranAn.BackEnd_Works.dto.response.auth.OtpResponseDto;
import com.TranAn.BackEnd_Works.dto.response.auth.ResetPasswordResponseDto;
import com.TranAn.BackEnd_Works.dto.response.auth.VerifyOtpResponseDto;
import com.TranAn.BackEnd_Works.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth")
@RestController
@RequestMapping("/auth/password")
@RequiredArgsConstructor
public class PasswordResetController {

    private final AuthService authService;

    /**
     * Bước 1: Gửi mã OTP đến email
     * POST /auth/password/forgot
     */
    @PostMapping("/forgot")
    @ApiMessage(value = "Mã OTP đã được gửi đến email")
    @Operation(summary = "Gửi mã OTP để đặt lại mật khẩu")
    @SecurityRequirements()
    public ResponseEntity<OtpResponseDto> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequestDto request
    ) {
        OtpResponseDto response = authService.sendOtpForPasswordReset(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Bước 1.5: Gửi lại mã OTP (Resend)
     * POST /auth/password/resend-otp
     */
    @PostMapping("/resend-otp")
    @ApiMessage(value = "Đã gửi lại mã OTP")
    @Operation(summary = "Gửi lại mã OTP mới")
    @SecurityRequirements()
    public ResponseEntity<OtpResponseDto> resendOtp(
            @Valid @RequestBody ForgotPasswordRequestDto request
    ) {
        OtpResponseDto response = authService.resendOtp(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Bước 2: Xác thực mã OTP (Optional)
     * POST /auth/password/verify-otp
     */
    @PostMapping("/verify-otp")
    @ApiMessage(value = "Xác thực mã OTP")
    @Operation(summary = "Xác thực mã OTP (tùy chọn)")
    @SecurityRequirements()
    public ResponseEntity<VerifyOtpResponseDto> verifyOtp(
            @Valid @RequestBody VerifyOtpRequestDto request
    ) {
        VerifyOtpResponseDto response = authService.verifyOtp(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Bước 3: Reset mật khẩu với OTP đã xác thực
     * POST /auth/password/reset
     */
    @PostMapping("/reset")
    @ApiMessage(value = "Đặt lại mật khẩu thành công")
    @Operation(summary = "Đặt lại mật khẩu với mã OTP")
    @SecurityRequirements()
    public ResponseEntity<ResetPasswordResponseDto> resetPassword(
            @Valid @RequestBody ResetPasswordRequestDto request
    ) {
        ResetPasswordResponseDto response = authService.resetPassword(request);
        return ResponseEntity.ok(response);
    }
}
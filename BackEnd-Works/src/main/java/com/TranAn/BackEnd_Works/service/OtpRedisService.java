package com.TranAn.BackEnd_Works.service;

public interface OtpRedisService {

    /**
     * Tạo mã OTP ngẫu nhiên 6 chữ số
     */
    String generateOtp();

    /**
     * Lưu OTP vào Redis với email làm key
     */
    void saveOtp(String email, String otp);

    /**
     * Xác thực OTP
     */
    boolean verifyOtp(String email, String otp);

    /**
     * Xóa OTP sau khi sử dụng
     */
    void deleteOtp(String email);

    /**
     * Kiểm tra OTP có tồn tại không
     */
    boolean isOtpExist(String email);

    /**
     * Kiểm tra xem có thể gửi OTP không (Rate limiting)
     */
    boolean canSendOtp(String email);

    /**
     * Tăng số lần gửi OTP
     */
    void incrementSendAttempt(String email);

    /**
     * Lấy số lần đã gửi OTP
     */
    int getSendAttempts(String email);

    /**
     * Reset rate limit (dùng cho testing hoặc admin)
     */
    void resetRateLimit(String email);
}
package com.TranAn.BackEnd_Works.service;

import com.TranAn.BackEnd_Works.model.ChatMessage;

import java.time.Duration;
import java.util.List;

public interface ChatRedisService {

    /**
     * Lưu lịch sử chat vào Redis
     */
    void saveChatHistory(String userId, String sessionId, List<ChatMessage> messages, Duration expire);

    /**
     * Lấy lịch sử chat từ Redis
     */
    List<ChatMessage> getChatHistory(String userId, String sessionId);

    /**
     * Thêm một message vào lịch sử
     */
    void addMessage(String userId, String sessionId, ChatMessage message, Duration expire);

    /**
     * Xóa lịch sử chat
     */
    void deleteChatHistory(String userId, String sessionId);

    /**
     * Kiểm tra lịch sử có tồn tại không
     */
    boolean existsChatHistory(String userId, String sessionId);

    /**
     * Lấy danh sách tất cả sessionId của user
     */
    List<String> getAllSessionIds(String userId);
}
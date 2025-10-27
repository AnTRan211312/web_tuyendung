package com.TranAn.BackEnd_Works.service.impl;

import com.TranAn.BackEnd_Works.model.ChatMessage;
import com.TranAn.BackEnd_Works.service.ChatRedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ChatRedisServiceImpl implements ChatRedisService {

    private final RedisTemplate<String, List<ChatMessage>> redisChatTemplate;

    private static final String CHAT_HISTORY_PREFIX = "chat::history:";
    private static final Duration DEFAULT_EXPIRATION = Duration.ofHours(24); // Chat history tồn tại 24h trong Redis

    private String buildKey(String userId, String sessionId) {
        return CHAT_HISTORY_PREFIX + userId + ":" + sessionId;
    }

    @Override
    public void saveChatHistory(String userId, String sessionId, List<ChatMessage> messages, Duration expire) {
        String key = buildKey(userId, sessionId);
        redisChatTemplate.opsForValue().set(key, messages, expire != null ? expire : DEFAULT_EXPIRATION);
    }

    @Override
    public List<ChatMessage> getChatHistory(String userId, String sessionId) {
        String key = buildKey(userId, sessionId);
        List<ChatMessage> messages = redisChatTemplate.opsForValue().get(key);
        return messages != null ? messages : Collections.emptyList();
    }

    @Override
    public void addMessage(String userId, String sessionId, ChatMessage message, Duration expire) {
        String key = buildKey(userId, sessionId);
        List<ChatMessage> history = getChatHistory(userId, sessionId);

        if (history.isEmpty()) {
            history = new ArrayList<>();
        } else {
            history = new ArrayList<>(history); // Tạo mutable list
        }

        history.add(message);
        saveChatHistory(userId, sessionId, history, expire != null ? expire : DEFAULT_EXPIRATION);
    }

    @Override
    public void deleteChatHistory(String userId, String sessionId) {
        String key = buildKey(userId, sessionId);
        redisChatTemplate.delete(key);
    }

    @Override
    public boolean existsChatHistory(String userId, String sessionId) {
        String key = buildKey(userId, sessionId);
        return Boolean.TRUE.equals(redisChatTemplate.hasKey(key));
    }

    @Override
    public List<String> getAllSessionIds(String userId) {
        String keyPattern = CHAT_HISTORY_PREFIX + userId + ":*";
        Set<String> keys = redisChatTemplate.keys(keyPattern);

        if (keys == null || keys.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> sessionIds = new ArrayList<>();
        for (String key : keys) {
            // Extract sessionId from key: "chat::history:userId:sessionId"
            String sessionId = key.substring(key.lastIndexOf(":") + 1);
            sessionIds.add(sessionId);
        }

        return sessionIds;
    }
}

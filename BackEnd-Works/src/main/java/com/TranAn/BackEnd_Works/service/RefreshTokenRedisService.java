package com.TranAn.BackEnd_Works.service;


import com.TranAn.BackEnd_Works.dto.request.auth.SessionMetaRequest;
import com.TranAn.BackEnd_Works.dto.response.auth.SessionMetaResponse;

import java.time.Duration;
import java.util.List;

public interface RefreshTokenRedisService {
    void saveRefreshToken(String token, String userId, SessionMetaRequest sessionMetaRequest, Duration expire);

    boolean validateToken(String token, String userId);

    void deleteRefreshToken(String token, String userId);

    void deleteRefreshToken(String key);

    List<SessionMetaResponse> getAllSessionMetas(String userId, String currentRefreshToken);
}

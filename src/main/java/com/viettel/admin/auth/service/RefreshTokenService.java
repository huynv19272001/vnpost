package com.viettel.admin.auth.service;

import com.viettel.admin.auth.dto.request.LogOutRequest;
import com.viettel.admin.auth.model.BlacklistToken;
import com.viettel.admin.auth.model.RefreshToken;
import com.viettel.admin.auth.repository.RefreshTokenRepository;
import com.viettel.admin.auth.sercurity.jwt.exception.TokenRefreshException;
import io.netty.util.internal.ObjectUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Value("${security.jwt.jwtExpirationMs:}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private BlacklistTokenService blacklistTokenService;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setStatus(true);
        refreshToken.setUserId(userId);
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new sign request");
        }
        return token;
    }


    @Transactional
    public void logoutUserId(LogOutRequest logOutRequest) {
        Long userIdLogin = UserPrincipal.getAuthorizedUser().getId();
        List<RefreshToken> refreshTokens = refreshTokenRepository.findByUserIdAndStatus(userIdLogin,true);
        if(ObjectUtils.isEmpty(refreshTokens)){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "ko tồn tại trên hệ thống");
        }
        refreshTokens.forEach(refreshToken -> refreshToken.setStatus(false));
        refreshTokenRepository.saveAll(refreshTokens);
        String authHeader =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
        BlacklistToken blacklistToken = new BlacklistToken();
        if(authHeader != null){
            blacklistToken.setToken(authHeader.substring(7));
        }
        blacklistTokenService.create(blacklistToken);
    }
}

package org.example.service;

import org.example.entities.RefreshToken;
import org.example.entities.UserInfo;
import org.example.repository.RefreshTokenRepository;
import org.example.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    UserRepo userRepo;

    public RefreshToken createRefreshToken(String username){
        UserInfo userInfoExtracted=userRepo.findByUsername(username);
        RefreshToken refreshToken = RefreshToken.builder()
                .userInfo(userInfoExtracted)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000))
                .build();
        return  refreshTokenRepository.save(refreshToken);
    }


    public RefreshToken verifyRefreshToken(RefreshToken token){
        if(token.getExpiryDate().isAfter(Instant.now())){
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken()+ "Refresh token expired");
        }
        return token;
    }
}


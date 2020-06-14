package com.example.springredditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Ответ на логин пользователя
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {

    /**
     * JWT-токен
     */
    private String authenticationToken;
    /**
     * Рефреш-токен
     */
    private String refreshToken;
    /**
     * Дата валидности
     */
    private Instant expiresAt;
    /**
     * Имя пользователя
     */
    private String username;
}

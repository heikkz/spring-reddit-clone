package com.example.springredditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Ответ на логин пользователя
 */
@Data
@AllArgsConstructor
public class AuthenticationResponse {

    /**
     * JWT-токен
     */
    private String authenticationToken;
    /**
     * Имя пользователя
     */
    private String username;
}

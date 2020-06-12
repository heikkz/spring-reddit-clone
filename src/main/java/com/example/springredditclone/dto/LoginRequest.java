package com.example.springredditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Запрос на аутентификацию
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    /**
     * Имя пользователя
     */
    private String username;
    /**
     * Пароль
     */
    private String password;

}

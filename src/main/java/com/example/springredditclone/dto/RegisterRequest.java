package com.example.springredditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Запрос регистрации пользователя
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    /**
     * Имя пользователя
     */
    private String username;
    /**
     * Адрес электронной почты
     */
    private String email;
    /**
     * Пароль
     */
    private String password;
}

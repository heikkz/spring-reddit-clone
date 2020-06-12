package com.example.springredditclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

/**
 * Сущность пользователя
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {

    /**
     * Уникальный id пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;
    /**
     * Имя
     */
    @NotBlank(message = "Username is required")
    private String username;
    /**
     * Пароль
     */
    @NotBlank(message = "Password is required")
    private String password;
    /**
     * Почтовый адрес
     */
    @Email
    @NotEmpty(message = "Email is required")
    private String email;
    /**
     * Дата регистрации
     */
    private Instant created;
    /**
     * Признак что пользователь активен
     */
    private boolean enabled;
}

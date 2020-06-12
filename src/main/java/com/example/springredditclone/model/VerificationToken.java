package com.example.springredditclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

/**
 * Токен верификации пользователя, после процесса регистрации
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "token")
public class VerificationToken {

    /**
     * id токена
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Сгенерированный токен
     */
    private String token;
    /**
     * Пользователь, для которого был сгенерирован токен
     */
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    /**
     * Дата валидности токена
     */
    private Instant expiryDate;
}

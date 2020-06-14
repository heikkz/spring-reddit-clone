package com.example.springredditclone.repository;

import com.example.springredditclone.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для работы с рефреш-токенами JWT
 */
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    /**
     * Получить объект рефреш-токена из БД
     * @param token рефреш-токен
     * @return рефреш-токен или {@link Optional#empty()}
     */
    Optional<RefreshToken> findByToken(String token);

    /**
     * Удалить рефреш-токен из БД
     * @param token рефреш-токен
     */
    void deleteByToken(String token);
}

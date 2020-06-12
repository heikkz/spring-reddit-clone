package com.example.springredditclone.repository;

import com.example.springredditclone.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для работы с токенами верификации регистрации пользователя
 */
@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    /**
     * Получить объект токена по сгенерированному значению
     * @param token сгенерированный токен
     * @return токен, или {@link Optional#empty()} если ничего не найдено
     */
    Optional<VerificationToken> findByToken(String token);
}

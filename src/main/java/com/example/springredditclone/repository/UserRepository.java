package com.example.springredditclone.repository;

import com.example.springredditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторий работы с пользователями {@link User}
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Получить optional пользователя по его имени
     * @param username имя пользователя
     * @return пользователь если он существует, либо {@link Optional#empty()}
     */
    Optional<User> findByUsername(String username);
}

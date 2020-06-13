package com.example.springredditclone.repository;

import com.example.springredditclone.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий работы с сабреддитами {@link Subreddit}
 */
@Repository
public interface SubredditRepository extends JpaRepository<Subreddit, Long> {

    /**
     * Получить сабреддит по наименованию
     * @param subredditName наименование
     * @return сабреддит, или {@link Optional#empty()} если ничего не найдено
     */
    Optional<Subreddit> findByName(String subredditName);
}

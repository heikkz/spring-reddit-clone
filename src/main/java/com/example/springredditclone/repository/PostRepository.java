package com.example.springredditclone.repository;

import com.example.springredditclone.model.Post;
import com.example.springredditclone.model.Subreddit;
import com.example.springredditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий работы с постами {@link Post}
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * Получить список всех постов для сабреддита
     * @param subreddit сабреддит
     * @return список постов
     */
    List<Post> findAllBySubreddit(Subreddit subreddit);

    /**
     * Получить список постов пользователя
     * @param user пользователь
     * @return список постов
     */
    List<Post> findByUser(User user);
}

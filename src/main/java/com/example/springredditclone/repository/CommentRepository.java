package com.example.springredditclone.repository;

import com.example.springredditclone.model.Comment;
import com.example.springredditclone.model.Post;
import com.example.springredditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий работы с комментариями {@link Comment}
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /** Получить список комментариев для переданного поста
     * @param post пост
     * @return список комментариев
     */
    List<Comment> findByPost(Post post);

    /** Получить список комментариев переданного пользователя
     * @param user пользователь
     * @return список комментариев
     */
    List<Comment> findAllByUser(User user);
}

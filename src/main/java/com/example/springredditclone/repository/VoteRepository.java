package com.example.springredditclone.repository;

import com.example.springredditclone.model.Post;
import com.example.springredditclone.model.User;
import com.example.springredditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для работы с голосами
 */
@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    /**
     * TODO написать комментарий
     * @param post пост
     * @param user пользователь
     * @return
     */
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User user);
}

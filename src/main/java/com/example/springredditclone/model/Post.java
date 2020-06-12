package com.example.springredditclone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

/**
 * Сущеость поста
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Post {

    /**
     * Уникальный id поста
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long postId;
    /**
     * Название поста
     */
    @NotBlank(message = "Post Name cannot be empty or Null")
    private String postName;
    /**
     * URL поста
     */
    @Nullable
    private String url;
    /**
     * Описание
     */
    @Nullable
    @Lob
    private String description;
    /**
     * Количество голосов
     */
    private Integer voteCount;
    /**
     * Автор поста
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    /**
     * Дата создания
     */
    private Instant createdDate;
    /**
     * Сабреддит, к которому принадлежит пост
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Subreddit subreddit;
}

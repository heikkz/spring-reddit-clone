package com.example.springredditclone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

/**
 * Сущность комментария
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Comment {

    /**
     * Идентификатор комментария
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    /**
     * Текст
     */
    @NotEmpty
    private String text;
    /**
     * Пост
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private Post post;
    /**
     * Дата создания
     */
    private Instant createdDate;
    /**
     * Автор комментария
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
}

package com.example.springredditclone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

/**
 * Сущность сабреддита
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Subreddit {

    /**
     * Уникальный id сабреддита
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    /**
     * Название сабреддита
     */
    @NotBlank(message = "Community name is required")
    private String name;
    /**
     * Описание
     */
    @NotBlank(message = "Description is required")
    private String description;
    /**
     * Список постов сабреддита
     */
    @OneToMany(fetch = FetchType.LAZY)
    private List<Post> posts;
    /**
     * Дата создания
     */
    private Instant createdDate;
    /**
     * Участники сабреддита
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}

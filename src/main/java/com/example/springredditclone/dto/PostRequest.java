package com.example.springredditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность запроса, хранящего в себе данные о посте
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {

    /**
     * id поста
     */
    private Long postId;
    /**
     * Имя сабреддита
     */
    private String subredditName;
    /**
     * Имя поста
     */
    private String postName;
    /**
     * url
     */
    private String url;
    /**
     * Описание
     */
    private String description;

}

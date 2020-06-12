package com.example.springredditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO поста
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    /**
     * id поста
     */
    private Long id;
    /**
     * Название поста
     */
    private String postName;
    /**
     * URL поста
     */
    private String url;
    /**
     * Описание
     */
    private String description;
    /**
     * Имя пользователя
     */
    private String userName;
    /**
     * Имя сабреддита
     */
    private String subredditName;

}

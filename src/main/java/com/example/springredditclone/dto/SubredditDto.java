package com.example.springredditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для сабреддита
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubredditDto {

    /**
     * id сабреддита
     */
    private Long id;
    /**
     * Наименование
     */
    private String name;
    /**
     * Описание
     */
    private String description;
    /**
     * Количество постов
     */
    private Integer postCount;
}

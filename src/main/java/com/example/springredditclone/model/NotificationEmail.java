package com.example.springredditclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность сообщения, отправляемого по email
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEmail {

    /**
     * Тема письма
     */
    private String subject;
    /**
     * Получатель
     */
    private String recipient;
    /**
     * Текст сообщения
     */
    private String body;
}

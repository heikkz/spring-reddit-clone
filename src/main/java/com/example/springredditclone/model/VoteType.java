package com.example.springredditclone.model;

/**
 * Перечисление типов голосов
 */
public enum VoteType {

    /**
     * +
     */
    UPVOTE(1),
    /**
     * -
     */
    DOWNVOTE(-1);

    /**
     * Конструктор
     * @param direction голос за/против
     */
    VoteType(int direction) {

    }
}

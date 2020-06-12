package com.example.springredditclone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Сущность голоса
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Vote {

    /**
     * Уникальный id голоса
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long voteId;
    /**
     * Тип голоса
     */
    private VoteType voteType;
    /**
     * Пост, к которому был применен голос
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private Post post;
    /**
     * Пользователь поставивший +/- посту
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
}

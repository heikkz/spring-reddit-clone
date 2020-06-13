package com.example.springredditclone.controller;

import com.example.springredditclone.dto.CommentsDto;
import com.example.springredditclone.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-контроллер для работы с комментариями
 */
@RestController
@RequestMapping("/api/comments/")
@AllArgsConstructor
public class CommentsController {

    private final CommentService commentService;

    /**
     * POST запрос добавления комментария
     * @param commentsDto dto комментария
     * @return http status 201
     */
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CommentsDto commentsDto) {
        commentService.save(commentsDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Получить список комментариев под постом
     * @param postId id поста
     * @return список комментариев
     */
    @GetMapping("by-postId/{postId}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForPost(@PathVariable Long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentsByPost(postId));
    }

    /**
     * Получить список комментариев пользователя
     * @param userName имя пользователя
     * @return список комментариев
     */
    @GetMapping("by-user/{userName}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsByUser(@PathVariable String userName) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentsByUser(userName));
    }
}

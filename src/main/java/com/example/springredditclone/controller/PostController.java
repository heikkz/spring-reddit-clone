package com.example.springredditclone.controller;

import com.example.springredditclone.dto.PostRequest;
import com.example.springredditclone.dto.PostResponse;
import com.example.springredditclone.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для работы с постами
 */
@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * POST запрос для сохранения переданного поста
     * @param postRequest запрос с информацией о посте
     * @return {@link ResponseEntity} status 201
     */
    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest) {
        postService.save(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * GET запрос получения списка всех постов
     * @return {@link ResponseEntity} список всех постов, статус 200
     */
    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPosts());
    }

    /**
     * GET запрос получения поста по его id
     * @param id идентифкатор поста
     * @return пост
     */
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPost(id));
    }

    /**
     * GET запрос получения списка постов для сабреддита по его id
     * @param id идентифкатор сабреддита
     * @return список постов
     */
    @GetMapping("/by-subreddit/{id}")
    public ResponseEntity<List<PostResponse>> getPostsBySubreddit(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostsBySubreddit(id));
    }

    /**
     * GET запрос получения списка постов пользователя по его имени
     * @param username имя пользователя
     * @return список постов
     */
    @GetMapping("/by-user/{name}")
    public ResponseEntity<List<PostResponse>> getPostsByUsername(@PathVariable("name") String username) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostsByUsername(username));
    }
}

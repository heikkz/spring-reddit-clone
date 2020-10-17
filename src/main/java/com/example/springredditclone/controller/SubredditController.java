package com.example.springredditclone.controller;

import com.example.springredditclone.dto.SubredditDto;
import com.example.springredditclone.service.SubredditService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для работы с сабреддитами
 */
@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
public class SubredditController {

    private final SubredditService subredditService;

    /**
     * Получить список всех сабреддитов
     * @return список всех сабреддитов
     */
    @GetMapping
    public ResponseEntity<List<SubredditDto>> getAllSubreddits() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subredditService.getAll());
    }

    /**
     * Получить сабреддит по его id
     * @param id id
     * @return сабреддит
     */
    @GetMapping("/{id}")
    public ResponseEntity<SubredditDto> getSubreddit(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subredditService.getSubreddit(id));
    }

    /**
     * Добавить/обновить сабреддит
     * @param subredditDto сабреддит
     * @return сабреддит
     */
    @PostMapping
    public ResponseEntity<SubredditDto> create(@RequestBody @Valid SubredditDto subredditDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subredditService.save(subredditDto));
    }
}

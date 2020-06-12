package com.example.springredditclone.service;

import com.example.springredditclone.dto.SubredditDto;
import com.example.springredditclone.exception.SpringRedditException;
import com.example.springredditclone.model.Subreddit;
import com.example.springredditclone.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для работы с  {@link Subreddit}
 */
@Service
@AllArgsConstructor
public class SubredditService {

    private final SubredditRepository subredditRepository;
    private final AuthService authService;

    /**
     * Получить список всех сабреддитов
     * @return список всех сабреддитов
     */
    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * Добавить/обновить сабреддит
     * @param dto сабреддит
     * @return сабреддит
     */
    @Transactional
    public SubredditDto save(SubredditDto dto) {
        Subreddit subreddit = subredditRepository.save(mapToSubreddit(dto));
        dto.setId(subreddit.getId());
        return dto;
    }

    /**
     * Получить сабреддит по его id
     * @param id id
     * @return сабреддит
     */
    @Transactional(readOnly = true)
    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("Subreddit not found with id -" + id));
        return mapToDto(subreddit);
    }

    /**
     * Мэппер {@link Subreddit} -> {@link SubredditDto}
     * @param subreddit сабреддит
     * @return dto сабреддит
     */
    private SubredditDto mapToDto(Subreddit subreddit) {
        return SubredditDto.builder()
                .name(subreddit.getName())
                .id(subreddit.getId())
                .description(subreddit.getDescription())
                .postCount(subreddit.getPosts().size())
                .build();
    }

    /**
     * Мэппер {@link SubredditDto} -> {@link Subreddit}
     * @param dto dto сабреддит
     * @return сабреддит
     */
    private Subreddit mapToSubreddit(SubredditDto dto) {
        return Subreddit.builder()
                .name("/r/" + dto.getName())
                .description(dto.getDescription())
                .user(authService.getCurrentUser())
                .createdDate(Instant.now())
                .build();
    }
}

package com.example.springredditclone.service;

import com.example.springredditclone.dto.SubredditDto;
import com.example.springredditclone.exception.SpringRedditException;
import com.example.springredditclone.mapper.SubredditMapper;
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
    private final SubredditMapper subredditMapper;

    /**
     * Получить список всех сабреддитов
     * @return список всех сабреддитов
     */
    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(Collectors.toList());
    }

    /**
     * Добавить/обновить сабреддит
     * @param dto сабреддит
     * @return сабреддит
     */
    @Transactional
    public SubredditDto save(SubredditDto dto) {
        Subreddit subreddit = subredditRepository.save(subredditMapper.mapDtoToSubreddit(dto));
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
        return subredditMapper.mapSubredditToDto(subreddit);
    }
}

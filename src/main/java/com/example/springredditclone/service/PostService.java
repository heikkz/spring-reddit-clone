package com.example.springredditclone.service;

import com.example.springredditclone.dto.PostRequest;
import com.example.springredditclone.dto.PostResponse;
import com.example.springredditclone.exception.PostNotFoundException;
import com.example.springredditclone.exception.SubredditNotFoundException;
import com.example.springredditclone.mapper.PostMapper;
import com.example.springredditclone.model.Post;
import com.example.springredditclone.model.Subreddit;
import com.example.springredditclone.model.User;
import com.example.springredditclone.repository.PostRepository;
import com.example.springredditclone.repository.SubredditRepository;
import com.example.springredditclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для работы с постами
 */
@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final SubredditRepository subredditRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    /**
     * Сохранить пост
     * @param postRequest запрос
     */
    public void save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
        postRepository.save(postMapper.map(postRequest, subreddit, authService.getCurrentUser()));
    }

    /**
     * Получить список всех постов
     * @return список всех постов
     */
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * Получить пост по его id
     * @param id идентификатор поста
     * @return пост
     */
    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    /**
     * Получить список постов сабреддита
     * @param id идентификатор сабреддита
     * @return список постов сабреддита
     */
    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id).orElseThrow(() -> new SubredditNotFoundException(id.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(Collectors.toList());
    }

    /**
     * Получить список постов пользователя
     * @param username имя пользователя
     * @return список постов пользователя
     */
    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }
}

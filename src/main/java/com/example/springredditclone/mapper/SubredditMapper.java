package com.example.springredditclone.mapper;

import com.example.springredditclone.dto.SubredditDto;
import com.example.springredditclone.model.Post;
import com.example.springredditclone.model.Subreddit;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Мэппер Subreddit <-> SubredditDto,
 * с использованием Mapstruct
 */
@Mapper(componentModel = "spring")
public interface SubredditMapper {

    /**
     * Мэппер {@link Subreddit} -> {@link SubredditDto}
     * @param subreddit сабреддит
     * @return dto сабреддит
     */
    @Mapping(target = "postCount", expression = "java(mapPosts(subreddit.getPosts()))")
    SubredditDto mapSubredditToDto(Subreddit subreddit);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    /**
     * Мэппер {@link SubredditDto} -> {@link Subreddit}
     * @param dto dto сабреддит
     * @return сабреддит
     */
    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subreddit mapDtoToSubreddit(SubredditDto dto);
}

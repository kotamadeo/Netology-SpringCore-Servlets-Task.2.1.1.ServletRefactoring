package com.gmail.at.kotamadeo.mapper;

import com.gmail.at.kotamadeo.dto.PostDto;
import com.gmail.at.kotamadeo.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "content", target = "content")
    PostDto convert(Post post);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "content", target = "content")
    Post convert(PostDto postDto);

    List<PostDto> convert(List<Post> posts);
}

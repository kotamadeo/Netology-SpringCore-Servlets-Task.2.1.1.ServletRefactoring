package com.gmail.at.kotamadeo.controller;

import com.gmail.at.kotamadeo.dto.PostDto;
import com.gmail.at.kotamadeo.exception.NotFoundException;
import com.gmail.at.kotamadeo.mapper.PostMapper;
import com.gmail.at.kotamadeo.model.Post;
import com.gmail.at.kotamadeo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService service;

    @GetMapping
    public ResponseEntity<List<PostDto>> all() {
        List<PostDto> posts = PostMapper.INSTANCE.convert(service.all());
        return posts != null ?
                new ResponseEntity<>(posts, FOUND) :
                new ResponseEntity<>(NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable long id) {
        PostDto post;
        try {
            post = PostMapper.INSTANCE.convert(service.getById(id));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(NOT_FOUND);
        }
        return new ResponseEntity<>(post, OK);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Post post) {
        try {
            service.save(post);
        } catch (Exception e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
        return new ResponseEntity<>(CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeById(@PathVariable long id) {
        try {
            service.removeById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(NOT_FOUND);
        }
        return new ResponseEntity<>(OK);
    }

    private String getErrorsList(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));
    }
}

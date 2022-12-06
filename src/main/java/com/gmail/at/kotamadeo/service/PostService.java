package com.gmail.at.kotamadeo.service;

import com.gmail.at.kotamadeo.exception.NotFoundException;
import com.gmail.at.kotamadeo.model.Post;
import com.gmail.at.kotamadeo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository repository;

    public List<Post> all() {
        return repository.all();
    }

    public Post getById(long id) {
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    public Post save(Post post) {
        return repository.save(post);
    }

    public void removeById(long id) {
        if (repository.getById(id).isEmpty()) {
            throw new NotFoundException();
        }
        repository.removeById(id);
    }
}


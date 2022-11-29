package com.gmail.at.kotamadeo.repository;

import com.gmail.at.kotamadeo.model.Post;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.Optional.of;

// Stub
@NoArgsConstructor
public class PostRepository {
    private static final AtomicLong postCounter = new AtomicLong(0);
    private static final Map<Long, Post> REPOSITORY = new ConcurrentHashMap<>();

    public List<Post> all() {
        return new ArrayList<>(REPOSITORY.values());
    }

    public Optional<Post> getById(long id) {
        Optional<Post> maybePost = Optional.empty();
        if (id < 1 || id > postCounter.get()) {
            return maybePost;
        }
        if (REPOSITORY.containsKey(id)) {
            maybePost = of(REPOSITORY.get(id));
        }
        return maybePost;
    }

    public Post save(Post post) {
        long id = post.getId();
        if (id < 0 || id > postCounter.get()) {
            throw new IllegalArgumentException();
        }
        if (id == 0) {
            id = postCounter.addAndGet(1);
            post.setId(id);
            REPOSITORY.put(id, post);
            System.out.println(REPOSITORY);
        } else {
            REPOSITORY.replace(id, post);
        }
        return REPOSITORY.get(id);
    }

    public void removeById(long id) {
        if (id <= 0 || id > postCounter.get() || !REPOSITORY.containsKey(id)) {
            return;
        }
        REPOSITORY.remove(id);
    }
}

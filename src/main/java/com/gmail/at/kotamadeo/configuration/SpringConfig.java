package com.gmail.at.kotamadeo.configuration;

import com.gmail.at.kotamadeo.controller.PostController;
import com.gmail.at.kotamadeo.model.Post;
import com.gmail.at.kotamadeo.repository.PostRepository;
import com.gmail.at.kotamadeo.service.PostService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Configuration
@ComponentScan("com.gmail.at.kotamadeo")
public class SpringConfig {
    @Bean
    @Scope(SCOPE_PROTOTYPE)
    Post post() {
        return new Post();
    }

    @Bean
    PostRepository postRepository() {
        return new PostRepository();
    }

    @Bean
    PostService postService(PostRepository postRepository) {
        return new PostService(postRepository);
    }

    @Bean
    PostController postController(PostService postService) {
        return new PostController(postService);
    }
}

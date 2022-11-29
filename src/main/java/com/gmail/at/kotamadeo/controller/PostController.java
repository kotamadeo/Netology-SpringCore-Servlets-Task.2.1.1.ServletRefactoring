package com.gmail.at.kotamadeo.controller;

import com.gmail.at.kotamadeo.model.Post;
import com.gmail.at.kotamadeo.service.PostService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

import static javax.servlet.http.HttpServletResponse.*;
@RequiredArgsConstructor
public class PostController {
    private static final String APPLICATION_JSON = "application/json";
    private final PostService service;
    private static final Gson GSON = new Gson();

    public void all(HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        response.setStatus(SC_OK);
        final var data = service.all();
        response.getWriter().print(GSON.toJson(data));
    }

    public void getById(long id, HttpServletResponse response) {
        final var data = service.getById(id);
        if (data == null) {
            response.setStatus(SC_NOT_FOUND);
        } else {
            try (var writer = response.getWriter()) {
                response.setContentType(APPLICATION_JSON);
                response.setStatus(SC_OK);
                writer.print(GSON.toJson(data));
            } catch (IOException e) {
                e.printStackTrace();
                response.setStatus(SC_INTERNAL_SERVER_ERROR);
            }
        }
    }

    public void save(Reader body, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var post = GSON.fromJson(body, Post.class);
        final var data = service.save(post);
        if (data == null) {
            response.setStatus(SC_BAD_REQUEST);
        } else {
            response.setStatus(SC_OK);
        }
        response.getWriter().print(GSON.toJson(data));
    }

    public void removeById(long id, HttpServletResponse response) {
        service.removeById(id);
        response.setStatus(SC_OK);
    }
}

package com.gmail.at.kotamadeo.controller;

import com.gmail.at.kotamadeo.model.Post;
import com.gmail.at.kotamadeo.service.PostService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

import static javax.servlet.http.HttpServletResponse.*;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE)
public class PostController {
    static final String APPLICATION_JSON = "application/json";
    static final Gson GSON = new Gson();
    final PostService service;


    public void all(HttpServletResponse response) {
        final var data = service.all();
        try (var writer = response.getWriter()) {
            response.setContentType(APPLICATION_JSON);
            response.setStatus(SC_OK);
            writer.print(GSON.toJson(data));
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(SC_INTERNAL_SERVER_ERROR);
        }
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

    public void save(Reader body, HttpServletResponse response) {
        response.setContentType(APPLICATION_JSON);
        final var post = GSON.fromJson(body, Post.class);
        final var data = service.save(post);
        if (data == null) {
            response.setStatus(SC_BAD_REQUEST);
        } else {
            response.setStatus(SC_OK);
        }
        try (var writer = response.getWriter()) {
            writer.print(GSON.toJson(data));
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(SC_INTERNAL_SERVER_ERROR);
        }
    }

    public void removeById(long id, HttpServletResponse response) {
        service.removeById(id);
        response.setStatus(SC_OK);
    }
}

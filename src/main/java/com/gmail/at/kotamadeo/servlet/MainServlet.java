package com.gmail.at.kotamadeo.servlet;

import com.gmail.at.kotamadeo.controller.PostController;
import com.gmail.at.kotamadeo.exception.NotFoundException;
import com.gmail.at.kotamadeo.repository.PostRepository;
import com.gmail.at.kotamadeo.service.PostService;
import lombok.experimental.FieldDefaults;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.gmail.at.kotamadeo.servlet.MainServlet.Method.*;
import static java.lang.Long.parseLong;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
public class MainServlet extends HttpServlet {
    PostController controller;

    @Override
    public void init() {
        final var repository = new PostRepository();
        final var service = new PostService(repository);
        controller = new PostController(service);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        // если деплоились в root context, то достаточно этого
        try {
            final var path = req.getRequestURI();
            final var method = req.getMethod();

            // primitive routing
            if (method.equals(GET.name()) && path.equals("/api/posts")) {
                controller.all(resp);
                return;
            }

            if (method.equals(GET.name()) && path.matches("/api/posts/\\d+")) {
                // easy way
                final var id = getIdFromPath(path);
                controller.getById(id, resp);
                return;
            }

            if (method.equals(POST.name()) && path.equals("/api/posts")) {
                controller.save(req.getReader(), resp);
                return;
            }

            if (method.equals(DELETE.name()) && path.matches("/api/posts/\\d+")) {
                // easy way
                final var id = getIdFromPath(path);
                controller.removeById(id, resp);
                return;
            }
            resp.setStatus(SC_NOT_FOUND);
        } catch (NotFoundException e) {
            e.printStackTrace();
            resp.setStatus(SC_NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(SC_INTERNAL_SERVER_ERROR);
        }
    }

    private static long getIdFromPath(String path) {
        return parseLong(path.substring(path.lastIndexOf("/") + 1));
    }

    enum Method {
        POST, GET, DELETE
    }
}


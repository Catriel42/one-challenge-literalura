package com.catodev.literalura.controller;

import com.catodev.literalura.service.AuthorService;
import org.springframework.stereotype.Component;

@Component
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    public String getAuthors() {
        return authorService.getAuthors();
    }
}

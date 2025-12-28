package com.catodev.literalura.controller;

import com.catodev.literalura.dto.GutendexResponse;
import com.catodev.literalura.service.BookService;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public String getSearchBooks(String query, int page) throws Exception {
        GutendexResponse response = bookService.getBooks(query, page);
        bookService.saveBooksFromResponse(response);
        return response.prettyPrint();
    }


}

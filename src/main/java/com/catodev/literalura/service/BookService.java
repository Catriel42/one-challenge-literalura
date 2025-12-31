package com.catodev.literalura.service;

import com.catodev.literalura.client.GutendexClient;
import com.catodev.literalura.dto.Book;
import com.catodev.literalura.dto.GutendexResponse;
import com.catodev.literalura.mapper.BookMapper;
import com.catodev.literalura.model.BookEntity;
import com.catodev.literalura.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {
    private final GutendexClient client;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(GutendexClient client, BookRepository bookRepository, BookMapper BookMapper) {
        this.client = client;
        this.bookRepository = bookRepository;
        this.bookMapper = BookMapper;

    }

    /**
     * Guarda todos los libros de una respuesta de Gutendex
     */
    @Transactional
    public List<BookEntity> saveBooksFromResponse(GutendexResponse response) {
        List<Book> bookDtos = response.results();
        List<BookEntity> bookEntities = bookDtos.stream()
                // evitar duplicados en BD
                .filter(dto -> bookRepository.findByTitle(dto.title()).isEmpty())
                // mapear
                .map(bookMapper::toEntity)
                // evitar nulls
                .filter(entity -> entity != null)
                // guardar uno por uno (o colectar y saveAll)
                .map(bookRepository::save)
                .toList();

        return bookRepository.saveAll(bookEntities);
    }

    public GutendexResponse getBooksFromClient(String query, int page) throws Exception {
        return client.searchBooks(query, page);
    }

    @Transactional
    public String getBooks(){
        StringBuilder sb = new StringBuilder();
        List<BookEntity> result =  bookRepository.findAll();
        List<Book> dtoResult = bookMapper.toDtoList(result);
        for(Book dto : dtoResult){
            sb.append(dto.prettyPrint()).append("\n");
        }
        return sb.toString();
    }
}

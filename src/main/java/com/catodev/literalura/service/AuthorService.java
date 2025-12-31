package com.catodev.literalura.service;

import com.catodev.literalura.dto.Author;
import com.catodev.literalura.dto.Book;
import com.catodev.literalura.mapper.AuthorMapper;
import com.catodev.literalura.model.AuthorEntity;
import com.catodev.literalura.model.BookEntity;
import com.catodev.literalura.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
        this.authorMapper = new AuthorMapper();
    }

    @Transactional
    public String getAuthors(){
        StringBuilder sb = new StringBuilder();
        List<AuthorEntity> result =  authorRepository.findAll();
        List<Author> dtoResult = authorMapper.toDtoList(result);
        for(Author dto : dtoResult){
            sb.append(dto.prettyPrint()).append("\n");
        }
        return sb.toString();
    }

    @Transactional
    public String getAuthorsByYearAlive(int year){
        StringBuilder sb = new StringBuilder();
        List<AuthorEntity> result = authorRepository.findAuthorsAliveInYear(year);
        List<Author> dtoResult = authorMapper.toDtoList(result);
        for(Author dto : dtoResult){
            sb.append(dto.prettyPrint()).append("\n");
        }
        return sb.toString();
    }
}

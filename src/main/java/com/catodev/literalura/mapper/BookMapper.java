package com.catodev.literalura.mapper;

import com.catodev.literalura.dto.Book;
import com.catodev.literalura.dto.Author;
import com.catodev.literalura.model.BookEntity;
import com.catodev.literalura.model.AuthorEntity;
import com.catodev.literalura.repository.AuthorRepository;
import com.catodev.literalura.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class BookMapper {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    //Maps book DTO to its corresponding entity
    public BookEntity toEntity(Book dto) {
        if (dto == null) {
            return null;
        }

        BookEntity entity = new BookEntity();
        entity.setTitle(dto.title());
        entity.setLanguages(dto.languages());
        entity.setCopyright(dto.copyright());
        entity.setDownloadCount(dto.downloadCount());

        // Sets author properties when author exists
        if (dto.authors() != null && !dto.authors().isEmpty()) {
            Author authorDto = dto.authors().get(0);
            // Buscar autor existente por nombre
            AuthorEntity authorEntity = authorRepository
                    .findByName(authorDto.name())
                    .orElseGet(() -> {
                        // Si no existe, crear uno nuevo
                        AuthorEntity newAuthor = new AuthorEntity();
                        newAuthor.setName(authorDto.name());
                        newAuthor.setBirthYear(authorDto.birth_year());
                        newAuthor.setDeathYear(authorDto.death_year());
                        return authorRepository.save(newAuthor);
                    });

            entity.setAuthor(authorEntity);
        }
        return entity;
    }
}
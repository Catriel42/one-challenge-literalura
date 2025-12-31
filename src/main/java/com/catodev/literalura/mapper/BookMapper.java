package com.catodev.literalura.mapper;

import com.catodev.literalura.dto.Book;
import com.catodev.literalura.dto.Author;
import com.catodev.literalura.model.BookEntity;
import com.catodev.literalura.model.AuthorEntity;
import com.catodev.literalura.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Component
public class BookMapper {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public BookEntity toEntity(Book dto) {
        if (dto == null) {
            return null;
        }

        BookEntity entity = new BookEntity();
        entity.setTitle(dto.title());

        if (dto.languages() != null && !dto.languages().isEmpty()) {
            entity.setLanguage(dto.languages().get(0));
        } else {
            entity.setLanguage("unknown");
        }

        entity.setCopyright(dto.copyright());
        entity.setDownloadCount(dto.downloadCount());

        if (dto.authors() != null && !dto.authors().isEmpty()) {
            Author authorDto = dto.authors().get(0);

            if (authorDto != null && authorDto.name() != null && !authorDto.name().trim().isEmpty()) {
                AuthorEntity authorEntity = authorRepository
                        .findByName(authorDto.name())
                        .orElseGet(() -> {
                            AuthorEntity newAuthor = authorMapper.toEntity(authorDto);
                            return authorRepository.save(newAuthor);
                        });

                entity.setAuthor(authorEntity);
            }
        }

        return entity;
    }

    public Book toDto(BookEntity entity) {
        if (entity == null) {
            return null;
        }

        List<Author> authors = entity.getAuthor() != null
                ? List.of(authorMapper.toDto(entity.getAuthor()))
                : Collections.emptyList();

        List<String> languages = entity.getLanguage() != null
                ? List.of(entity.getLanguage())
                : Collections.emptyList();

        return new Book(
                entity.getTitle(),
                authors,
                languages,
                entity.isCopyright(),
                entity.getDownloadCount()
        );
    }

    public List<Book> toDtoList(List<BookEntity> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toDto)
                .toList();
    }
}
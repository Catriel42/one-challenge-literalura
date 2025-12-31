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
    private final AuthorMapper authorMapper;  // ← Inyectar AuthorMapper

    public BookEntity toEntity(Book dto) {
        if (dto == null) {
            return null;
        }

        BookEntity entity = new BookEntity();
        entity.setTitle(dto.title());
        entity.setLanguages(dto.languages());
        entity.setCopyright(dto.copyright());
        entity.setDownloadCount(dto.downloadCount());

        // Mapear autor usando AuthorMapper
        if (dto.authors() != null && !dto.authors().isEmpty()) {
            Author authorDto = dto.authors().get(0);

            // Buscar autor existente por nombre
            AuthorEntity authorEntity = authorRepository
                    .findByName(authorDto.name())
                    .orElseGet(() -> {
                        // Si no existe, crear uno nuevo usando el mapper
                        AuthorEntity newAuthor = authorMapper.toEntity(authorDto);
                        return authorRepository.save(newAuthor);
                    });

            entity.setAuthor(authorEntity);
        }
        return entity;
    }

    public Book toDto(BookEntity entity) {
        if (entity == null) {
            return null;
        }

        // Usar AuthorMapper para convertir el autor
        List<Author> authors = entity.getAuthor() != null
                ? List.of(authorMapper.toDto(entity.getAuthor()))
                : Collections.emptyList();

        return new Book(
                entity.getTitle(),
                authors,
                entity.getLanguages(),
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
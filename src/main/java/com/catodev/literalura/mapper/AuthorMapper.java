package com.catodev.literalura.mapper;

import com.catodev.literalura.dto.Author;
import com.catodev.literalura.model.AuthorEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorMapper {

    public AuthorEntity toEntity(Author dto) {
        if (dto == null) {
            return null;
        }

        AuthorEntity entity = new AuthorEntity();
        entity.setName(dto.name());
        entity.setBirthYear(dto.birth_year());
        entity.setDeathYear(dto.death_year());
        return entity;
    }

    public Author toDto(AuthorEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Author(
                entity.getName(),
                entity.getBirthYear(),
                entity.getDeathYear()
        );
    }

    public List<Author> toDtoList(List<AuthorEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }

        return entities.stream()
                .map(this::toDto)
                .toList();
    }
}
package com.catodev.literalura.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", nullable = false)
    private AuthorEntity author;

    @ElementCollection
    @CollectionTable(name = "book_languages", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "language")
    private List<String> languages;

    private boolean copyright;

    @Column(name = "download_count")
    private int downloadCount;
}
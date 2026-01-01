package com.catodev.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Book(
        String title,
        List<Author> authors,
        List<String> languages,
        boolean copyright,
        @JsonProperty("download_count") int downloadCount
) {
    public String prettyPrint() {
        String authorInfo = "Unknown Author";
        if (authors != null && !authors.isEmpty() && authors.get(0) != null) {
            authorInfo = authors.get(0).prettyPrint();
        }

        String languageInfo = "unknown";
        if (languages != null && !languages.isEmpty()) {
            languageInfo = languages.get(0);
        }

        return """
               Title: %s
               Author: %s
               Languages: %s
               Downloads: %d
               -------------------------
               """.formatted(title, authorInfo, languageInfo, downloadCount);
    }
}


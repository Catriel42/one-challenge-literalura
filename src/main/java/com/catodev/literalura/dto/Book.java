package com.catodev.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Book(
        int id,
        String title,
        List<Author> authors,
        List<String> languages,
        boolean copyright,
        @JsonProperty("download_count") int downloadCount
) {
    public String prettyPrint() {
        return """
               Title: %s
               Languages: %s
               Downloads: %d
               -------------------------
               """.formatted(title, languages, downloadCount);
    }
}


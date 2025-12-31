package com.catodev.literalura.dto;

public record Author(
        String name,
        int birth_year,
        int death_year
) {
    public String prettyPrint() {
        return "%s, (%d - %d) ".formatted(name,  birth_year, death_year);
    }
}

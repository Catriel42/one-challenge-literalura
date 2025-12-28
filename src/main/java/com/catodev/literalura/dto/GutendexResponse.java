package com.catodev.literalura.dto;

import java.util.List;


public record GutendexResponse(
        int count,
        String next,
        String previous,
        List<Book> results
) {
    public String prettyPrint() {
        StringBuilder sb = new StringBuilder();

        sb.append("Total books found: ").append(count).append("\n\n");

        for (Book book : results) {
            sb.append(book.prettyPrint()).append("\n");
        }

        return sb.toString();
    }
}

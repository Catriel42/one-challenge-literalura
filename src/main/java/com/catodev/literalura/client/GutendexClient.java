package com.catodev.literalura.client;

import com.catodev.literalura.dto.GutendexResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class GutendexClient {
    private final String baseUrl = "https://gutendex.com/";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public GutendexClient() {
        httpClient = HttpClient.newHttpClient();
        objectMapper = new ObjectMapper();
    }

    private <T> T get(String endpoint,
            Map<String, String> queryParams,
            Class<T> responseType) throws Exception {

        StringBuilder url = new StringBuilder(baseUrl + endpoint);

        if (queryParams != null && !queryParams.isEmpty()) {
            url.append("?");

            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                url.append(entry.getKey())
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                        .append("&");
            }

            url.deleteCharAt(url.length() - 1);
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url.toString()))
                .build();
        HttpResponse<String> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString());
        System.out.println(url);
        if (response.statusCode() != 200) {
            throw new RuntimeException("Error en la API: " + response.statusCode());
        }
        return objectMapper.readValue(response.body(), responseType);
    }

    public GutendexResponse searchBooks(String query, int page) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("search", query);
        params.put("page", String.valueOf(page));

        return get("books/", params, GutendexResponse.class);
    }

}

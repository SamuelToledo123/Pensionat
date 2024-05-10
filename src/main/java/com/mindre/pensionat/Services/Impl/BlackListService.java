package com.mindre.pensionat.Services.Impl;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class BlackListService {
    public CompletableFuture<Boolean> isEmailBlacklisted(String email) {
        HttpClient client = HttpClient.newHttpClient();
        String json = String.format("{\"email\":\"%s\"}", email);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://javabl.systementor.se/api/MSK/blacklist"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    System.out.println("Response: " + response.body());
                    return response.body().contains("\"isOk\":false");
                });
    }
}

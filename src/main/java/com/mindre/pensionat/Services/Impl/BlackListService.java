package com.mindre.pensionat.Services.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindre.pensionat.Models.BlacklistModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


@Service
public class BlackListService {
    //private final BlacklistModel blacklist = new BlacklistModel();
    private final String BLACKLIST_URL = "https://javabl.systementor.se/api/MSK/blacklist";
    private static final Logger logger = LoggerFactory.getLogger(BlackListService.class);

    public boolean checkIfBlacklisted(String email) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BLACKLIST_URL + "check/" + email))
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonResponse = response.body();
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(jsonResponse);
                boolean isBlacklisted = jsonNode.get("ok").asBoolean();
                return !isBlacklisted;
        } catch (Exception e) {
            System.err.println("Request failed: " + e.getMessage());
        }
        return false;
    }
    public void addToBlacklist() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BLACKLIST_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"email\":\"stefa@aaasdadsdsd.com\", \"name\":\"Kalle\",  \"isOk\":\"false\" }"))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();

    }
    public List<String> fetchBlacklisted() throws IOException, InterruptedException {

        System.out.println("fetchBlacklisted called");
        List<String> blacklisted = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BLACKLIST_URL))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String responseBody = response.body();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            for (JsonNode node : jsonNode) {
                String email = node.get("email").asText();
                blacklisted.add(email);
            }
        }
        catch (Exception e) {
            logger.error("Fetching error:" + e.getMessage());
        }
        return blacklisted;
}
}








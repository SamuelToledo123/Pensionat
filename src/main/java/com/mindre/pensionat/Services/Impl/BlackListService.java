package com.mindre.pensionat.Services.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindre.pensionat.configuration.IntegrationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


@Service
public class BlackListService {

    private final IntegrationProperties properties;
    private final String BLACKLIST_URL;
    @Autowired
    public BlackListService(IntegrationProperties properties) {
        this.properties = properties;
        this.BLACKLIST_URL = properties.getBlacklistUrl();
    }

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
            logger.error("Request failed" + e.getMessage());
        }
        return false;
    }

}








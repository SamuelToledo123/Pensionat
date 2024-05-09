package com.mindre.pensionat;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class BlackList implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(BlackList.class);



    @Override
    public void run(String... args) throws Exception {

        System.out.println("Blacklist method called");

        public static void saveCustomer() {
            System.out.println("Vad är eran favorrite Post Malone låt?");

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://javabl.systementor.se/api/MSK/blacklist"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString("{\"email\":\"samuel.torpedo@gmail.com\", \"name\":\"Samuel\", \"isOk\":\"true\" }"))
                    .build();
            logger.info("martin deee lääcker");

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(System.out::println)
                    .join();
        }

        public static void updateCustomer () {
            HttpClient clientUpdate = HttpClient.newHttpClient();
            HttpRequest requestUpdate = HttpRequest.newBuilder()
                    .uri(URI.create("https://javabl.systementor.se/api/MSK/blacklist"))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString("{\"email\":\"samuel.torpedo@gmail.com\", \"name\":\"Samuel\", \"isOk\":\"true\" }"))
                    .build();

            clientUpdate.sendAsync(requestUpdate, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(System.out::println)
                    .join();
        }

        public boolean checkIfBlacklisted (String email){
            HttpClient clientUpdate = HttpClient.newHttpClient();
            HttpRequest requestUpdate = HttpRequest.newBuilder()
                    .uri(URI.create("https://javabl.systementor.se/api/MSK/check-blacklist?email=" + email))
                    .header("Content-Type", "application/json")
                    .GET().build();

            CompletableFuture<HttpResponse<String>> response = clientUpdate.sendAsync(requestUpdate, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.thenApply(HttpResponse::body).join();
            return Boolean.parseBoolean(responseBody.trim());
        }

    }
}


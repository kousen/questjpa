package com.oreilly.quest.services;

import com.oreilly.quest.entities.Castle;
import com.oreilly.quest.json.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GeocoderService {
    private static final String KEY = "AIzaSyDw_d6dfxDEI7MAvqfGXEIsEMwjC1PWRno";

    private final WebClient client;

    public GeocoderService(WebClient.Builder builder) {
        client = builder.baseUrl("https://maps.googleapis.com")
                .build();
    }

    public Castle fillInLatLng(Castle castle) {
        String encoded = Stream.of(castle.getCity(), castle.getState())
                .map(s -> URLEncoder.encode(s, StandardCharsets.UTF_8))
                .collect(Collectors.joining(","));
        Response response = client.get()
                .uri((uriBuilder -> uriBuilder.path("/maps/api/geocode/json")
                        .queryParam("address", encoded)
                        .queryParam("key", KEY)
                        .build()
                ))
                .retrieve()
                .bodyToMono(Response.class)
                .block(Duration.ofSeconds(2L));
        if (response != null) {
            castle.setLatitude(response.getLocation().getLat());
            castle.setLongitude(response.getLocation().getLng());
        }
        return castle;
    }
}

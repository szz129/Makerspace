package com.makerspace.makerspaceapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class YouTubeService {

    @Value("${youtube.api.key}")
    private String apiKey;

    private final String YOUTUBE_SEARCH_URL =
            "https://www.googleapis.com/youtube/v3/search?part=snippet&type=video&maxResults=10&q=%s&key=%s";

    public List<String> searchVideos(String query) {

        String url = String.format(YOUTUBE_SEARCH_URL, query, apiKey);

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        List<String> videoUrls = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            JsonNode items = root.path("items");

            for (JsonNode item : items) {
                String videoId = item.path("id").path("videoId").asText();
                videoUrls.add("https://www.youtube.com/watch?v=" + videoId);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse YouTube API response", e);
        }

        return videoUrls;
    }
}

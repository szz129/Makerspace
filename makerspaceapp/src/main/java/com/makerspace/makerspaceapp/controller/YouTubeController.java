package com.makerspace.makerspaceapp.controller;

import com.makerspace.makerspaceapp.service.YouTubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/youtube")
public class YouTubeController {

    @Autowired
    private YouTubeService youTubeService;

    @GetMapping("/search")
    public List<String> searchVideos(@RequestParam String query) {
        return youTubeService.searchVideos(query);
    }
}
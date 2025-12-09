package com.makerspace.makerspaceapp.controller;

import com.makerspace.makerspaceapp.dto.ApiResponse;
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
    public ApiResponse<List<String>> searchVideos(@RequestParam String query) {
        List<String> videos = youTubeService.searchVideos(query);
        return new ApiResponse<>(true, "Videos fetched successfully", videos);
    }
}

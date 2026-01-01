package com.example.hybridsearch.controller;

import com.example.hybridsearch.service.HybridSearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HybridSearchController {

    private final HybridSearchService service;

    public HybridSearchController(HybridSearchService service) {
        this.service = service;
    }

    @GetMapping("/hybrid")
    public List<HybridSearchService.HybridResult> hybridSearch(
            @RequestParam String query,
            @RequestParam(defaultValue = "2") int fuzzy,
            @RequestParam(defaultValue = "0.85") double semantic
    ) {
        return service.search(query, fuzzy, semantic);
    }
}

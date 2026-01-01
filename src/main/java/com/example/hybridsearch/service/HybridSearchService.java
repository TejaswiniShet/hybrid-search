package com.example.hybridsearch.service;

import com.example.hybridsearch.model.DocumentEntity;
import com.example.hybridsearch.repository.DocumentRepository;
import com.example.hybridsearch.util.EmbeddingUtil;
import com.example.hybridsearch.util.FuzzySearchUtil;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HybridSearchService {

    private final DocumentRepository repo;

    public HybridSearchService(DocumentRepository repo) {
        this.repo = repo;
    }

    public List<HybridResult> search(
            String query,
            int fuzzyThreshold,
            double semanticThreshold
    ) {

        String normalizedQuery = normalize(query);

        // STEP 1: relaxed fuzzy filtering (word-level)
        List<DocumentEntity> fuzzyMatches = repo.findAll().stream()
                .filter(doc ->
                        fuzzyMatch(doc.getText(), normalizedQuery, fuzzyThreshold)
                )
                .collect(Collectors.toList());

        double[] queryVector = EmbeddingUtil.embed(normalizedQuery);

        // STEP 2: embedding ranking
        return fuzzyMatches.stream()
                .map(doc -> {
                    double score = EmbeddingUtil.cosineSimilarity(
                            queryVector,
                            EmbeddingUtil.embed(normalize(doc.getText()))
                    );
                    return new HybridResult(doc.getText(), score);
                })
                .filter(r -> r.score >= semanticThreshold)
                .sorted(Comparator.comparingDouble(
                        (HybridResult r) -> r.score
                ).reversed())
                .collect(Collectors.toList());
    }

    // ---------- helper methods ----------

    private boolean fuzzyMatch(String text, String query, int threshold) {
        for (String word : text.split("\\s+")) {
            if (FuzzySearchUtil.levenshtein(
                    normalize(word), query) <= threshold) {
                return true;
            }
        }
        return false;
    }

    private String normalize(String s) {
        return s.toLowerCase().replaceAll("\\s+", "");
    }

    // ---------- response DTO ----------
    public static class HybridResult {
        public String text;
        public double score;

        public HybridResult(String text, double score) {
            this.text = text;
            this.score = score;
        }
    }
}

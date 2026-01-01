package com.example.hybridsearch.util;

public class EmbeddingUtil {

    public static double[] embed(String text) {
        return new double[]{
                text.length(),
                text.chars().filter(Character::isLetter).count(),
                text.chars().filter(Character::isWhitespace).count()
        };
    }

    public static double cosineSimilarity(double[] a, double[] b) {
        double dot = 0, magA = 0, magB = 0;

        for (int i = 0; i < a.length; i++) {
            dot += a[i] * b[i];
            magA += a[i] * a[i];
            magB += b[i] * b[i];
        }
        return dot / (Math.sqrt(magA) * Math.sqrt(magB));
    }
}

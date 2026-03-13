import java.util.*;

public class Plagiarism {
    private HashMap<String, Set<String>> ngramMap = new HashMap<>();

    public void analyzeDocument(String docId, String content) {
        String[] words = content.split("\\s+");
        for (int i = 0; i <= words.length - 5; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 5; j++) {
                sb.append(words[i + j]).append(" ");
            }
            String ngram = sb.toString().trim().toLowerCase();
            ngramMap.computeIfAbsent(ngram, k -> new HashSet<>()).add(docId);
        }
    }

    public double calculateSimilarity(String docA, String docB) {
        int matches = 0;
        int totalDocA = 0;

        for (Set<String> docs : ngramMap.values()) {
            if (docs.contains(docA)) {
                totalDocA++;
                if (docs.contains(docB)) {
                    matches++;
                }
            }
        }

        if (totalDocA == 0) return 0.0;
        return ((double) matches / totalDocA) * 100;
    }

    public static void main(String[] args) {
        Plagiarism detector = new Plagiarism();

        String doc1 = "The quick brown fox jumps over the lazy dog";
        String doc2 = "A quick brown fox jumps over the lazy dog consistently";

        detector.analyzeDocument("Doc1", doc1);
        detector.analyzeDocument("Doc2", doc2);

        double similarity = detector.calculateSimilarity("Doc1", "Doc2");

        System.out.println("Document 1: " + doc1);
        System.out.println("Document 2: " + doc2);
        System.out.printf("Similarity Score: %.2f%%\n", similarity);
    }
}

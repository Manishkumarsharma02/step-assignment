import java.util.*;

public class Real_Time_Analytics {
    private HashMap<String, Integer> pageViews = new HashMap<>();
    private HashMap<String, Set<String>> uniqueVisitors = new HashMap<>();
    private HashMap<String, Integer> sources = new HashMap<>();

    public void processEvent(String url, String userId, String source) {
        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);
        uniqueVisitors.computeIfAbsent(url, k -> new HashSet<>()).add(userId);
        sources.put(source, sources.getOrDefault(source, 0) + 1);
    }

    public void displayDashboard() {
        System.out.println("--- Real-Time Analytics Dashboard ---");
        System.out.println("Top Pages by Views:");
        pageViews.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " views"));

        System.out.println("\nUnique Visitors per Page:");
        uniqueVisitors.forEach((url, visitors) ->
                System.out.println(url + ": " + visitors.size() + " unique users"));
    }

    public static void main(String[] args) {
        Real_Time_Analytics analytics = new Real_Time_Analytics ();

        analytics.processEvent("/home", "user1", "Google");
        analytics.processEvent("/home", "user2", "Direct");
        analytics.processEvent("/products", "user1", "Google");
        analytics.processEvent("/home", "user1", "Google");
        analytics.processEvent("/contact", "user3", "Social");

        analytics.displayDashboard();
    }
}
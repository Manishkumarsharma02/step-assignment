import java.util.*;


public class SocialMedia {
    private HashMap<String, Integer> usernameMap;
    private HashMap<String, Integer> attemptFrequency;

    public SocialMedia() {
        usernameMap = new HashMap<>();
        attemptFrequency = new HashMap<>();
    }
    public void registerUser(String username, int userId) {
        usernameMap.put(username, userId);
    }
    public boolean checkAvailability(String username) {
        attemptFrequency.put(
                username,attemptFrequency.getOrDefault(username, 0) + 1
        );
        return !usernameMap.containsKey(username);
    }

    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            String newName = username + 2*i;
            if (!usernameMap.containsKey(newName)) {
                suggestions.add(newName);
            }
        }

        String dotVersion = username.replace("_", ".");
        if (!usernameMap.containsKey(dotVersion)) {
            suggestions.add(dotVersion);
        }
        return suggestions;
    }
    public String getMostAttempted() {
        String maxUser = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : attemptFrequency.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxUser = entry.getKey();
            }
        }
        return maxUser + " (" + maxCount + " attempts)";
    }

    public static void main(String[] args) {

      SocialMedia checker = new SocialMedia();

        checker.registerUser("john_doe", 1);
        checker.registerUser("alice", 2);
        checker.registerUser("Ram",3);
        checker.registerUser("suresh",4);
        checker.registerUser("pawan",5);
        checker.registerUser("Ramesh",6);

        System.out.println("Check ram: " + checker.checkAvailability("ram"));
        System.out.println("Check pawan: " + checker.checkAvailability("pawan"));
        System.out.println("Suggestions for ram: " + checker.suggestAlternatives("ram"));
        System.out.println("Most attempted username: " + checker.getMostAttempted());
    }
}
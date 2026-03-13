import java.util.*;

class TokenBucket {
    int tokens;
    int maxTokens;
    long lastRefill;

    TokenBucket(int max) {
        maxTokens = max;
        tokens = max;
        lastRefill = System.currentTimeMillis();
    }
}

public class Distributed_Rate {

    static HashMap<String, TokenBucket> clients = new HashMap<>();

    static boolean checkRateLimit(String clientId) {

        clients.putIfAbsent(clientId, new TokenBucket(5));

        TokenBucket bucket = clients.get(clientId);

        if(bucket.tokens > 0) {
            bucket.tokens--;
            return true;
        }

        return false;
    }

    public static void main(String[] args) {

        String id = "abc123";

        for(int i=1;i<=7;i++) {

            if(checkRateLimit(id))
                System.out.println("Allowed");
            else
                System.out.println("Denied");
        }
    }
}
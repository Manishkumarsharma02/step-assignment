import java.util.HashMap;

class DNSEntry {
    String ip;
    long expiry;

    DNSEntry(String ip, long ttlSeconds) {
        this.ip = ip;
        this.expiry = System.currentTimeMillis() + (ttlSeconds * 1000);
    }
}

public class DNS {
    private HashMap<String, DNSEntry> cache = new HashMap<>();
    private int hits = 0;
    private int misses = 0;

    public String resolve(String domain) {
        DNSEntry entry = cache.get(domain);

        if (entry != null && System.currentTimeMillis() < entry.expiry) {
            hits++;
            System.out.println("Cache HIT for: " + domain);
            return entry.ip;
        }

        misses++;
        System.out.println("Cache MISS for: " + domain + ". Fetching from upstream...");

        String ipFromUpstream = "172.217.14.206";
        cache.put(domain, new DNSEntry(ipFromUpstream, 5));
        return ipFromUpstream;
    }

    public static void main(String[] args) throws InterruptedException {
        DNS dns = new DNS();

        System.out.println("Result: " + dns.resolve("google.com"));

        System.out.println("Result: " + dns.resolve("google.com"));

        System.out.println("\nWaiting for TTL to expire (6 seconds)...");
        Thread.sleep(6000);

        System.out.println("Result: " + dns.resolve("google.com"));

        System.out.println("\nTotal Hits: " + dns.hits + ", Total Misses: " + dns.misses);
    }
}
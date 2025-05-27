import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VirtualThreadsExample {
    private static final int NUM_THREADS = 100_000; // Launch 100,000 virtual threads [cite: 101]

    public static void main(String[] args) throws InterruptedException {
        // --- Using Virtual Threads ---
        Instant startVirtual = Instant.now();
        System.out.println("Launching " + NUM_THREADS + " virtual threads...");

        List<Thread> virtualThreads = new ArrayList<>();
        for (int i = 0; i < NUM_THREADS; i++) {
            final int threadNum = i;
            // Use Thread.startVirtualThread [cite: 102]
            virtualThreads.add(Thread.ofVirtual().unstarted(() -> {
                // System.out.println("Virtual Thread " + threadNum + ": Hello from virtual thread!");
                // Simulate some work
                try {
                    Thread.sleep(1); // Small sleep
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }));
        }

        for (Thread thread : virtualThreads) {
            thread.start();
        }

        for (Thread thread : virtualThreads) {
            thread.join();
        }

        Instant endVirtual = Instant.now();
        System.out.println("Virtual threads completed in: " + Duration.between(startVirtual, endVirtual).toMillis() + " ms"); // cite: 102

        // --- Using Traditional Platform Threads (for comparison) ---
        // Be cautious when running this with a very high NUM_THREADS as it can exhaust system resources.
        // For 100,000 threads, you might get an OutOfMemoryError.
        // It's recommended to test this with a smaller number, e.g., 1000-5000.
        int numPlatformThreads = 5000; // Reduced for practical comparison
        System.out.println("\nLaunching " + numPlatformThreads + " platform threads (caution: can be resource intensive)...");
        Instant startPlatform = Instant.now();

        List<Thread> platformThreads = new ArrayList<>();
        for (int i = 0; i < numPlatformThreads; i++) {
            final int threadNum = i;
            platformThreads.add(Thread.ofPlatform().unstarted(() -> {
                // System.out.println("Platform Thread " + threadNum + ": Hello from platform thread!");
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }));
        }

        for (Thread thread : platformThreads) {
            thread.start();
        }

        for (Thread thread : platformThreads) {
            thread.join();
        }

        Instant endPlatform = Instant.now();
        System.out.println("Platform threads completed in: " + Duration.between(startPlatform, endPlatform).toMillis() + " ms"); // cite: 102
    }
}
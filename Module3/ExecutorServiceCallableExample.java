import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceCallableExample {
    public static void main(String[] args) {
        // Use Executors.newFixedThreadPool() [cite: 104]
        ExecutorService executor = Executors.newFixedThreadPool(3); // A thread pool of 3 threads

        List<Callable<Integer>> tasks = new ArrayList<>();
        // Create multiple Callable tasks that return results [cite: 103]
        for (int i = 1; i <= 5; i++) {
            final int taskNum = i;
            tasks.add(() -> {
                System.out.println("Executing Task " + taskNum + " in " + Thread.currentThread().getName());
                // Simulate some work
                Thread.sleep(taskNum * 100);
                return taskNum * 10; // Return a result
            });
        }

        List<Future<Integer>> results = new ArrayList<>();
        try {
            for (Callable<Integer> task : tasks) {
                // Submit to execute callables [cite: 104]
                results.add(executor.submit(task));
            }

            // Collect results using Future.get() [cite: 104]
            for (Future<Integer> future : results) {
                System.out.println("Task Result: " + future.get());
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown(); // Shut down the executor service
            try {
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
        }
    }
}
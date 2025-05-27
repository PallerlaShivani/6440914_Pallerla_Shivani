class MyThread extends Thread { // cite: 64
    private String message;

    public MyThread(String message) {
        this.message = message;
    }

    @Override
    public void run() { // cite: 65
        for (int i = 0; i < 3; i++) {
            System.out.println(message + " - " + i); // cite: 65
            try {
                Thread.sleep(100); // Simulate some work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class MyRunnable implements Runnable { // cite: 64
    private String message;

    public MyRunnable(String message) {
        this.message = message;
    }

    @Override
    public void run() { // cite: 65
        for (int i = 0; i < 3; i++) {
            System.out.println(message + " - " + i); // cite: 65
            try {
                Thread.sleep(100); // Simulate some work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class ThreadCreation {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread("Thread A"); // cite: 65
        Thread thread2 = new Thread(new MyRunnable("Thread B")); // cite: 65

        thread1.start(); // cite: 65
        thread2.start(); // cite: 65
    }
}
package ThirdSemesterExercises.Backend.Week6Year2024.Day2.Exercise6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        // Determine the number of available cores
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of available cores: " + cores);

        // Create an ExecutorService with a thread pool size equal to the number of cores
        ExecutorService executor = Executors.newFixedThreadPool(cores);

        // Submit tasks to the ExecutorService
        for (int i = 0; i < 100; i++) {
            executor.submit(new Task(i));
        }

        // Shutdown the ExecutorService
        executor.shutdown();
    }
}

class Task implements Runnable {
    private int taskId;

    public Task(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println("Task " + taskId + " is running on core: " + Thread.currentThread().getName());
        for (int i = 0; i < 1000000; i++) {
            Math.sin(i);
        }
    }
}

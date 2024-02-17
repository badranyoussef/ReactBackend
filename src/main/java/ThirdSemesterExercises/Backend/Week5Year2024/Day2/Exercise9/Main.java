package ThirdSemesterExercises.Backend.Week5Year2024.Day2.Exercise9;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
         /*
           Using CompletableFuture
           Use the CompletableFuture class to execute multiple tasks concurrently and wait for their completion.

           Create several CompletableFuture instances, each running a Task asynchronously using the runAsync() method.
           Use the allOf() method to wait for all CompletableFuture instances to complete.
           Print a message indicating the completion of all tasks.

         */

        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            try {
                new Task().run();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            try {
                new Task().run();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2);
        allFutures.thenRun(() -> System.out.println("All CompletableFuture tasks completed."));

        System.out.println();

        /*
          Using ExecutorService:
          Use the ExecutorService to manage a pool of threads and execute tasks concurrently.

          Create an ExecutorService using Executors.newFixedThreadPool().
          Submit multiple Task instances to the executor using the submit() method.
          Shutdown the executor using the shutdown() method after submitting tasks.
          Print a message indicating the completion of all tasks.
        */

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(() -> {
            try {
                new Task().run();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.submit(() -> {
            try {
                new Task().run();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        executorService.shutdown();
        System.out.println("All ExecutorService tasks submitted.");
    }
}
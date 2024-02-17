package ThirdSemesterExercises.Backend.Week6Year2024.Day2.Exercise3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {

    public static void main(String[] args) {

        /*
        Write a program where the main thread creates a number of tasks for an ExecutorService.
        The first task should print AAA, the next BBB,CCC etc. up to ZZZ (there are 25 characters in total).
        There should be 4 worker threads in the executor service.
         */

        ExecutorService workers = Executors.newFixedThreadPool(4);

        // Submit tasks to print consecutive letters
        for (char c = 'A'; c <= 'Z'; c++) {
            char letter = c;
            workers.submit(() -> {
                System.out.println(letter + "" + letter + "" + letter);
            });
        }

        // Shutdown the ExecutorService
        workers.shutdown();
    }
}


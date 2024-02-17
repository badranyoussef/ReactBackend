package ThirdSemesterExercises.Backend.Week5Year2024.Day2.Exercise9;

//                  Concurrency

// Create a Task Class
    /*
    Create a simple Task class that simulates some computation. The class should have a run() method
    that performs the computation for a fixed time.
     */

// Exception Handling: Handle exceptions that might occur during task execution.

// Modify the Task class to throw an exception during computation.

class Task {
    void run() throws InterruptedException {
        // Simulate some computation
        try {
            Thread.sleep(1000); // Simulate 1 second of work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
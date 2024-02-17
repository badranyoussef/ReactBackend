package ThirdSemesterExercises.Backend.Week6Year2024.FridayReview;

import java.util.concurrent.Callable;


// Know the purpose of Runnable and Callable interfaces plus Future

public class Main {
    public static void main(String[] args) {
    }
}

// Formålet med både Runnable og Callable

class Example1 implements Runnable {
    @Override
    public void run() {
    }
}

class Example2 implements Callable<String> {
    @Override
    public String call() throws Exception {
        return null;
    }
}
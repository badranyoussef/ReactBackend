package ThirdSemesterExercises.Backend.Week6Year2024.Day2.Exercise4;

public class Main {
    public static void main(String[] args) {
        // Read up on the concept of 'synchronized' (for example this page "http://tutorials.jenkov.com/java-concurrency/synchronized.html").
        // Fix the below code so that the Counter class´s increment method is thread safe:

        /*
        private static class Counter {
        private int count = 0;

        // Method to increment the count, synchronized to ensure thread safety
        public synchronized void increment() {
            count++;
        }

        // Method to retrieve the current count value
        public int getCount() {
            return count;
        }
        }*/

    }

    // My solution -->
    private static class Counter {
        private static int count = 0;

        public synchronized void increment() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }
}

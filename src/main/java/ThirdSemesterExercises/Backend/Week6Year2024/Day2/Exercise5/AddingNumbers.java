package ThirdSemesterExercises.Backend.Week6Year2024.Day2.Exercise5;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AddingNumbers {

    // Problemet var at den samme værdi bliver ændret og printet ud samtidigt med at en anden tråd begynder
    // at ændre værdien. Derfor går det helt galt. Min løsning er at bruge AtomicInteger. AtomicInteger
    // gør den er trådsikker. Således at den samme værdi ikke bliver ændret, mens en anden tråd bruger den.
    // Det er en måde, at sikre at ens program kører fornuftigt.

    public static void main(String[] args) {
        ExecutorService workingJack = Executors.newFixedThreadPool(17);
        System.out.println("Main starts");
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        AtomicInteger counter = new AtomicInteger(0);

        // Start 1000 opgaver i trådpoolen
        for (int i = 0; i < 1000; i++) {
            workingJack.submit(new TaskToAddCount(map, counter));
        }

        System.out.println("Main is done");
        workingJack.shutdown();
    }

    private static class TaskToAddCount implements Runnable {
        private ConcurrentHashMap<Integer, Integer> map;
        private AtomicInteger counter;

        TaskToAddCount(ConcurrentHashMap<Integer, Integer> map, AtomicInteger counter) {
            this.map = map;
            this.counter = counter;
        }

        @Override
        public void run() {
            try {
                // Simuler en kort ventetid
                Thread.sleep((int) (Math.random() * 1 + 0));

                // Generer en unik værdi til tælleren
                int value = counter.getAndIncrement();

                // Tilføj værdien til map, hvis den ikke allerede findes
                map.putIfAbsent(value, value);

                // Udskriv værdien af tælleren og størrelsen af map
                System.out.println("Task: " + value + ": Map size = " + map.size());
            } catch (InterruptedException ex) {
                System.out.println("Thread was interrupted");
            }
        }
    }
}
package ThirdSemesterExercises.Backend.Week5Year2024.Day2.Exercise8;

import java.util.HashMap;
import java.util.Map;

// Implement Storage Classes: Implement two classes that implement the DataStorage interface
public class MemoryStorage<T> implements DataStorage<T> {
    private Map<String, T> storageMap = new HashMap<>();
    private static int idCounter = 0;

    @Override
    public String store(T data) {
        String uniqueId = String.valueOf(generateUniqueId());
        storageMap.put(uniqueId, data);
        return uniqueId.toString(); // Convert the uniqueId to a String before returning
    }

    @Override
    public T retrieve(String source) {
        return storageMap.get(source);
    }

    private Integer generateUniqueId() {
        return ++idCounter;
    }
}

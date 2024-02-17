package ThirdSemesterExercises.Backend.Week5Year2024.Day2.Exercise8;

import java.io.*;
import java.util.Scanner;

// Implement Storage Classes: Implement two classes that implement the DataStorage interface
public class FileStorage<T> implements DataStorage<T> {
    private String filePath;

    public FileStorage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String store(T data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.println(data.toString());
            return data.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public T retrieve(String source) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals(source)) {
                    return (T) Integer.valueOf(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

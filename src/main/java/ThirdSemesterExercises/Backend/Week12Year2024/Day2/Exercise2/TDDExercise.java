package ThirdSemesterExercises.Backend.Week12Year2024.Day2.Exercise2;

import java.util.ArrayList;
import java.util.List;

public class TDDExercise {
    public static String greet(Object o) {
        // Hvis inputtet er null, returneres en standardhilsen.
        if (o == null) {
            return "Hello, my friend.";
        }
        // Hvis inputtet er en enkelt streng, behandles det som et navn.
        else if (o instanceof String) {
            String name = (String) o;
            // Hvis navnet er skrevet med store bogstaver, returneres en "råbende" hilsen.
            if (name.equals(name.toUpperCase())) {
                return "HELLO " + name + "!";
            }
            // Ellers returneres en normal hilsen.
            else {
                return "Hello, " + name + ".";
            }
        }
        // Hvis inputtet er en streng-array, behandles det som en liste af navne.
        else if (o instanceof String[]) {
            String[] names = (String[]) o;
            List<String> normalNames = new ArrayList<>();
            List<String> shoutedNames = new ArrayList<>();
            // Itererer igennem hvert navn i arrayet.
            for (String name : names) {
                // Hvis navnet er omsluttet af anførselstegn, fjernes disse og navnet tilføjes som en enkelt streng.
                if (name.startsWith("\"") && name.endsWith("\"")) {
                    name = name.substring(1, name.length() - 1);
                    normalNames.add(name);
                }
                // Hvis navnet indeholder et komma, behandles det som flere navne, ellers som et enkelt navn.
                else if (name.contains(",")) {
                    String[] splitNames = name.split(",\\s*");
                    for (String splitName : splitNames) {
                        // Hvis et af de splittede navne er skrevet med store bogstaver, tilføjes det til råbende navne.
                        if (splitName.equals(splitName.toUpperCase())) {
                            shoutedNames.add(splitName);
                        }
                        // Ellers tilføjes det til normale navne.
                        else {
                            normalNames.add(splitName);
                        }
                    }
                }
                // Hvis navnet er skrevet med store bogstaver, tilføjes det til råbende navne, ellers til normale navne.
                else {
                    if (name.equals(name.toUpperCase())) {
                        shoutedNames.add(name);
                    } else {
                        normalNames.add(name);
                    }
                }
            }
            // Oprettelse af en StringBuilder til at opbygge resultatet.
            StringBuilder result = new StringBuilder();
            // Hvis der er normale navne, tilføjes de til resultatet.
            if (!normalNames.isEmpty()) {
                result.append("Hello, ");
                if (normalNames.size() == 1) {
                    result.append(normalNames.get(0));
                } else if (normalNames.size() == 2) {
                    result.append(normalNames.get(0)).append(" and ").append(normalNames.get(1));
                } else {
                    for (int i = 0; i < normalNames.size() - 1; i++) {
                        result.append(normalNames.get(i)).append(", ");
                    }
                    result.append("and ").append(normalNames.get(normalNames.size() - 1));
                }
                result.append(".");
            }
            // Hvis der er råbende navne, tilføjes de til resultatet.
            if (!shoutedNames.isEmpty()) {
                if (result.length() > 0) {
                    result.append(" ");
                }
                result.append("AND ");
                for (String name : shoutedNames) {
                    result.append("HELLO ").append(name).append("! ");
                }
            }
            // Resultatet returneres trimmet.
            return result.toString().trim();
        }
        // Hvis inputtet er af en anden type, returneres en besked om, at det ikke understøttes.
        else {
            return "Unsupported input type.";
        }
    }
}
// Link til testen https://github.com/AhmadAlkaseb/ThirdSemesterExercises/blob/main/src/test/java/ThirdSemesterExercises/Backend/Week12Year2024/Day2/Exercise2/TDDExerciseTest.java
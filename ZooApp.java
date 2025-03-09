import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZooApp {
    public static void main(String[] args) {
        System.out.println("Welcome to my Zoo Program!");

        List<Animal> animals = new ArrayList<>();
        Map<String, Integer> speciesCount = new HashMap<>();

        String filePath = "animalNames.txt";
        AnimalNameListsWrapper animalLists = Utilities.createAnimalNameLists(filePath);

        List<String> listOfHyenaNames = animalLists.getHyenaNameList();
        List<String> listOfLionNames = animalLists.getLionNameList();
        List<String> listOfTigerNames = animalLists.getTigerNameList();
        List<String> listOfBearNames = animalLists.getBearNameList();

        printAnimalNames("Hyena", listOfHyenaNames);
        printAnimalNames("Lion", listOfLionNames);
        printAnimalNames("Tiger", listOfTigerNames);
        printAnimalNames("Bear", listOfBearNames);

        try (BufferedReader reader = new BufferedReader(new FileReader("arrivingAnimals.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String[] details = line.split(",");
                if (details.length < 2) continue;

                String species = details[0].trim();
                int age = Integer.parseInt(details[1].trim());
                String name = getNameForSpecies(species, listOfHyenaNames, listOfLionNames, listOfTigerNames, listOfBearNames);

                Animal animal = createAnimal(species, name, age);

                if (animal != null) {
                    animals.add(animal);
                    speciesCount.put(species, speciesCount.getOrDefault(species, 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        writeReport("newAnimals.txt", animals, speciesCount);
    }

    private static void printAnimalNames(String species, List<String> names) {
        System.out.println("\n" + species + " Names:");
        for (String name : names) {
            System.out.println(name);
        }
    }

    private static String getNameForSpecies(String species, List<String> hyenaNames, List<String> lionNames, List<String> tigerNames, List<String> bearNames) {
        return switch (species) {
            case "Hyena" -> !hyenaNames.isEmpty() ? hyenaNames.remove(0) : "Unnamed Hyena";
            case "Lion" -> !lionNames.isEmpty() ? lionNames.remove(0) : "Unnamed Lion";
            case "Tiger" -> !tigerNames.isEmpty() ? tigerNames.remove(0) : "Unnamed Tiger";
            case "Bear" -> !bearNames.isEmpty() ? bearNames.remove(0) : "Unnamed Bear";
            default -> "Unknown";
        };
    }

    private static Animal createAnimal(String species, String name, int age) {
        return switch (species) {
            case "Hyena" -> new Hyena(name, age);
            case "Lion" -> new Lion(name, age);
            case "Tiger" -> new Tiger(name, age);
            case "Bear" -> new Bear(name, age);
            default -> null;
        };
    }

    private static void writeReport(String fileName, List<Animal> animals, Map<String, Integer> speciesCount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String species : speciesCount.keySet()) {
                writer.write(species + " (Total: " + speciesCount.get(species) + ")\n");
                for (Animal animal : animals) {
                    if (animal.getSpecies().equals(species)) {
                        writer.write("  - " + animal.getName() + ", Age: " + animal.getAge() + "\n");
                    }
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


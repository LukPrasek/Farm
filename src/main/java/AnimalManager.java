import java.util.*;
import java.util.stream.Collectors;


public class AnimalManager {
    private BarnManager barnManager = new BarnManager();
    private InputDataFromConsoleForAnimal inputDataFromConsoleForAnimal;
    private Scanner scanner;

    public AnimalManager() {
        inputDataFromConsoleForAnimal = new InputDataFromConsoleForAnimal();
        scanner = new Scanner(System.in);
    }

    public void createNewAnimalAndAddToList() {
        System.out.println("To create new animal, first select or create new barn");
        System.out.println("1-select existing barn by providing the id");
        System.out.println("2- create a new barn");
        createOrSelectBarnToAssignAnAnimal();
    }

    private void createOrSelectBarnToAssignAnAnimal() {
        String userAnswer = scanner.nextLine();
        switch (userAnswer) {
            case ("1"):
                barnManager.showAllBarns();
                System.out.println("Choose the barn giving the id");
                Barn barn = DatabaseReader.getBarnList().get(scanner.nextInt() - 1);
                addCreatedAnimalToListFromBarnClass(barn);
                break;
            case ("2"):
                Barn barn1 = barnManager.createNewBarn();
                addCreatedAnimalToListFromBarnClass(barn1);
                break;
        }
    }

    private void addCreatedAnimalToListFromBarnClass(Barn barn) {
        try {
            barn.getAnimalList().add(inputDataFromConsoleForAnimal.getAnimalDataFromUserConsole(scanner));
            String pathToFile = Menu.getPathToFolder() + "\\" + barn.getId() + ".txt";
            barnManager.saveBarnToFile(barn, pathToFile);
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        }
    }

    public void showFiveOldestAnimals() {
        DatabaseReader.getAllAnimalList().stream().sorted(Comparator.comparingInt(Animal::getAge).reversed()).limit(5).forEach(System.out::println);//limit(10).
    }

    private List<Animal> getAnimalListFromAllBarns() {
        return DatabaseReader.getAllAnimalList();
    }

    public void showMostNumerousAnimalSpecies() {
        List<String> animalSpeciesList = getAnimalListFromAllBarns().stream().map(o -> o.getAnimalSpecies().toString()).collect(Collectors.toList());
        countFrequencies(animalSpeciesList);
    }

    public void countFrequencies(List<String> list) {
        Map<String, Long> couterMap = list.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        sortbykey(couterMap);
    }

    public static void sortbykey(Map<String, Long> map) {
        TreeMap<String, Long> sorted = new TreeMap<>();
        sorted.putAll(map);
        // Display the TreeMap which is naturally sorted
        long maxValueInMap = (Collections.max(sorted.values()));  // This will return max value in the Hashmap
        for (Map.Entry<String, Long> entry : map.entrySet()) {  // Itrate through hashmap
            if (entry.getValue() == maxValueInMap) {
                System.out.println("Species with most occurances: " + entry.getKey() + " - " +maxValueInMap + " times.");
            }
        }
    }
}


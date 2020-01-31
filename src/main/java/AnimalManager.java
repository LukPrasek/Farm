import java.util.*;
import java.util.stream.Collectors;


public class AnimalManager {
    private BarnManager barnManager = new BarnManager();
    private InputDataFromConsoleForAnimal inputDataFromConsoleForAnimal;
    private Scanner scanner;
    private ScannerAsker scannerAsker;

    public AnimalManager() {
        inputDataFromConsoleForAnimal = new InputDataFromConsoleForAnimal();
        scanner = new Scanner(System.in);
        scannerAsker = new ScannerAsker(System.in, System.out);
    }

    public void createNewAnimalAndAddToList() throws FarmException {
        //messagePrintOptions();
        createOrSelectBarnToAssignAnAnimal();
    }

    private String messagePrintOptions() {
        return "To create new animal, first select or create new barn"+"\n"+
        "1-select existing barn by providing the id"+"\n"+
        "2- create a new barn";
    }

    private void createOrSelectBarnToAssignAnAnimal() throws FarmException {
        String userAnswer;
        while (true) {
            userAnswer = scannerAsker.askString(messagePrintOptions());
            if (userAnswer.equalsIgnoreCase("1") || userAnswer.equalsIgnoreCase("2")) {
                switch (userAnswer) {
                    case ("1"):
                        barnManager.showAllBarns();
                        //System.out.println("Choose the barn giving the id");
                        int userBarnId = scannerAsker.askInt("Choose the barn giving the id") - 1;
                        Barn barn = DatabaseReader.getBarnList().get(userBarnId);
                        addCreatedAnimalToListFromBarnClass(barn);
                        break;
                    case ("2"):
                        Barn barn1 = barnManager.createNewBarn();
                        addCreatedAnimalToListFromBarnClass(barn1);
                        break;
                }
            } else {
                System.out.println("Wrong input, try again");
            }
        }
    }

    private void addCreatedAnimalToListFromBarnClass(Barn barn) throws FarmException {
        try {
            barn.getAnimalList().add(inputDataFromConsoleForAnimal.getAnimalDataFromUserConsole(scannerAsker));
            String pathToFile = Menu.getPathToFolder() + "\\" + barn.getId() + ".txt";
            barnManager.saveBarnToFile(barn, pathToFile);
        } catch (IllegalArgumentException | FarmException exception) {
            throw new FarmException("Illegal argument of Animal species-enum");
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
        sortByKey(couterMap);
    }

    public static void sortByKey(Map<String, Long> map) {
        TreeMap<String, Long> sorted = new TreeMap<>(map);
        // Display the TreeMap which is naturally sorted
        long maxValueInMap = (Collections.max(sorted.values()));  // This will return max value in the Hashmap
        for (Map.Entry<String, Long> entry : map.entrySet()) {  // Iterate through hashmap
            if (entry.getValue() == maxValueInMap) {
                System.out.println("Species with most occurances: " + entry.getKey() + " - " + maxValueInMap + " times.");
            }
        }
    }
}



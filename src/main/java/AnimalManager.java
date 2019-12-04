import java.util.*;
import java.util.stream.Collectors;


public class AnimalManager {
    private static List<Animal> animalList;
    private BarnManager barnManager = new BarnManager();
    private InputDataFromConsoleForAnimal inputDataFromConsoleForAnimal;
    private Scanner scanner;

    public AnimalManager() {
        inputDataFromConsoleForAnimal = new InputDataFromConsoleForAnimal();
        animalList = new ArrayList<>();
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
                barn.getAnimalList().add(inputDataFromConsoleForAnimal.getAnimalDataFromUserConsole());
                String pathToFile = Menu.getPathToFolder() + "\\" + barn.getId() + ".txt";
                barnManager.saveBarnToFile(barn, pathToFile);
                break;
            case ("2"):
                System.out.println("Provide the name of a new barn");
                Barn barn1 = barnManager.createNewBarn(scanner.nextLine());
                barn1.getAnimalList().add(inputDataFromConsoleForAnimal.getAnimalDataFromUserConsole());
                String pathToNewFile = Menu.getPathToFolder() + "\\" + barn1.getId() + ".txt";
                barnManager.saveBarnToFile(barn1, pathToNewFile);
                break;
        }
    }

    public void showFiveOldestAnimals() {
        getAnimalListFromAllBarns().sort(Comparator.comparingInt(Animal::getAge));
        animalList.stream().limit(5);
        animalList.forEach(System.out::println);
    }

    private List<Animal> getAnimalListFromAllBarns() {
        List<Barn> barns = DatabaseReader.getBarnList();
        for (Barn barn : barns) {
            animalList.addAll(barn.getAnimalList());
        }
        return animalList;
    }

    public void showMostNumerousAnimalSpecies() {
        List<String> animalSpeciesList = getAnimalListFromAllBarns().stream().map(o -> o.getAnimalSpecies().toString()).collect(Collectors.toList());
        countFrequencies(animalSpeciesList);
    }

    public void countFrequencies(List<String> list) {
        Map<String, Integer> hm = new HashMap<>();
        for (String i : list) {
            Integer j = hm.get(i);
            hm.put(i, (j == null) ? 1 : j + 1);
        }
        ArrayList<Integer> al = new ArrayList<>(hm.values());
        int max = 0;
        for (Integer anAl : al) {
            if (anAl > max) {
                max = anAl;
            }
        }
        String word = "";
        for (Map.Entry<String, Integer> entry : hm.entrySet()) {
            if (Objects.equals(entry.getValue(), max)) {
                word = entry.getKey();
            }
        }
        System.out.println("Species with most occurances: " + word + " - " + max + " times.");
    }
}

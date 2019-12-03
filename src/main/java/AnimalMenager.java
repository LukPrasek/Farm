import java.security.KeyStore;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnimalMenager {
    private Animal animal;
    private static List<Animal> animalList;
    private BarnManager barnManager = new BarnManager();
    private Scanner scanner;

    public AnimalMenager() {

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
                barn.getAnimalList().add(getAnimalDataFromUserConsole());
                String pathToFile = Menu.getPathToFolder() + "\\" + barn.getId() + ".txt";
                barnManager.saveBarnToFile(barn, pathToFile);
                // scanner.close();
                break;
            case ("2"):
                System.out.println("Provide the name of a new barn");
                Barn barn1 = barnManager.createNewBarn(scanner.nextLine());
                barn1.getAnimalList().add(getAnimalDataFromUserConsole());
                String pathToNewFile = Menu.getPathToFolder() + "\\" + barn1.getId() + ".txt";
                barnManager.saveBarnToFile(barn1, pathToNewFile);
                break;
        }
    }

    private Animal getAnimalDataFromUserConsole() {
        System.out.println("Select the species from the list by providing the name");
        System.out.println(AnimalSpecies.CAT.toString() + "; " + AnimalSpecies.DOG.toString() + "; "
                + AnimalSpecies.HORSE.toString() + "; " + AnimalSpecies.COW.toString() + "; " + AnimalSpecies.PIG.toString());
        Animal animal = new Animal();
        String animalSpeciesName = scanner.next();
        animal.setAnimalSpecies(AnimalSpecies.valueOf(animalSpeciesName.toUpperCase()));
        scanner.nextLine();
        System.out.println("Is the animal vaccinated - true or false");
        String vaccinated = scanner.next();
        animal.setVaccinated(Boolean.parseBoolean(vaccinated));
        System.out.println("Provide the age");
        scanner.nextLine();
        int age = Integer.parseInt(scanner.next());
        animal.setAge(age);
        return animal;
    }

    public void showFiveOldestAnimals() {
        getAnimalListFromAllBarns().sort(Comparator.comparingInt(o -> o.getAge()));
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
        // hashmap to store the frequency of element
        Map<String, Integer> hm = new HashMap<>();
        for (String i : list) {
            Integer j = hm.get(i);
            hm.put(i, (j == null) ? 1 : j + 1);
        }
        // displaying the occurrence of elements in the arraylist
//        for (Map.Entry<String, Integer> val : hm.entrySet()) {
//            System.out.println("Species " + val.getKey() + " "
//                    + "occurs" + ": " + val.getValue() + " times");
//
//        }
        ArrayList<Integer> al = new ArrayList<>();
        al.addAll(hm.values());
        int max = 0;
        for (Integer anAl : al) {
            if (anAl > max) {
                max = anAl;
            }
        }
        String word="";
        System.out.println("max: " + max);
        for (Map.Entry<String, Integer> entry : hm.entrySet()) {
            if (Objects.equals(entry.getValue(), max)) {
                word=entry.getKey();
            }
        }
        System.out.println("Word with most occurances"+word);

    }
}


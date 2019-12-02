import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnimalMenager {
    private Animal animal;
    private List<Animal> animalList;
    private BarnManager barnManager = new BarnManager();

    public AnimalMenager() {
        animalList = new ArrayList<>();
    }

    public Animal createNewAnimalAndAddToList(AnimalSpecies animalSpecies, int age, boolean isVaccinated) {
        System.out.println("To create new animal, first select or create new barn");
        System.out.println("1-select existing barn by providing the id");
        System.out.println("2- create a new barn");
        getDataFromUser();

        Animal animal = new Animal(animalSpecies, age, isVaccinated);
        animalList.add(animal);
        return animal;
    }

    private void getDataFromUser() {
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        switch (answer) {
            case ("1"):
                barnManager.showAllBarns();
                System.out.println("Choose the barn giving the id");
                Barn barn = DatabaseReader.getBarnList().get(scanner.nextInt() - 1);
                barn.getAnimalList().add(getAnimalDataFromUserConsole(scanner));
                String pathToFile = Menu.getPathToFolder() + "\\" + barn.getId() + ".txt";
                barnManager.saveBarnToFile(barn, pathToFile);
                scanner.close();
                break;
            case ("2"):
                System.out.println("Provide the name of a new barn");
                Barn barn1 = barnManager.createNewBarn(scanner.nextLine());
                barn1.getAnimalList().add(getAnimalDataFromUserConsole(scanner));
                scanner.close();
                String pathToFile1 = Menu.getPathToFolder() + "\\" + barn1.getId() + ".txt";
                barnManager.saveBarnToFile(barn1, pathToFile1);
                break;
        }
    }

    private Animal getAnimalDataFromUserConsole(Scanner scanner) {
        System.out.println("Select the species from the list by providing the name");
        System.out.println(AnimalSpecies.CAT.toString() + "; " + AnimalSpecies.DOG.toString() + "; " + AnimalSpecies.HORSE.toString() + "; " + AnimalSpecies.COW.toString() + "; " + AnimalSpecies.PIG.toString());
        scanner.nextLine();
        //scanner.close();
        Animal animal = new Animal();
        String animalSpeciesName = scanner.nextLine();
        animal.setAnimalSpecies(AnimalSpecies.valueOf(animalSpeciesName.toUpperCase()));
        System.out.println("Provide the age");
        animal.setAge(scanner.nextInt());
        System.out.println("Is the animal vaccinated - true or false");
        scanner.nextLine();
        animal.setVaccinated(Boolean.parseBoolean(scanner.nextLine()));
        return animal;
    }
}

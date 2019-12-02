import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnimalMenager {
    private Animal animal;
    private List<Animal> animalList;
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
                //scanner.close();
                break;
        }
    }

    private Animal getAnimalDataFromUserConsole() {
        System.out.println("Select the species from the list by providing the name");
        System.out.println(AnimalSpecies.CAT.toString() + "; " + AnimalSpecies.DOG.toString() + "; " + AnimalSpecies.HORSE.toString() + "; " + AnimalSpecies.COW.toString() + "; " + AnimalSpecies.PIG.toString());
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
        //scanner.close();
        return animal;
    }
}

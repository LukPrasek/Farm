import java.util.*;
import java.util.stream.Collectors;

public class InputDataFromConsoleForAnimal {
    private Scanner scanner;

    public InputDataFromConsoleForAnimal() {
        scanner = new Scanner(System.in);
    }

    public Animal getAnimalDataFromUserConsole() {
        System.out.println("Select the species from the list by providing the name");
        System.out.println(AnimalSpecies.CAT.toString() + "; " + AnimalSpecies.DOG.toString() + "; "
                + AnimalSpecies.HORSE.toString() + "; " + AnimalSpecies.COW.toString() + "; " + AnimalSpecies.PIG.toString());
        Animal animal = new Animal();
        String animalSpeciesName = scanner.next();
        animal.setAnimalSpecies(AnimalSpecies.valueOf(animalSpeciesName.toUpperCase()));
        scanner.nextLine();
        System.out.println("Is the animal vaccinated - true or false");
        String vaccinated;
        boolean flag=true;
        while (flag){
            vaccinated = scanner.next();
            if (((vaccinated.equalsIgnoreCase("true")) || vaccinated.equalsIgnoreCase("false"))){
                animal.setVaccinated(Boolean.parseBoolean(vaccinated));
                break;
            } else {
                System.out.println("Wrong input, try again write true or false");
                flag=true;
            }
        }
        System.out.println("Provide the age");
        scanner.nextLine();
        int age = Integer.parseInt(scanner.next());
        animal.setAge(age);
        return animal;
    }
}


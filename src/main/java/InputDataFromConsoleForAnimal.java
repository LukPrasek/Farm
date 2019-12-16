import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputDataFromConsoleForAnimal {


    public InputDataFromConsoleForAnimal() {
    }

    public Animal getAnimalDataFromUserConsole(Scanner scanner) throws IllegalArgumentException {
        // scanner.nextLine();
        System.out.println("Select the species from the list by providing the name");
        System.out.println(AnimalSpecies.CAT.toString() + "; " + AnimalSpecies.DOG.toString() + "; "
                + AnimalSpecies.HORSE.toString() + "; " + AnimalSpecies.COW.toString() + "; " + AnimalSpecies.PIG.toString());
        Animal animal = new Animal();
        String animalSpeciesName = scanner.next();
        animal.setAnimalSpecies(AnimalSpecies.valueOf(animalSpeciesName.toUpperCase()));
        getVaccinatedDataFromUser(animal, scanner);
        getAgeOfAnimalFromUser(scanner, animal);
        return animal;
    }

    private void getAgeOfAnimalFromUser(Scanner scanner, Animal animal) {
        while (true) {
            System.out.println("Provide the age");
            String ageString = scanner.next();
            String patternString = "\\d+";
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(ageString);
            if (matcher.matches()) {
                int age = Integer.parseInt(ageString);
                animal.setAge(age);
                break;
            } else {
                System.out.println("Wrong input, try again giving the number");
            }
        }
    }

    private void getVaccinatedDataFromUser(Animal animal, Scanner scanner) {
        System.out.println("Is the animal vaccinated - true or false");
        String vaccinated;

        while (true) {
            vaccinated = scanner.next();
            if (((vaccinated.equalsIgnoreCase("true")) || vaccinated.equalsIgnoreCase("false"))) {
                animal.setVaccinated(Boolean.parseBoolean(vaccinated));
                break;
            } else {
                System.out.println("Wrong input, try again write true or false");
            }
        }
    }
}


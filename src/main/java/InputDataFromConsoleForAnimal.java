import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputDataFromConsoleForAnimal {


    public InputDataFromConsoleForAnimal() {
    }

    public Animal getAnimalDataFromUserConsole(ScannerAsker scannerAsker) throws IllegalArgumentException {
        System.out.println(AnimalSpecies.CAT.toString() + "; " + AnimalSpecies.DOG.toString() + "; "
                + AnimalSpecies.HORSE.toString() + "; " + AnimalSpecies.COW.toString() + "; " + AnimalSpecies.PIG.toString());
        String animalSpeciesName = scannerAsker.askString("Select the species from the list by providing the name");

        return Animal.anAnimalBuilder()
                .withAnimalSpecies(AnimalSpecies.valueOf(animalSpeciesName.toUpperCase()))
                .withIsVaccinated(getVaccinatedDataFromUser(scannerAsker.askString("Is the animal vaccinated - true or false")))
                .withAge(getAgeOfAnimalFromUser(scannerAsker.askString("Provide the age")))
                .build();
    }

    private int getAgeOfAnimalFromUser(String message) {

        String patternString = "\\d+";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(message);
        try {
            if (matcher.matches()) {
                return Integer.parseInt(message);
            } else {
                throw new IllegalArgumentException("Age should be Integer and value is set to 0");
            }
        } catch (IllegalArgumentException a) {
            a.printStackTrace();
            System.out.println(a.getMessage());
        }
        return 0;
    }

    private boolean getVaccinatedDataFromUser(String message) {
        try {
            if (((message.equalsIgnoreCase("true")) || message.equalsIgnoreCase("false"))) {
                return Boolean.parseBoolean(message);
            } else {
                throw new IllegalArgumentException("Wrong input, value is set to false");
            }

        } catch (IllegalArgumentException a) {
            a.printStackTrace();
            System.out.println(a.getMessage());

        }
        return false;
    }
}

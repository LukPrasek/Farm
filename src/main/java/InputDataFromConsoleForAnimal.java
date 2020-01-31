import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputDataFromConsoleForAnimal {


    public InputDataFromConsoleForAnimal() {
    }

    public Animal getAnimalDataFromUserConsole(ScannerAsker scannerAsker) throws IllegalArgumentException {
         System.out.println("Select the species from the list by providing the name");
        System.out.println(AnimalSpecies.CAT.toString() + "; " + AnimalSpecies.DOG.toString() + "; "
                + AnimalSpecies.HORSE.toString() + "; " + AnimalSpecies.COW.toString() + "; " + AnimalSpecies.PIG.toString());
        String animalSpeciesName = scannerAsker.askString("");
               return Animal.anAnimalBuilder()
                .withAnimalSpecies(AnimalSpecies.valueOf(animalSpeciesName.toUpperCase()))
                .withIsVaccinated(getVaccinatedDataFromUser(scannerAsker))
                .withAge(getAgeOfAnimalFromUser(scannerAsker))
                .build();
    }

    private int getAgeOfAnimalFromUser(ScannerAsker scannerAsker) {
        while (true) {
            //System.out.println("Provide the age");
            String ageString = scannerAsker.askString("Provide the age");
            String patternString = "\\d+";
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(ageString);
            if (matcher.matches()) {
                return Integer.parseInt(ageString);
            } else {
                System.out.println("Wrong input, try again giving the number");
            }
        }
    }

    private boolean getVaccinatedDataFromUser(ScannerAsker scannerAsker) {
        //System.out.println("Is the animal vaccinated - true or false");
        String vaccinated;
        while (true) {
            vaccinated = scannerAsker.askString("Is the animal vaccinated - true or false");
            if (((vaccinated.equalsIgnoreCase("true")) || vaccinated.equalsIgnoreCase("false"))) {
                return Boolean.parseBoolean(vaccinated);
            } else {
                System.out.println("Wrong input, try again write true or false");
            }
        }
    }
}


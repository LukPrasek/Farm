public class AnimalSpeciesMapper {
    public AnimalSpecies mapToAnimalGrade(String name) {
        AnimalSpecies animalSpecies = AnimalSpecies.valueOf(name);
        return animalSpecies;
    }
}

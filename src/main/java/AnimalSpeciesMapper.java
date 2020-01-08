public class AnimalSpeciesMapper {
    public AnimalSpecies mapToAnimalGrade(String name) {
        return AnimalSpecies.valueOf(name);
    }
}
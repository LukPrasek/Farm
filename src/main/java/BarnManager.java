import java.util.List;

public class BarnManager {
    private Barn barn;
    private List<Barn> barns;

    public BarnManager() {
    }

    public Barn createNewBarn(String name) {
        barn = new Barn(name);
        return barn;
    }

    public void saveBarnToFile(Barn barn, String pathToFile) {
        FileWriterAndReader fileWriterAndReader = new FileWriterAndReader(pathToFile);
        fileWriterAndReader.writeObjectToFile(barn);
    }

    public void addAnimalToBarn(Animal animal) {

        barn.getAnimalList().add(animal);
    }

    public void addAnimalToSpecificBarn(Animal animal, Barn barn) {
        barn.getAnimalList().add(animal);
    }

    public void showAllBarns() {
        barns = DatabaseReader.getBarnList();
        barns.forEach(System.out::println);
    }
}

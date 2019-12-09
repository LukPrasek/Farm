import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BarnManager {
    private Barn barn;

    public BarnManager() {
    }

    public Barn createNewBarn() {
        barn = new Barn(getDataFromUserForBarnCreation());
        String pathToFile = Menu.getPathToFolder() + "\\" + barn.getId() + ".txt";
        saveBarnToFile(barn, pathToFile);
        return barn;
    }

    private String getDataFromUserForBarnCreation() {
        System.out.println("Give the name of the barn");
        Scanner scanner = new Scanner(System.in);
        String answer=scanner.next();
        return answer;
    }

    public void deleteSelectedBarn() {
        System.out.println("Provide the barn ID to be deleted");
        showAllBarns();
        Scanner scanner = new Scanner(System.in);
        int barnID = scanner.nextInt();
        FileWriterAndReader fileWriterAndReader = new FileWriterAndReader(Menu.getPathToFolder());
        String[] filesInCatalog = fileWriterAndReader.getAllFilesFromCatalog();
        for (int i = 0; i < filesInCatalog.length; i++) {
            if (filesInCatalog[i].equalsIgnoreCase(barnID + ".txt")) {
                fileWriterAndReader.deleteFile(Menu.getPathToFolder() + "\\" + barnID + ".txt");
            }
        }
        scanner.close();
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
        List<Barn> list = DatabaseReader.getBarnList();
        list.forEach(System.out::println);
    }

    public void showBarnWithMostAnimals() {
        List<Barn> barns = DatabaseReader.getBarnList();
        Barn barn = barns.stream().max(Comparator.comparingInt(x -> x.getAnimalList().size())).get();
        System.out.println(barn.toString());
    }
}

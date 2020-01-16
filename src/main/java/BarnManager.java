import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BarnManager {
    private Barn barn;

    public BarnManager() {
    }

    public Barn createNewBarn() {
        barn = Barn.barnBuilder().withName(getDataFromUserForBarnCreation()).build();
        String pathToFile = Menu.getPathToFolder() + "\\" + barn.getId() + ".txt";
        saveBarnToFile(barn, pathToFile);
        return barn;
    }

    private String getDataFromUserForBarnCreation() {
        System.out.println("Give the name of the barn");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next();
        //scanner.close();
        return answer;
    }

    public void deleteSelectedBarn(String pathToFolder) {
        System.out.println("Provide the barn ID to be deleted");
        showAllBarns();
        Scanner scanner = new Scanner(System.in);
        int barnID = scanner.nextInt();
        FileWriterAndReader fileWriterAndReader = new FileWriterAndReader(pathToFolder);
        String[] filesInCatalog = fileWriterAndReader.getAllFilesFromCatalog();
        for (int i = 0; i < filesInCatalog.length; i++) {
            if (filesInCatalog[i].equalsIgnoreCase(barnID + ".txt")) {
                fileWriterAndReader.deleteFile(pathToFolder + "\\" + barnID + ".txt");
            } else {
                System.out.println("Couln't find file with given name");
            }
        }
    }

    public void saveBarnToFile(Barn barn, String pathToFile) {
        FileWriterAndReader fileWriterAndReader = new FileWriterAndReader(pathToFile);
        fileWriterAndReader.writeObjectToFile(barn, Menu.getPathToFolder());
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

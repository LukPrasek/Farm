import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BarnManager {
    private Barn barn;


    public BarnManager() {    }

    public Barn createNewBarn(ScannerAsker scannerAsker) throws FarmException {
        barn = Barn.barnBuilder().withName(scannerAsker.askString("Give the name of the barn")).build();
        String pathToFile = Menu.getPathToFolder() + "\\" + barn.getId() + ".txt";
        saveBarnToFile(barn, pathToFile);
        return barn;
    }


    public void deleteSelectedBarn(ScannerAsker scannerAsker, String pathToFolder) throws WrongIdException {
        showAllBarns();
        int barnID = scannerAsker.askInt("Provide the barn ID to be deleted");
        FileWriterAndReader fileWriterAndReader = new FileWriterAndReader(pathToFolder);
        String[] filesInCatalog = fileWriterAndReader.getAllFilesFromCatalog();
        for (int i = 0; i < filesInCatalog.length; i++) {
            if (filesInCatalog[i].equalsIgnoreCase(barnID + ".txt")) {
                fileWriterAndReader.deleteFile(pathToFolder + "\\" + barnID + ".txt");
            } else {
                throw new WrongIdException("Wrong barn ID");
            }
        }
    }

    public void saveBarnToFile(Barn barn, String pathToFile) throws FarmException {
        FileWriterAndReader fileWriterAndReader = new FileWriterAndReader(pathToFile);
        fileWriterAndReader.writeObjectToFile(barn, Menu.getPathToFolder());
    }

    public void showAllBarns() {
        List<Barn> list = DatabaseReader.getBarnList();
        list.forEach(System.out::println);
    }

    public Barn showBarnWithMostAnimals() {
        List<Barn> barns = DatabaseReader.getBarnList();
        return barns.stream().max(Comparator.comparingInt(x -> x.getAnimalList().size())).get();

    }
}

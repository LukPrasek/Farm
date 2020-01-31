import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BarnManager {
    private Barn barn;
    private ScannerAsker scannerAsker;

    public BarnManager() {
        scannerAsker = new ScannerAsker(System.in, System.out);
    }

    public Barn createNewBarn() throws FarmException {
        barn = Barn.barnBuilder().withName(scannerAsker.askString("Give the name of the barn")).build();
        String pathToFile = Menu.getPathToFolder() + "\\" + barn.getId() + ".txt";
        saveBarnToFile(barn, pathToFile);
        return barn;
    }

//    private String getDataFromUserForBarnCreation() {
//        System.out.println("Give the name of the barn");
//        Scanner scanner = new Scanner(System.in);
//        String answer = scanner.next();
//        //scanner.close();
//        return answer;
//    }

    public void deleteSelectedBarn(String pathToFolder) throws WrongIdException {
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

    public void showBarnWithMostAnimals() {
        List<Barn> barns = DatabaseReader.getBarnList();
        Barn barn = barns.stream().max(Comparator.comparingInt(x -> x.getAnimalList().size())).get();
        System.out.println(barn.toString());
    }
}

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private static String pathToFolder = ".\\src\\main\\resources";
    private BarnManager barnManager;
    private AnimalMenager animalMenager;
    private FileWriterAndReader fwr;
    private DatabaseReader databaseReader;
    private Scanner scanner = new Scanner(System.in);


    public static String getPathToFolder() {
        return pathToFolder;
    }

    public Menu() {
        barnManager = new BarnManager();
        animalMenager = new AnimalMenager();
        databaseReader = new DatabaseReader();
    }

    public void startApp() {

        executeCases();
    }

    private void executeCases() {
        String answer;
        do {
            databaseReader.checkIfFileExistsAndReadItIfSo(pathToFolder);
            printOptionList();
            answer = scanner.nextLine();
            switch (answer) {
                case ("1"):
                    Barn barn = barnManager.createNewBarn(getDataFromUserForBarnCreation());
                    String pathToFile = pathToFolder + "\\" + barn.getId() + ".txt";
                    barnManager.saveBarnToFile(barn, pathToFile);
                    break;
                case ("2"):
                    animalMenager.createNewAnimalAndAddToList();
                    break;
                case ("3"):
                    showAllBarns();
                    break;
                case ("4"):
                    barnManager.showBarnWithMostAnimals();
                    break;
                case ("5"):
                    animalMenager.showFiveOldestAnimals();
                    break;
                case ("6"):
                    animalMenager.showMostNumerousAnimalSpecies();
                    break;
                case ("7"):
                    deleteSelectedBarnAndAssignedAnimals();
                    break;
            }

        } while (!answer.equalsIgnoreCase("exit"));
    }


    private void deleteSelectedBarnAndAssignedAnimals() {
        System.out.println("Provide the barn ID to be deleted");
        barnManager.showAllBarns();
        int id = scanner.nextInt();
        try {
            barnManager.deleteSelectedBarn(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAllBarns() {
        barnManager.showAllBarns();
    }

    private String getDataFromUserForBarnCreation() {
        System.out.println("Give the name of the barn");
        return scanner.next();
    }

    private void printOptionList() {
        System.out.println("What would you like to do");
        System.out.println("1 - add a new barn");
        System.out.println("2 - add a new animal");
        System.out.println("3 - show all barns");
        System.out.println("4 - show barn with most animals");
        System.out.println("5 - show five oldest animals");
        System.out.println("6 - show most numerous animal species");
        System.out.println("7 - delete the barn with all the animals");
        System.out.println("exit");

    }
}

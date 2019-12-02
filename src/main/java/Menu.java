import java.util.Scanner;

public class Menu {
    private static String pathToFolder = "D:\\doksztalcanie\\Intellij\\powtorka\\src\\files";
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
                    animalMenager.createNewAnimalAndAddToList(AnimalSpecies.COW, 3, true);
                    break;
                case ("3"):
                    showAllBarns();
                    break;
                case ("4"):
                    //        showBarnWithMostAnimals();
                    break;
                case ("5"):

                    //        showFiveOldestAnimals();
                    break;
                case ("6"):
                    //        showMostNumerousAnimalSpecies();
                    break;
                case ("7"):
                    //        deleteSelectedBarnAndAssignedAnimals();
                    break;
            }

        } while (!answer.equalsIgnoreCase("exit"));
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
        System.out.println("6 - delete a barn with all the animals");
        System.out.println("exit");

    }
}

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {
    private static String pathToFolder = ".\\src\\main\\resources\\folderWithBarns\\";
    private BarnManager barnManager;
    private AnimalManager animalManager;
    private FileWriterAndReader fwr;
    private DatabaseReader databaseReader;
    private ScannerAsker scannerAsker;
    private Scanner scanner = new Scanner(System.in);

    public static String getPathToFolder() {
        return pathToFolder;
    }

    public Menu() {
        barnManager = new BarnManager();
        animalManager = new AnimalManager();
        databaseReader = new DatabaseReader(Menu.getPathToFolder());
        scannerAsker = new ScannerAsker(System.in, System.out);
    }

    public void startApp() {
        try {
            executeCases();
        } catch (FarmException e) {
            e.printStackTrace();
        }
    }

    private void executeCases() throws FarmException {
        String answer;
        do {
            databaseReader.readFilesFromFolder(pathToFolder);
            printOptionList();
            answer = scanner.nextLine();
            if (checkAnswerCorrectness(answer)) {
                switch (answer) {
                    case ("1"):
                        barnManager.createNewBarn(scannerAsker);
                        break;
                    case ("2"):
                        animalManager.createNewAnimalAndAddToList();
                        break;
                    case ("3"):
                        barnManager.showAllBarns();
                        break;
                    case ("4"):
                        System.out.println(barnManager.showBarnWithMostAnimals());
                        break;
                    case ("5"):
                        animalManager.showFiveOldestAnimals();
                        break;
                    case ("6"):
                        animalManager.showMostNumerousAnimalSpecies();
                        break;
                    case ("7"):
                        try {
                            barnManager.deleteSelectedBarn(scannerAsker,pathToFolder);
                        } catch (WrongIdException e) {
                            e.getMessage();
                        }
                        break;
                }
            } else {
                System.out.println("Wrong input number, try again");
            }
        }
        while (!answer.equalsIgnoreCase("exit"));
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

    private boolean checkAnswerCorrectness(String answer) {
        String regex = "^([1-7])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(answer);
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }
}
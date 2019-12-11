import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatabaseReader {
    private Barn barn;
    private static LinkedList<Barn> barnListReadFromFile;//5= new LinkedList<>();
    private static LinkedList<Animal> animalListReadFromFile;//= new LinkedList<>();
    private FileWriterAndReader fileWriterAndReader;

    public DatabaseReader() {
        fileWriterAndReader = new FileWriterAndReader(Menu.getPathToFolder());
    }

    public void checkIfFileExistsAndReadItIfSo(String pathToFolder) {
        String[] filesInCatalog = fileWriterAndReader.getAllFilesFromCatalog();
        barnListReadFromFile = new LinkedList<>();
        animalListReadFromFile= new LinkedList<>();
        if (filesInCatalog.length > 0) {
            File fileToRead;
            for (String fileName : filesInCatalog) {
                fileToRead = new File(pathToFolder + "\\" + fileName);
                FileWriterAndReader fwr = new FileWriterAndReader(fileToRead.getPath());
                try {
                    if (fwr.checkIfFileIsNotEmpty()) {//fileToRead.getPath())
                        barnListReadFromFile.add(mapStringToBarnObjects(fwr.readFile(fileToRead)));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Barn mapStringToBarnObjects(String string) {
        barn = new Barn();
        String[] singleWordsInLineTable = string.split(":");
        barn.setId(Integer.parseInt(singleWordsInLineTable[0]));
        barn.setName(singleWordsInLineTable[1]);
        if (singleWordsInLineTable.length > 2) {
            String animalListAsString = singleWordsInLineTable[2];
            if (animalListAsString.length() > 2) {//brackets [] are visible in file even if the Animal list is empty
                barn.setAnimalList(mapStringToAnimalObject(animalListAsString));
            }
        }
        return barn;
    }

    private LinkedList<Animal> mapStringToAnimalObject(String s) {
        AnimalSpeciesMapper animalSpeciesMapper = new AnimalSpeciesMapper();
        convertStringDataFromTxtIntoAnimalObject(s, animalSpeciesMapper);
        return (LinkedList<Animal>) barn.getAnimalList();
    }

    private void convertStringDataFromTxtIntoAnimalObject(String s, AnimalSpeciesMapper animalSpeciesMapper) {
        String regex = "\\w+-\\d+-\\w+";//cow-true-3
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        StringBuilder stringBuilder = new StringBuilder();
        while (matcher.find()) {
            stringBuilder.append(matcher.group());
            stringBuilder.append(":");
        }
        createAnimalObjectWithRegex(animalSpeciesMapper, stringBuilder);
        System.out.println("Wielkosc listy animal w databasereaderze: "+animalListReadFromFile.size());
    }

    private void createAnimalObjectWithRegex(AnimalSpeciesMapper animalSpeciesMapper, StringBuilder stringBuilder) {
        if (stringBuilder.toString().length() > 2) {
            String[] singleAnimalDataFromTable = stringBuilder.toString().split(":");
            for (int i = 0; i < singleAnimalDataFromTable.length; i++) {
                String[] animalClassFieldsInTable = singleAnimalDataFromTable[i].split("-");
                Animal animal = new Animal();
                animal.setAnimalSpecies(animalSpeciesMapper.mapToAnimalGrade(animalClassFieldsInTable[0]));
                animal.setAge(Integer.parseInt(animalClassFieldsInTable[1]));
                animalListReadFromFile.add(animal);
                barn.addAnimalToList(animal);
            }
        }
    }

    public static LinkedList<Barn> getBarnList() {
        return barnListReadFromFile;
    }

    public static LinkedList<Animal> getAllAnimalList() {
        return animalListReadFromFile;
    }
}

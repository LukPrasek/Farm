import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatabaseReader {
    private Barn barn;
    private static LinkedList<Barn> barnListReadFromFile;
    private static LinkedList<Animal> animalListReadFromFile;
    private FileWriterAndReader fileWriterAndReader;

    public DatabaseReader() {
        fileWriterAndReader = new FileWriterAndReader(Menu.getPathToFolder());
    }

    public void readFilesFromFolder(String pathToFolder) {
        String[] filesInCatalog = fileWriterAndReader.getAllFilesFromCatalog();
        barnListReadFromFile = new LinkedList<>();
        animalListReadFromFile = new LinkedList<>();
        if (filesInCatalog.length > 0) {
            File fileToRead;
            for (String fileName : filesInCatalog) {
                String pathToFile = pathToFolder + "\\" + fileName;
                fileToRead = new File(pathToFile);
                FileWriterAndReader fwr = new FileWriterAndReader(pathToFolder);
                if (fwr.checkIfFileIsNotEmpty(pathToFile)) {
                    try {
                        barnListReadFromFile.add(mapStringToBarnObjects(fwr.readFile(fileToRead)));//fwr.readFile(fileToRead)=1:Obora:null
                    } catch (FarmException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private Barn mapStringToBarnObjects(String string) {//1:Obora:null
        String[] singleWordsInLineTable = string.split(":");
        List<Animal> tempList = new LinkedList<>();
        if (singleWordsInLineTable.length > 2) {
            String animalListAsString = singleWordsInLineTable[2];
            if (animalListAsString.length() > 2) {//brackets [] are visible in file even if the Animal list is empty
                tempList = mapStringToAnimalObject(animalListAsString);
            }
        }
        barn = Barn.barnBuilder()
                .withId(Integer.parseInt(singleWordsInLineTable[0]))
                .withName(singleWordsInLineTable[1])
                .withAnimalList(tempList)
                .build();
        return barn;
    }

    private List<Animal> mapStringToAnimalObject(String s) {
        AnimalSpeciesMapper animalSpeciesMapper = new AnimalSpeciesMapper();
        return convertStringDataFromTxtIntoAnimalObject(s, animalSpeciesMapper);
    }

    private List<Animal> convertStringDataFromTxtIntoAnimalObject(String s, AnimalSpeciesMapper animalSpeciesMapper) {
        String regex = "\\w+-\\d+-\\w+";//cow-true-3
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        StringBuilder stringBuilder = new StringBuilder();
        while (matcher.find()) {
            stringBuilder.append(matcher.group());
            stringBuilder.append(":");
        }
        return createAnimalObjectsWithRegex(animalSpeciesMapper, stringBuilder);
    }

    private List<Animal> createAnimalObjectsWithRegex(AnimalSpeciesMapper animalSpeciesMapper, StringBuilder stringBuilder) {
        List<Animal> tempList = new LinkedList<>();
        if (stringBuilder.toString().length() > 2) {
            String[] singleAnimalDataFromTable = stringBuilder.toString().split(":");
            for (int i = 0; i < singleAnimalDataFromTable.length; i++) {
                String[] animalClassFieldsInTable = singleAnimalDataFromTable[i].split("-");
                Animal animal = Animal.anAnimalBuilder()
                        .withAnimalSpecies(animalSpeciesMapper.mapToAnimalGrade(animalClassFieldsInTable[0]))
                        .withAge(Integer.parseInt(animalClassFieldsInTable[1]))
                        .withIsVaccinated(Boolean.parseBoolean(animalClassFieldsInTable[2]))
                        .build();
                animalListReadFromFile.add(animal);
                tempList.add(animal);
            }
        }
        return tempList;
    }

    public static LinkedList<Barn> getBarnList() {
        return barnListReadFromFile;
    }

    public static LinkedList<Animal> getAllAnimalList() {
        return animalListReadFromFile;
    }
}

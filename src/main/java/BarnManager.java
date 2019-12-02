import java.io.IOException;
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

    public void deleteSelectedBarn(int barnID) throws IOException {

        FileWriterAndReader fileWriterAndReader = new FileWriterAndReader(Menu.getPathToFolder());
        String[] filesInCatalog = fileWriterAndReader.getAllFilesFromCatalog();
        for (int i = 0; i < filesInCatalog.length; i++) {
            if (filesInCatalog[i].equalsIgnoreCase(barnID + ".txt")) {
                fileWriterAndReader.deleteFile(Menu.getPathToFolder()+"\\"+barnID + ".txt");
            }
        }
    }
        public void saveBarnToFile (Barn barn, String pathToFile){
            FileWriterAndReader fileWriterAndReader = new FileWriterAndReader(pathToFile);
            fileWriterAndReader.writeObjectToFile(barn);
        }

        public void addAnimalToBarn (Animal animal){

            barn.getAnimalList().add(animal);
        }

        public void addAnimalToSpecificBarn (Animal animal, Barn barn){
            barn.getAnimalList().add(animal);
        }

        public void showAllBarns () {
            barns = DatabaseReader.getBarnList();
            barns.forEach(System.out::println);
        }
    }

import java.util.LinkedList;
import java.util.List;

public class Barn {

    private int id = 0;
    private String name;
    private List<Animal> animalList = new LinkedList<>();

    public Barn() {
    }

    public Barn(String name, List<Animal> animalList) {
        this.id = createNewOrSetId();
        this.name = name;
        this.animalList = animalList;
    }

    public Barn(String name) {
        this.id = createNewOrSetId();
        this.name = name;
    }

    public void addAnimalToList(Animal animal) {
        animalList.add(animal);
    }

    private int createNewOrSetId() {
        if (DatabaseReader.getBarnList() != null) {
            if (DatabaseReader.getBarnList().size() > 0) {
                return id = DatabaseReader.getBarnList().getLast().getId() + 1;
            } else {
                return id = 1;
            }

        } else {
            return id = 1;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }



    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    @Override
    public String toString() {
        return id + ":" + name + ":" + animalList;
    }
}

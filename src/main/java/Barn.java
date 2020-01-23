import java.util.LinkedList;
import java.util.List;

public class Barn {

    private int id = 0;
    private String name;
    private List<Animal> animalList;

//    public Barn() {
//    }

    private Barn(String name, List<Animal> animalList) {
        this.id = createNewOrSetId();
        this.name = name;
        this.animalList = animalList;
    }

    public Barn(String name) {
        this.id = createNewOrSetId();
        this.name = name;
    }

    public static final BarnBuilder barnBuilder() {
        return new BarnBuilder();
    }

    public void addAnimalToList(Animal animal) {
        System.out.println(animal.toString()+"z metody add");
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

    public static final class  BarnBuilder {
        private int id;
        private String name;
        private List<Animal> animalList;

        public BarnBuilder withId(int id) {
            //this.id = createNewOrSetId();
            this.id = id;
            return this;
        }

        public BarnBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public BarnBuilder withAnimalList(List<Animal> animalList) {
            this.animalList = animalList;
            return this;
        }

        public Barn build() {
            return new Barn(name, animalList);
        }

    }
}

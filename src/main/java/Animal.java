import java.util.Objects;

public class Animal implements Comparable {
    private AnimalSpecies animalSpecies;
    private int age;
    private boolean isVaccinated;

    public Animal() {
    }

    public Animal(AnimalSpecies animalSpecies, int age, boolean isVaccinated) {
        this.animalSpecies = animalSpecies;
        this.age = age;
        this.isVaccinated = isVaccinated;
    }


    public AnimalSpecies getAnimalSpecies() {
        return animalSpecies;
    }

    public void setAnimalSpecies(AnimalSpecies animalSpecies) {
        this.animalSpecies = animalSpecies;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isVaccinated() {
        return isVaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        isVaccinated = vaccinated;
    }

    @Override
    public String toString() {
        return "'" + animalSpecies + "-" + age + "-" + isVaccinated + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return getAge() == animal.getAge() &&
                isVaccinated() == animal.isVaccinated() &&
                getAnimalSpecies() == animal.getAnimalSpecies();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAnimalSpecies(), getAge(), isVaccinated());
    }

    @Override
    public int compareTo(Object o) {
        Animal animal = (Animal) o;
        if (animalSpecies.equals(animal.getAnimalSpecies()))
            return Integer.compare(age, ((Animal) o).getAge());
        else {
            return Boolean.compare(isVaccinated, animal.isVaccinated);
        }
    }
}


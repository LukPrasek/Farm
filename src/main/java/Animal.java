import java.util.Objects;

public class Animal implements Comparable {
    private AnimalSpecies animalSpecies;
    private int age;
    private boolean isVaccinated;


    private Animal(AnimalSpecies animalSpecies, int age, boolean isVaccinated) {
        this.animalSpecies = animalSpecies;
        this.age = age;
        this.isVaccinated = isVaccinated;
    }

    public static Builder anAnimalBuilder() {
        return new Builder();
    }

    public AnimalSpecies getAnimalSpecies() {
        return animalSpecies;
    }

    public int getAge() {
        return age;
    }

    public boolean isVaccinated() {
        return isVaccinated;
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

    public static final class Builder {
        private AnimalSpecies animalSpecies;
        private int age;
        private boolean isVaccinated;


        public Builder withAnimalSpecies(AnimalSpecies animalSpecies) {
            this.animalSpecies = animalSpecies;
            return this;
        }

        public Builder withAge(int age) {
            this.age = age;
            return this;
        }

        public Builder withIsVaccinated(boolean isVaccinated) {
            this.isVaccinated = isVaccinated;
            return this;
        }

        public Animal build() {
            return new Animal(animalSpecies, age, isVaccinated);
        }
    }


}


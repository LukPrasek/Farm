public class Animal {
    private AnimalSpecies animalSpecies;
    private int age;
    private boolean isVaccinated;
    public Animal(){
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
        return "'"+ animalSpecies +"-" +age +"-"+ isVaccinated+"'";
    }
}


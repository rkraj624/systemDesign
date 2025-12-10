package dsa.practice.reflections;

import dsa.practice.annotation.Category;

@Category(diet = {"Carnivore"}, animalType = "Bird")
@Category
public class Eagle {
    private String breed;
    private boolean canSwim;
    public boolean canFly;

    public Eagle() {
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public boolean isCanSwim() {
        return canSwim;
    }

    public void setCanSwim(boolean canSwim) {
        this.canSwim = canSwim;
    }

    public boolean isCanFly() {
        return canFly;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }
    @Deprecated
    public void fly(String breed, boolean canFly, boolean canSwim) {
        System.out.println("breed: " + breed + " can fly: " + canFly + " can swim: " + canSwim);
    }
    @Override
    public String toString() {
        return "Eagle [breed=" + breed + ", canSwim=" + canSwim + ", canFly=" + canFly + "]";
    }

}

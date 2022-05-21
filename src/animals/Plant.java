package animals;

import java.util.ArrayList;

public class Plant implements Runnable{
    public static final int maxPopulation = 10000;

    public static int getMaxPopulation() {
        return maxPopulation;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getMaxNumberOneCage() {
        return maxNumberOneCage;
    }

    public void setMaxNumberOneCage(int maxNumberOneCage) {
        this.maxNumberOneCage = maxNumberOneCage;
    }

    private double weight;
    private int maxNumberOneCage;



    public Plant() {
        weight = 1.0;
    }



    @Override
    public void run() {

    }
}

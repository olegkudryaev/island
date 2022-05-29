package animals;

import java.util.ArrayList;

//коза
public class Goat extends Herbivorous implements Runnable {

    public Goat() {
        gaveBirth = false;
        moved = true;
        deadBody = false;
        surviveWithoutFoodLeft = surviveWithoutFood = 5;
        weight = 65;
        speed = 1;
        maximumFood = 10;
        satiety = maximumFood / 3;
//        satiety = 8; // для тестов
        hungerPerDay = maximumFood * 0.1;
        maxPopulation = 107;
    }

    @Override
    public void run() {
//        eat();
    }

    public int getSurviveWithoutFood() {
        return surviveWithoutFood;
    }

}

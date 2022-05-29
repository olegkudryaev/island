package animals;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Wolf extends Predator implements Runnable {

    public Wolf() {
        gaveBirth = false;
        moved = true;
        deadBody = false;
        surviveWithoutFoodLeft = surviveWithoutFood = 10;
        weight = 50;
        speed = 3;
        maximumFood = 8;
        satiety = maximumFood / 3;
        hungerPerDay = maximumFood * 0.1;
        maxPopulation = 30;
    }

    public int getSurviveWithoutFood() {
        return surviveWithoutFood;
    }

    @Override
    public void eat(Island.Square squares) {
        for (int i = 0; i < squares.list.size(); i++) {
            int chanceToKillFood = ThreadLocalRandom.current().nextInt(0, 10);
            Object target = squares.list.get(i);
            if (getSatiety() >= getMaximumFood()) {
                break;
            }
            if ((target instanceof Goat && chanceToKillFood < 8) || (target instanceof Goat && ((Goat) target).deadBody)) {
                if (getMaximumFood() - getSatiety() >= ((Goat) target).getWeight()) {
                    setSatiety(getSatiety() + ((Goat) target).getWeight());
                    ((Goat) target).setWeight(0);
                    ((Goat) target).deadBody = true;
                } else if (getMaximumFood() - getSatiety() < ((Goat) target).getWeight()) {
                    ((Goat) target).setWeight(((Goat) target).getWeight() - (getMaximumFood() - getSatiety()));
                    setSatiety(getMaximumFood());
                    ((Goat) target).deadBody = true;
                }
            }
        }
        if (getSatiety() > 0) {
            setSatiety(getSatiety() - getHungerPerDay());
        }
        if (getSatiety() <= 0) {
            setSurviveWithoutFoodLeft(getSurviveWithoutFoodLeft() - 1);
            setSatiety(0);
        }

    }

    @Override
    public void run() {

    }
}

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
        satiety = maximumFood;
//        satiety = 5; // для тестов
        hungerPerDay = satiety * 0.1;
        maxPopulation = 30;
    }

    public int getSurviveWithoutFood() {
        return surviveWithoutFood;
    }

    @Override
    public void eat(Island.Square squares) {
        int chanceToKillFood = ThreadLocalRandom.current().nextInt(0, 10);
//        int chanceToKillFood = 5;
        for (int i = 0; i < squares.list.size(); i++) {
            if (getSatiety() >= getMaximumFood()) {
                setSatiety(getSatiety() - getHungerPerDay());
                break;
            }
            Object target = squares.list.get(i);
            if (target instanceof Goat && chanceToKillFood < 8 && getHungerPerDay() <= ((Goat) target).getWeight()) {
                setSatiety(getSatiety() + getHungerPerDay());
                ((Goat) target).setWeight(((Goat) target).getWeight() - getHungerPerDay());
                i--;
                ((Goat) target).deadBody = true;

                // нужно повесить тикер труп

            } else if (target instanceof Goat && chanceToKillFood < 8 && getHungerPerDay() >= ((Goat) target).getWeight()) {
                setSatiety(getSatiety() + ((Goat) target).getWeight());
                ((Goat) target).setWeight(0);
                // нужно повесить тикер труп
            }

        }

    }

    @Override
    public void run() {

    }
}

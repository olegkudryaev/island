package animals;

import java.util.concurrent.ThreadLocalRandom;

public class Anaconda extends Herbivorous implements Runnable{

    public Anaconda() {
        gaveBirth = false;
        moved = true;
        deadBody = false;
        surviveWithoutFoodLeft = surviveWithoutFood = 15;
        weight = 15;
        speed = 1;
        maximumFood = 3;
        satiety = maximumFood / 3;
        hungerPerDay = maximumFood * 0.1;
        maxPopulation = 30;
    }

    public int getSurviveWithoutFood() {
        return surviveWithoutFood;
    }

    @Override
    public void eat(Island.Square squares) {

        int chanceToKillFox = 2;
        int chanceToKillRabbit = 2;
        int chanceToKillMouse = 4;
        int chanceToKillDuck = 1;

        for (int i = 0; i < squares.list.size(); i++) {
            int chanceToKillFood = ThreadLocalRandom.current().nextInt(0, 10);
            Object target = squares.list.get(i);
            if (getSatiety() >= getMaximumFood()) {
                break;
            }
            if ((target instanceof Fox && chanceToKillFood < chanceToKillFox) || (target instanceof Fox && ((Fox) target).deadBody)) {
                if (getMaximumFood() - getSatiety() >= ((Fox) target).getWeight()) {
                    setSatiety(getSatiety() + ((Fox) target).getWeight());
                    ((Fox) target).setWeight(0);
                    ((Fox) target).deadBody = true;
                } else if (getMaximumFood() - getSatiety() < ((Fox) target).getWeight()) {
                    ((Fox) target).setWeight(((Fox) target).getWeight() - (getMaximumFood() - getSatiety()));
                    setSatiety(getMaximumFood());
                    ((Fox) target).deadBody = true;
                }
            }
            if ((target instanceof Rabbit && chanceToKillFood < chanceToKillRabbit) || (target instanceof Rabbit && ((Rabbit) target).deadBody)) {
                if (getMaximumFood() - getSatiety() >= ((Rabbit) target).getWeight()) {
                    setSatiety(getSatiety() + ((Rabbit) target).getWeight());
                    ((Rabbit) target).setWeight(0);
                    ((Rabbit) target).deadBody = true;
                } else if (getMaximumFood() - getSatiety() < ((Rabbit) target).getWeight()) {
                    ((Rabbit) target).setWeight(((Rabbit) target).getWeight() - (getMaximumFood() - getSatiety()));
                    setSatiety(getMaximumFood());
                    ((Rabbit) target).deadBody = true;
                }
            }
            if ((target instanceof Mouse && chanceToKillFood < chanceToKillMouse) || (target instanceof Mouse && ((Mouse) target).deadBody)) {
                if (getMaximumFood() - getSatiety() >= ((Mouse) target).getWeight()) {
                    setSatiety(getSatiety() + ((Mouse) target).getWeight());
                    ((Mouse) target).setWeight(0);
                    ((Mouse) target).deadBody = true;
                } else if (getMaximumFood() - getSatiety() < ((Mouse) target).getWeight()) {
                    ((Mouse) target).setWeight(((Mouse) target).getWeight() - (getMaximumFood() - getSatiety()));
                    setSatiety(getMaximumFood());
                    ((Mouse) target).deadBody = true;
                }
            }
            if ((target instanceof Duck && chanceToKillFood < chanceToKillDuck) || (target instanceof Duck && ((Duck) target).deadBody)) {
                if (getMaximumFood() - getSatiety() >= ((Duck) target).getWeight()) {
                    setSatiety(getSatiety() + ((Duck) target).getWeight());
                    ((Duck) target).setWeight(0);
                    ((Duck) target).deadBody = true;
                } else if (getMaximumFood() - getSatiety() < ((Duck) target).getWeight()) {
                    ((Duck) target).setWeight(((Duck) target).getWeight() - (getMaximumFood() - getSatiety()));
                    setSatiety(getMaximumFood());
                    ((Duck) target).deadBody = true;
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

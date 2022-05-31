package animals;

import java.util.concurrent.ThreadLocalRandom;

public class Eagle extends Predator implements Runnable{

    public Eagle(){
        gaveBirth = false;
        moved = true;
        deadBody = false;
        surviveWithoutFoodLeft = surviveWithoutFood = 5;
        weight = 6;
        speed = 3;
        maximumFood = 1;
        satiety = maximumFood / 3;
        hungerPerDay = maximumFood * 0.1;
        maxPopulation = 20;
    }

    public int getSurviveWithoutFood() {
        return surviveWithoutFood;
    }

    @Override
    public void eat(Island.Square squares) {

        int chanceToKillFox = 1;
        int chanceToKillRabbit = 9;
        int chanceToKillMouse = 9;
        int chanceToKillDuck = 8;

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
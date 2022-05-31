package animals;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Mouse extends Herbivorous implements Runnable{

    public Mouse(){
        gaveBirth = false;
        moved = true;
        deadBody = false;
        surviveWithoutFoodLeft = surviveWithoutFood = 1;
        weight = 0.05;
        speed = 1;
        maximumFood = 0.01;
        satiety = maximumFood / 3;
        hungerPerDay = maximumFood * 0.1;
        maxPopulation = 300;
    }

    public int getSurviveWithoutFood() {
        return surviveWithoutFood;
    }

    @Override
    public void eat(Island.Square squares) {
        ArrayList<Object> copySquaresList = squares.list; // делаем копию листа для перебора, все исправления делаем в оригинале.
        int chanceToKillCaterpillar = 9;
        for (int l = 0; l < copySquaresList.size(); l++) { // ищем траву
            Object target = copySquaresList.get(l);
            int chanceToKillFood = ThreadLocalRandom.current().nextInt(0, 10);
            if (target instanceof Plant && getSatiety() >= getMaximumFood()) {
                break;
            } else if (target instanceof Plant && getSatiety() < getMaximumFood() && ((Plant) target).getWeight() > 0) {
                if (getMaximumFood() - getSatiety() >= ((Plant) target).getWeight()) {
                    setSatiety(getSatiety() + ((Plant) target).getWeight());
                    ((Plant) target).setWeight(0);
                } else if (getMaximumFood() - getSatiety() < ((Plant) target).getWeight()) {
                    ((Plant) target).setWeight(((Plant) target).getWeight() - (getMaximumFood() - getSatiety()));
                    setSatiety(getSatiety() + getMaximumFood() - getSatiety());
                }
            }
            if ((target instanceof Caterpillar && chanceToKillFood < chanceToKillCaterpillar) || (target instanceof Caterpillar && ((Caterpillar) target).deadBody)) {
                if (getMaximumFood() - getSatiety() >= ((Caterpillar) target).getWeight()) {
                    setSatiety(getSatiety() + ((Caterpillar) target).getWeight());
                    ((Caterpillar) target).setWeight(0);
                    ((Caterpillar) target).deadBody = true;
                } else if (getMaximumFood() - getSatiety() < ((Caterpillar) target).getWeight()) {
                    ((Caterpillar) target).setWeight(((Caterpillar) target).getWeight() - (getMaximumFood() - getSatiety()));
                    setSatiety(getMaximumFood());
                    ((Caterpillar) target).deadBody = true;
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
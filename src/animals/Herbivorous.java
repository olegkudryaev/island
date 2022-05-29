package animals;

import java.util.ArrayList;

public abstract class Herbivorous extends Animal {

    @Override
    public void eat(Island.Square squares) {
        ArrayList<Object> copySquaresList = squares.list; // делаем копию листа для перебора, все исправления делаем в оригинале.

        for (int l = 0; l < copySquaresList.size(); l++) { // ищем траву
            Object target = copySquaresList.get(l);
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
        }
        if (getSatiety() > 0) {
            setSatiety(getSatiety() - getHungerPerDay());
        }
        if (getSatiety() <= 0) {
            setSurviveWithoutFoodLeft(getSurviveWithoutFoodLeft() - 1);
            setSatiety(0);
        }
    }
}






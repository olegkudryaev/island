package animals;

import java.util.concurrent.ThreadLocalRandom;

public class Bear extends Predator implements Runnable{

    public Bear() {
        gaveBirth = false;
        moved = true;
        deadBody = false;
        surviveWithoutFoodLeft = surviveWithoutFood = 15;
        weight = 500;
        speed = 2;
        maximumFood = 80;
        satiety = maximumFood / 3;
        hungerPerDay = maximumFood * 0.1;
        maxPopulation = 5;
    }

    public int getSurviveWithoutFood() {
        return surviveWithoutFood;
    }

    @Override
    public void eat(Island.Square squares) {

        int chanceToKillAnaconda = 8;
        int chanceToKillHorse = 4;
        int chanceToKillDeer = 8;
        int chanceToKillRabbit = 8;
        int chanceToKillMouse = 9;
        int chanceToKillGoat = 7;
        int chanceToKillSheep = 7;
        int chanceToKillBoar = 5;
        int chanceToKillBuffalo = 2;
        int chanceToKillDuck = 1;

        for (int i = 0; i < squares.list.size(); i++) {
            int chanceToKillFood = ThreadLocalRandom.current().nextInt(0, 10);
            Object target = squares.list.get(i);
            if (getSatiety() >= getMaximumFood()) {
                break;
            }
            if ((target instanceof Anaconda && chanceToKillFood < chanceToKillAnaconda) || (target instanceof Anaconda && ((Anaconda) target).deadBody)) {
                if (getMaximumFood() - getSatiety() >= ((Anaconda) target).getWeight()) {
                    setSatiety(getSatiety() + ((Anaconda) target).getWeight());
                    ((Anaconda) target).setWeight(0);
                    ((Anaconda) target).deadBody = true;
                } else if (getMaximumFood() - getSatiety() < ((Anaconda) target).getWeight()) {
                    ((Anaconda) target).setWeight(((Anaconda) target).getWeight() - (getMaximumFood() - getSatiety()));
                    setSatiety(getMaximumFood());
                    ((Anaconda) target).deadBody = true;
                }
            }
            if ((target instanceof Horse && chanceToKillFood < chanceToKillHorse) || (target instanceof Horse && ((Horse) target).deadBody)) {
                if (getMaximumFood() - getSatiety() >= ((Horse) target).getWeight()) {
                    setSatiety(getSatiety() + ((Horse) target).getWeight());
                    ((Horse) target).setWeight(0);
                    ((Horse) target).deadBody = true;
                } else if (getMaximumFood() - getSatiety() < ((Horse) target).getWeight()) {
                    ((Horse) target).setWeight(((Horse) target).getWeight() - (getMaximumFood() - getSatiety()));
                    setSatiety(getMaximumFood());
                    ((Horse) target).deadBody = true;
                }
            }
            if ((target instanceof Deer && chanceToKillFood < chanceToKillDeer) || (target instanceof Deer && ((Deer) target).deadBody)) {
                if (getMaximumFood() - getSatiety() >= ((Deer) target).getWeight()) {
                    setSatiety(getSatiety() + ((Deer) target).getWeight());
                    ((Deer) target).setWeight(0);
                    ((Deer) target).deadBody = true;
                } else if (getMaximumFood() - getSatiety() < ((Deer) target).getWeight()) {
                    ((Deer) target).setWeight(((Deer) target).getWeight() - (getMaximumFood() - getSatiety()));
                    setSatiety(getMaximumFood());
                    ((Deer) target).deadBody = true;
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
            if ((target instanceof Goat && chanceToKillFood < chanceToKillGoat) || (target instanceof Goat && ((Goat) target).deadBody)) {
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
            if ((target instanceof Sheep && chanceToKillFood < chanceToKillSheep) || (target instanceof Sheep && ((Sheep) target).deadBody)) {
                if (getMaximumFood() - getSatiety() >= ((Sheep) target).getWeight()) {
                    setSatiety(getSatiety() + ((Sheep) target).getWeight());
                    ((Sheep) target).setWeight(0);
                    ((Sheep) target).deadBody = true;
                } else if (getMaximumFood() - getSatiety() < ((Sheep) target).getWeight()) {
                    ((Sheep) target).setWeight(((Sheep) target).getWeight() - (getMaximumFood() - getSatiety()));
                    setSatiety(getMaximumFood());
                    ((Sheep) target).deadBody = true;
                }
            }
            if ((target instanceof Boar && chanceToKillFood < chanceToKillBoar) || (target instanceof Boar && ((Boar) target).deadBody)) {
                if (getMaximumFood() - getSatiety() >= ((Boar) target).getWeight()) {
                    setSatiety(getSatiety() + ((Boar) target).getWeight());
                    ((Boar) target).setWeight(0);
                    ((Boar) target).deadBody = true;
                } else if (getMaximumFood() - getSatiety() < ((Boar) target).getWeight()) {
                    ((Boar) target).setWeight(((Boar) target).getWeight() - (getMaximumFood() - getSatiety()));
                    setSatiety(getMaximumFood());
                    ((Boar) target).deadBody = true;
                }
            }
            if ((target instanceof Buffalo && chanceToKillFood < chanceToKillBuffalo) || (target instanceof Buffalo && ((Buffalo) target).deadBody)) {
                if (getMaximumFood() - getSatiety() >= ((Buffalo) target).getWeight()) {
                    setSatiety(getSatiety() + ((Buffalo) target).getWeight());
                    ((Buffalo) target).setWeight(0);
                    ((Buffalo) target).deadBody = true;
                } else if (getMaximumFood() - getSatiety() < ((Buffalo) target).getWeight()) {
                    ((Buffalo) target).setWeight(((Buffalo) target).getWeight() - (getMaximumFood() - getSatiety()));
                    setSatiety(getMaximumFood());
                    ((Buffalo) target).deadBody = true;
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

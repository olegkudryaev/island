package animals;

//общий класс для всех животных, с общими для всех животными методами.

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal {
    protected double weight; // вес
    protected int speed; // скорость
    protected double maximumFood; // максимальное количество еды в желудке животного
    protected int surviveWithoutFoodLeft; // оставшиеся тики в данный момоент
    protected double satiety; // текущее состояние сытости
    protected double hungerPerDay; // голод за тик
    protected boolean gaveBirth; // готовность спариваться
    protected boolean moved; // готовность двигатсья
    protected int maxPopulation; // максимальное число животных в квадрате
    protected int surviveWithoutFood; // максимальное количество ходов выживания без еды.
    protected boolean deadBody; // съеден или нет.

    public abstract void eat(Island.Square squares);

    public void reproduction(Island.Square squares) {

        Class animalHusband = getClass();

        int countWife = 0;
        ArrayList<Object> copySquaresList = squares.list;

//        for (int i = 0; i < copySquaresList.size(); i++) { // подсчет животных перед размножением
//            Object animalWife = squares.list.get(i).getClass();
//            if (animalHusband == animalWife) {
//                countWife++;
//            }
//        }
//        if (countWife >= maxPopulation){
//            return;
//        }

        for (int i = 0; i < squares.list.size(); i++) {

//            for (int j = 0; j < copySquaresList.size(); j++) { // подсчет животных перед размножением
//                Object animalWife = squares.list.get(i).getClass();
//                Object animalWifeObject = squares.list.get(i);
//                if (animalHusband == animalWife && ((Animal) animalWifeObject).gaveBirth && gaveBirth && animalWifeObject != this) {
//                    countWife++;
//                }
//            }

            Object animalWife = squares.list.get(i).getClass();
            Object animalWifeObject = squares.list.get(i);
            if (animalHusband == animalWife && ((Animal) animalWifeObject).gaveBirth && gaveBirth && animalWifeObject != this) { // отказ от размножения

//                if (countWife >= ((Animal) animalWifeObject).getMaxPopulation()) {
//                    break;
//                }

                ((Animal) animalWifeObject).setGaveBirth(false);
                setGaveBirth(false);
                try {
                    squares.list.add(animalHusband.newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public void chooseDirectionOfTravel(Island.Square[][] squares, int i, int j) {
        if (!isMoved()) {
            return;
        }

        int chanceToMoveUpOrDown = ThreadLocalRandom.current().nextInt(-speed, speed + 1);
        int chanceToMoveLeftOrRight = ThreadLocalRandom.current().nextInt(-speed, speed + 1);

//
//        if (i + chanceToMoveUpOrDown < squares.length &&
//                j + chanceToMoveLeftOrRight < squares[i].length &&
//                i + chanceToMoveUpOrDown >= 0 &&
//                j + chanceToMoveLeftOrRight >= 0) {
//            int countAnimal = 0;
//            Object animal = this.getClass();
//            for (int z = 0; j < squares[i + chanceToMoveUpOrDown][j + chanceToMoveLeftOrRight].list.size(); z++) { // подсчет животных перед размножением
//
//            }
//        }
//


        if (i + chanceToMoveUpOrDown < squares.length &&
                j + chanceToMoveLeftOrRight < squares[i].length &&
                i + chanceToMoveUpOrDown >= 0 &&
                j + chanceToMoveLeftOrRight >= 0) {
            squares[i][j].list.remove(this);
            squares[i + chanceToMoveUpOrDown][j + chanceToMoveLeftOrRight].list.add(this);
            setMoved(false);
        }
    }

    public int getMaxPopulation() {
        return maxPopulation;
    }

    public void setMaxPopulation(int maxPopulation) {
        this.maxPopulation = maxPopulation;
    }

    public int getSurviveWithoutFood() {
        return surviveWithoutFood;
    }

    public void setSurviveWithoutFood(int surviveWithoutFood) {
        this.surviveWithoutFood = surviveWithoutFood;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getMaximumFood() {
        return maximumFood;
    }

    public void setMaximumFood(double maximumFood) {
        this.maximumFood = maximumFood;
    }

    public int getSurviveWithoutFoodLeft() {
        return surviveWithoutFoodLeft;
    }

    public void setSurviveWithoutFoodLeft(int surviveWithoutFoodLeft) {
        this.surviveWithoutFoodLeft = surviveWithoutFoodLeft;
    }

    public double getSatiety() {
        return satiety;
    }

    public void setSatiety(double satiety) {
        this.satiety = satiety;
    }

    public double getHungerPerDay() {
        return hungerPerDay;
    }

    public void setHungerPerDay(double hungerPerDay) {
        this.hungerPerDay = hungerPerDay;
    }

    public boolean isGaveBirth() {
        return gaveBirth;
    }

    public void setGaveBirth(boolean gaveBirth) {
        this.gaveBirth = gaveBirth;
    }

}

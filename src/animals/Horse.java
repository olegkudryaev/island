package animals;

public class Horse extends Herbivorous implements Runnable{

    public Horse(){
        gaveBirth = false;
        moved = true;
        deadBody = false;
        surviveWithoutFoodLeft = surviveWithoutFood = 5;
        weight = 400;
        speed = 4;
        maximumFood = 60;
        satiety = maximumFood / 3;
        hungerPerDay = maximumFood * 0.1;
        maxPopulation = 20;
    }

    public int getSurviveWithoutFood() {
        return surviveWithoutFood;
    }

    @Override
    public void run() {

    }
}
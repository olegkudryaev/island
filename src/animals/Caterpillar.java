package animals;

public class Caterpillar extends Herbivorous implements Runnable{

    public Caterpillar(){
        gaveBirth = false;
        moved = true;
        deadBody = false;
        surviveWithoutFoodLeft = surviveWithoutFood = 1;
        weight = 0.01;
        speed = 0;
        maximumFood = 0;
        satiety = maximumFood / 3;
        hungerPerDay = maximumFood * 0.1;
        maxPopulation = 300;
    }

    public int getSurviveWithoutFood() {
        return surviveWithoutFood;
    }

    @Override
    public void run() {

    }
}
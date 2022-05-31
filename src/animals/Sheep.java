package animals;

public class Sheep extends Herbivorous implements Runnable{

    public Sheep(){
        gaveBirth = false;
        moved = true;
        deadBody = false;
        surviveWithoutFoodLeft = surviveWithoutFood = 5;
        weight = 70;
        speed = 3;
        maximumFood = 15;
        satiety = maximumFood / 3;
        hungerPerDay = maximumFood * 0.1;
        maxPopulation = 140;
    }

    public int getSurviveWithoutFood() {
        return surviveWithoutFood;
    }

    @Override
    public void run() {

    }
}
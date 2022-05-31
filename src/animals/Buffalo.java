package animals;

public class Buffalo extends Herbivorous implements Runnable{

    public Buffalo(){
        gaveBirth = false;
        moved = true;
        deadBody = false;
        surviveWithoutFoodLeft = surviveWithoutFood = 4;
        weight = 700;
        speed = 3;
        maximumFood = 100;
        satiety = maximumFood / 3;
        hungerPerDay = maximumFood * 0.1;
        maxPopulation = 10;
    }

    public int getSurviveWithoutFood() {
        return surviveWithoutFood;
    }



    @Override
    public void run() {

    }
}
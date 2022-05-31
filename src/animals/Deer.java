package animals;

public class Deer extends Herbivorous implements Runnable{

    public Deer(){
        gaveBirth = false;
        moved = true;
        deadBody = false;
        surviveWithoutFoodLeft = surviveWithoutFood = 4;
        weight = 300;
        speed = 4;
        maximumFood = 50;
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

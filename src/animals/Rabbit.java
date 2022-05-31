package animals;

public class Rabbit extends Herbivorous implements Runnable{

    public Rabbit(){
        gaveBirth = false;
        moved = true;
        deadBody = false;
        surviveWithoutFoodLeft = surviveWithoutFood = 4;
        weight = 2;
        speed = 2;
        maximumFood = 0.45;
        satiety = maximumFood / 3;
        hungerPerDay = maximumFood * 0.1;
        maxPopulation = 150;
    }

    public int getSurviveWithoutFood() {
        return surviveWithoutFood;
    }

    @Override
    public void run() {

    }
}
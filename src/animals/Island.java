package animals;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class Island {
    private Square squares[][];

    public Island() {
        squares = new Island.Square[2][2];
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                squares[i][j] = new Square();
            }
        }
    }


    public class Square {
        ArrayList<Object> list = new ArrayList<>();
    }

    //количество заселяемых животных
    public static int rnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    //заселение козами, волками и травой
    public void SettlementIslands() {

        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                for (int k = 0; k < 200; k++) {
                    squares[i][j].list.add(new Goat());
                }
                for (int k = 0; k < 200; k++) {
                    squares[i][j].list.add(new Wolf());
                }
            }
        }
    }

    public void grassGrowth() {
        int count = 0;
        int number = 0;
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                for (int s = 0; s < squares[i][j].list.size(); s++) {
                    Object plant = squares[i][j].list.get(s);
                    if (plant instanceof Plant) {
                        count++;
                    }
                }
                number = Plant.getMaxPopulation() - count;
                for (int k = 0; k < number; k++) {
                    squares[i][j].list.add(new Plant());
                }
            }
        }
    }

    public void reproductionReady(Island.Square squares) {
        for (int i = 0; i < squares.list.size(); i++) {
            if (!(squares.list.get(i) instanceof Animal)) {
                continue;
            }
            Animal animal = (Animal) squares.list.get(i);
            if (animal.satiety == animal.getMaximumFood() / 2) {
                animal.setGaveBirth(true);
            }
        }
    }

    public void moveToReady(Island.Square squares) {
        for (int i = 0; i < squares.list.size(); i++) {
            Object animal = squares.list.get(i);
            if (animal instanceof Animal) {
                ((Animal) animal).setMoved(true);
            }
        }
    }

    public void cleaning(Island.Square squares) {
        ArrayList<Object> copySquaresList = squares.list;
        for (int i = 0; i < copySquaresList.size(); i++) {
            Object target = copySquaresList.get(i);
            if (target instanceof Plant) {
                if (((Plant) target).getWeight() < 1) {
                    squares.list.remove(i);
                    i--;
                }
            } else if (target instanceof Animal) {
                if (((Animal) target).getSurviveWithoutFoodLeft() <= 0) {
                    squares.list.remove(i);
                    i--;
                } else if (((Animal) target).deadBody) {
                    squares.list.remove(i);
                    i--;
                }
            }
        }
    }

    public void animalCount(Island.Square[][] squares) {
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                String wolf = "\uD83D\uDC3A";
                String goat = "\uD83D\uDC10";
                int countWolf = 0;
                int countGoat = 0;
                for (int k = 0; k < squares[i][j].list.size(); k++) {
                    Object objects = squares[i][j].list.get(k);
                    if (objects instanceof Animal) {
                        if (objects instanceof Wolf) {
                            countWolf++;
                            if (countWolf > ((Wolf) objects).getMaxPopulation()) {
                                countWolf--;
                                squares[i][j].list.remove(k);
                                k--;
                            }
                        }
                        if (objects instanceof Goat) {
                            countGoat++;
                            if (countGoat > ((Goat) objects).getMaxPopulation()) {
                                countGoat--;
                                squares[i][j].list.remove(k);
                                k--;
                            }
                        }
                    }

                }
                HashMap<String, Integer> stats = new HashMap<>();
                stats.put(wolf, countWolf);
                stats.put(goat, countGoat);
                Object[] objects = stats.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).toArray();
                System.out.println(objects[0]);
                System.out.println();
            }
        }
    }

    public void start() {
        animalCount(squares);
        for (int i = 0; i < squares.length; i++) {

            for (int j = 0; j < squares[i].length; j++) {
                ArrayList<Object> copyAnimals = new ArrayList<>();
                reproductionReady(squares[i][j]);
                moveToReady(squares[i][j]);
                for (int k = 0; k < squares[i][j].list.size(); k++) {
                    Object animal = squares[i][j].list.get(k);
                    if (animal instanceof Animal && !((Animal) animal).deadBody) {
                        ((Animal) animal).eat(squares[i][j]);
                        System.out.println(); // точка для тестов
                    }
                }
                cleaning(squares[i][j]);

            }
            for (int j = 0; j < squares[i].length; j++) {
                reproductionReady(squares[i][j]);
                moveToReady(squares[i][j]);
                for (int k = 0; k < squares[i][j].list.size(); k++) {
                    Object animal = squares[i][j].list.get(k);
                    if (animal instanceof Animal && !((Animal) animal).deadBody) {
                        ((Animal) animal).reproduction(squares[i][j]);
                    }
                }
                cleaning(squares[i][j]);
            }
            for (int j = 0; j < squares[i].length; j++) {
                ArrayList<Object> copyAnimals = new ArrayList<>();
                reproductionReady(squares[i][j]);
                moveToReady(squares[i][j]);
                for (int k = 0; k < squares[i][j].list.size(); k++) {
                    Object animal = squares[i][j].list.get(k);
                    if (animal instanceof Animal && !((Animal) animal).deadBody) {
                        ((Animal) animal).chooseDirectionOfTravel(squares, i, j);

                    }
                }
                cleaning(squares[i][j]);
            }
        }
    }


    public static void main(String[] args) {

        Island island = new Island();
        island.SettlementIslands();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                island.grassGrowth();
                island.animalCount(island.squares);

            }
        }, 0, 2, TimeUnit.SECONDS);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < island.squares.length; i++) {
            for (int j = 0; j < island.squares[i].length; j++) {
                service.submit(new Runnable() {
                    @Override
                    public void run() {
                        island.start();
                    }
                });
            }
        }
        System.out.println();

//        while (true) {
//        island.start();
//        System.out.println();
//        }

    }


}

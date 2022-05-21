package animals;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Island {
    private Square squares[][];

    public Island() {
        squares = new Island.Square[10][10];
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
//                for (int k = 0; k < 10; k++) {
//                    squares[i][j].list.add(new Plant());
//                }
                for (int k = 0; k < 10; k++) {
                    squares[i][j].list.add(new Goat());
                }
                for (int k = 0; k < 10; k++) {
                    squares[i][j].list.add(new Wolf());
                }

            }
        }
    }

    public void reproductionReady(Island.Square squares) {
        for (int i = 0; i < squares.list.size(); i++) {
            Object animal = squares.list.get(i);
            if (animal instanceof Animal) {
                ((Animal) animal).setGaveBirth(true);
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
                }
                else if (((Animal) target).deadBody){
                    squares.list.remove(i);
                    i--;
                }
            }
        }
    }

    public void start() {
//        ThreadLocalRandom.current().nextDouble();
        for (int i = 0; i < squares.length; i++) {

            for (int j = 0; j < squares[i].length; j++) {
                ArrayList<Object> copyAnimals = new ArrayList<>();
                reproductionReady(squares[i][j]);
                moveToReady(squares[i][j]);
                for (int k = 0; k < squares[i][j].list.size(); k++) {
                    Object animal = squares[i][j].list.get(k);
                    if (animal instanceof Animal && !((Animal) animal).deadBody) {

//                        ((Animal) animal).eat(squares[i][j]);

//                        ((Animal) animal).reproduction(squares[i][j]);

                        ((Animal) animal).chooseDirectionOfTravel(squares, i, j);

                        System.out.println(); // точка для тестов

                    }
                    if (animal instanceof Wolf) {
//                        ((Wolf) animal).eat(squares[i][j]);
//                        ((Wolf) animal).reproduction(squares[i][j]);
                    }

                }
                cleaning(squares[i][j]);

                System.out.println(); // точка для тестов

            }
        }
    }


    public static void main(String[] args) {

        Island island = new Island();
        island.SettlementIslands();
//        ExecutorService service = Executors.newCachedThreadPool();
        island.start();
        System.out.println();


    }
}

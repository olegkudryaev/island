package animals;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Island {
    private Square squares[][];

    //создание острова
    public Island() {
        squares = new Island.Square[50][20];
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                squares[i][j] = new Square();
            }
        }
    }

    public class Square {
        ArrayList<Object> list = new ArrayList<>();
    }

    //заселение
    public void SettlementIslands() {
        Wolf wolf = new Wolf();
        Anaconda anaconda = new Anaconda();
        Fox fox = new Fox();
        Bear bear = new Bear();
        Eagle eagle = new Eagle();
        Horse horse = new Horse();
        Deer deer = new Deer();
        Rabbit rabbit = new Rabbit();
        Mouse mouse = new Mouse();
        Goat goat = new Goat();
        Sheep sheep = new Sheep();
        Boar boar = new Boar();
        Buffalo buffalo = new Buffalo();
        Duck duck = new Duck();
        Caterpillar caterpillar = new Caterpillar();

        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {

                int numberWolf = ThreadLocalRandom.current().nextInt(0, wolf.maxPopulation);
                int numberAnaconda = ThreadLocalRandom.current().nextInt(0, anaconda.maxPopulation);
                int numberFox = ThreadLocalRandom.current().nextInt(0, fox.maxPopulation);
                int numberBear = ThreadLocalRandom.current().nextInt(0, bear.maxPopulation);
                int numberEagle = ThreadLocalRandom.current().nextInt(0, eagle.maxPopulation);
                int numberHorse = ThreadLocalRandom.current().nextInt(0, horse.maxPopulation);
                int numberDeer = ThreadLocalRandom.current().nextInt(0, deer.maxPopulation);
                int numberRabbit = ThreadLocalRandom.current().nextInt(0, rabbit.maxPopulation);
                int numberMouse = ThreadLocalRandom.current().nextInt(0, mouse.maxPopulation);
                int numberGoat = ThreadLocalRandom.current().nextInt(0, goat.maxPopulation);
                int numberSheep = ThreadLocalRandom.current().nextInt(0, sheep.maxPopulation);
                int numberBoar = ThreadLocalRandom.current().nextInt(0, boar.maxPopulation);
                int numberBuffalo = ThreadLocalRandom.current().nextInt(0, buffalo.maxPopulation);
                int numberDuck = ThreadLocalRandom.current().nextInt(0, duck.maxPopulation);
                int numberCaterpillar = ThreadLocalRandom.current().nextInt(0, caterpillar.maxPopulation);

                for (int k = 0; k < numberWolf; k++) {
                    squares[i][j].list.add(new Wolf());
                }
                for (int k = 0; k < numberAnaconda; k++) {
                    squares[i][j].list.add(new Anaconda());
                }
                for (int k = 0; k < numberFox; k++) {
                    squares[i][j].list.add(new Fox());
                }
                for (int k = 0; k < numberBear; k++) {
                    squares[i][j].list.add(new Bear());
                }
                for (int k = 0; k < numberEagle; k++) {
                    squares[i][j].list.add(new Eagle());
                }
                for (int k = 0; k < numberHorse; k++) {
                    squares[i][j].list.add(new Horse());
                }
                for (int k = 0; k < numberDeer; k++) {
                    squares[i][j].list.add(new Deer());
                }
                for (int k = 0; k < numberRabbit; k++) {
                    squares[i][j].list.add(new Rabbit());
                }
                for (int k = 0; k < numberMouse; k++) {
                    squares[i][j].list.add(new Mouse());
                }
                for (int k = 0; k < numberGoat; k++) {
                    squares[i][j].list.add(new Goat());
                }
                for (int k = 0; k < numberSheep; k++) {
                    squares[i][j].list.add(new Sheep());
                }
                for (int k = 0; k < numberBoar; k++) {
                    squares[i][j].list.add(new Boar());
                }
                for (int k = 0; k < numberBuffalo; k++) {
                    squares[i][j].list.add(new Buffalo());
                }
                for (int k = 0; k < numberDuck; k++) {
                    squares[i][j].list.add(new Duck());
                }
                for (int k = 0; k < numberCaterpillar; k++) {
                    squares[i][j].list.add(new Caterpillar());
                }
            }
        }
    }

    //рост травы
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

    // готовность к размножению
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

    //готовность к движению
    public void moveToReady(Island.Square squares) {
        for (int i = 0; i < squares.list.size(); i++) {
            Object animal = squares.list.get(i);
            if (animal instanceof Animal) {
                ((Animal) animal).setMoved(true);
            }
        }
    }

    //удаление надкусанных животных
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

    //подсчет и удаление лишних животных
    public void animalCount() {
        System.out.println();
        for (int i = 0; i < squares.length; i++) {
            System.out.println();
            for (int j = 0; j < squares[i].length; j++) {
                String wolf = "\uD83D\uDC3A";
                String goat = "\uD83D\uDC10";
                String anaconda = "\uD83E\uDD8A";
                String fox = "\uD83E\uDD8A";
                String bear = "\uD83D\uDC3B";
                String eagle = "\uD83E\uDD85";
                String horse = "\uD83D\uDC0E";
                String deer = "\uD83E\uDD8C";
                String rabbit = "\uD83D\uDC07";
                String mouse = "\uD83D\uDC39"; // символ хомяка
                String boar = "\uD83E\uDD98"; // символ кенгуру
                String buffalo = "\uD83D\uDC2E"; //символ коровы
                String duck = "\uD83E\uDD86";
                String caterpillar = "\uD83D\uDC1B";
                String sheep = "\uD83D\uDC11";

                int countWolf = 0;
                int countGoat = 0;
                int countAnaconda = 0;
                int countFox = 0;
                int countBear = 0;
                int countEagle = 0;
                int countHorse = 0;
                int countDeer = 0;
                int countRabbit = 0;
                int countMouse = 0;
                int countBoar = 0;
                int countBuffalo = 0;
                int countDuck = 0;
                int countCaterpillar = 0;
                int countSheep = 0;
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
                        if (objects instanceof Anaconda) {
                            countAnaconda++;
                            if (countAnaconda > ((Anaconda) objects).getMaxPopulation()) {
                                countAnaconda--;
                                squares[i][j].list.remove(k);
                                k--;
                            }
                        }
                        if (objects instanceof Fox) {
                            countFox++;
                            if (countFox > ((Fox) objects).getMaxPopulation()) {
                                countFox--;
                                squares[i][j].list.remove(k);
                                k--;
                            }
                        }
                        if (objects instanceof Bear) {
                            countBear++;
                            if (countBear > ((Bear) objects).getMaxPopulation()) {
                                countBear--;
                                squares[i][j].list.remove(k);
                                k--;
                            }
                        }
                        if (objects instanceof Eagle) {
                            countEagle++;
                            if (countEagle > ((Eagle) objects).getMaxPopulation()) {
                                countEagle--;
                                squares[i][j].list.remove(k);
                                k--;
                            }
                        }
                        if (objects instanceof Horse) {
                            countHorse++;
                            if (countHorse > ((Horse) objects).getMaxPopulation()) {
                                countHorse--;
                                squares[i][j].list.remove(k);
                                k--;
                            }
                        }
                        if (objects instanceof Deer) {
                            countDeer++;
                            if (countDeer > ((Deer) objects).getMaxPopulation()) {
                                countDeer--;
                                squares[i][j].list.remove(k);
                                k--;
                            }
                        }
                        if (objects instanceof Rabbit) {
                            countRabbit++;
                            if (countRabbit > ((Rabbit) objects).getMaxPopulation()) {
                                countRabbit--;
                                squares[i][j].list.remove(k);
                                k--;
                            }
                        }
                        if (objects instanceof Mouse) {
                            countMouse++;
                            if (countMouse > ((Mouse) objects).getMaxPopulation()) {
                                countMouse--;
                                squares[i][j].list.remove(k);
                                k--;
                            }
                        }
                        if (objects instanceof Boar) {
                            countBoar++;
                            if (countBoar > ((Boar) objects).getMaxPopulation()) {
                                countBoar--;
                                squares[i][j].list.remove(k);
                                k--;
                            }
                        }
                        if (objects instanceof Buffalo) {
                            countBuffalo++;
                            if (countBuffalo > ((Buffalo) objects).getMaxPopulation()) {
                                countBuffalo--;
                                squares[i][j].list.remove(k);
                                k--;
                            }
                        }
                        if (objects instanceof Duck) {
                            countDuck++;
                            if (countDuck > ((Duck) objects).getMaxPopulation()) {
                                countDuck--;
                                squares[i][j].list.remove(k);
                                k--;
                            }
                        }
                        if (objects instanceof Caterpillar) {
                            countCaterpillar++;
                            if (countCaterpillar > ((Caterpillar) objects).getMaxPopulation()) {
                                countCaterpillar--;
                                squares[i][j].list.remove(k);
                                k--;
                            }
                        }
                        if (objects instanceof Sheep) {
                            countSheep++;
                            if (countSheep > ((Sheep) objects).getMaxPopulation()) {
                                countSheep--;
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
                stats.put(anaconda, countAnaconda);
                stats.put(fox, countFox);
                stats.put(bear, countBear);
                stats.put(eagle, countEagle);
                stats.put(horse, countHorse);
                stats.put(deer, countDeer);
                stats.put(rabbit, countRabbit);
                stats.put(mouse, countMouse);
                stats.put(boar, countBoar);
                stats.put(buffalo, countBuffalo);
                stats.put(duck, countDuck);
                stats.put(caterpillar, countCaterpillar);
                stats.put(sheep, countSheep);

                Object[] objects = stats.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).toArray();
                System.out.print(objects[0] + " ");
            }
        }
    }

    //стартовый метод
    public void start(Island.Square squares, int i, int j) {
//        if (true) {
//            throw new RuntimeException();
//        }
        reproductionReady(squares);
        moveToReady(squares);
        for (int k = 0; k < squares.list.size(); k++) {
            Object animal = squares.list.get(k);
            if (animal instanceof Animal && !((Animal) animal).deadBody) {
                ((Animal) animal).eat(squares);
            }
        }
        cleaning(squares);
//        reproductionReady(squares); нужны ли эти методы?
//        moveToReady(squares); нужны ли эти методы?
        for (int k = 0; k < squares.list.size(); k++) {
            Object animal = squares.list.get(k);
            if (animal instanceof Animal && !((Animal) animal).deadBody) {
                ((Animal) animal).reproduction(squares);
            }
        }
        cleaning(squares);
//        reproductionReady(squares); нужны ли эти методы?
//        moveToReady(squares); нужны ли эти методы?
        for (int k = 0; k < squares.list.size(); k++) {
            Object animal = squares.list.get(k);
            if (animal instanceof Animal && !((Animal) animal).deadBody) {
                ((Animal) animal).chooseDirectionOfTravel(this.squares, i, j);
            }
        }
        cleaning(squares);
    }

    public static void main(String[] args) {
        Island island = new Island();
        island.SettlementIslands();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);


//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Сколько циклов прокрутим?");
//        final int numberOfCycles = scanner.nextInt();
//        int a = 0;
//        while (a <= 0){
//            island.grassGrowth();
//            for (int i = 0; i < island.squares.length; i++) {
//                for (int j = 0; j < island.squares[i].length; j++) {
//                    int finalI = i;
//                    int finalJ = j;
//                    island.start(island.squares[finalI][finalJ], finalI, finalJ);
//                }
//            }
//            island.animalCount();
//            a--;
//        }
//        System.out.println();
        futureList = new ArrayList<Object>();
        ExecutorService service = Executors.newCachedThreadPool();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
//                if (finishThreadCount.get() == 100) {
//                    finishThreadCount.set(0);
//                } else {
//                    return;
//                }
                island.grassGrowth();
                island.animalCount();
                for (int i = 0; i < island.squares.length; i++) {
                    for (int j = 0; j < island.squares[i].length; j++) {
                        int finalI = i;
                        int finalJ = j;
                        submited++;
//                        Object obj =
                                service.submit(new Runnable() {
                                    @Override
                                    public void run() {
                                        threadStarted++;
//                                        try {
                                            island.start(island.squares[finalI][finalJ], finalI, finalJ);
//                                        }catch (Exception e) {
//                                            System.out.println(e);
//                                        }
                                        finishThreadCount.incrementAndGet();
                                    }
                                });
//                        futureList.add(obj);
                    }
                }
//                executorService.shutdown();
            }
        }, 0, 10, TimeUnit.SECONDS);
    }

//    static int finishThreadCount = 2000;
    static AtomicInteger finishThreadCount = new AtomicInteger(100);
    static int threadStarted = 0;
    static int submited = 0;
    static ArrayList futureList;
}

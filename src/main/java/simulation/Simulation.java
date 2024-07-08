package main.java.simulation;

import main.java.simulation.actions.GrassGrower;
import main.java.simulation.actions.MoveMaker;
import main.java.simulation.actions.PlaceTree;
import main.java.simulation.board.Coordinates;
import main.java.simulation.board.MapConsoleRender;
import main.java.simulation.actions.Action;

import java.util.*;


public class Simulation implements Runnable {

    private static boolean pause = false;
    private static boolean simulationAtWork = true;
    private static int round = 1;
    private static WorldMap worldMap;
    private Action action;
    private MapConsoleRender mapConsoleRender;
    private static List<Action> initActions;
    private static List<Action> turnAction;


    public static void initialConfiguration() {
        worldMap = WorldMap.get();
        createActions();
        for (Action action : initActions) {
            action.perform(worldMap);
        }
    }

    private static void createActions() {
        initActions = new ArrayList<>();
        turnAction = new ArrayList<>();

        initActions.add(new PlaceTree());

        turnAction.add(new MoveMaker());
        turnAction.add(new GrassGrower());
    }

    private static void checkPause() {
        try {
            Thread.sleep(2000);
            while (pause) {
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void startSimulation() {
        while (simulationAtWork) {
            ConsolePrinter.displayRoundCount(round);
            ConsolePrinter.displayWorld(worldMap);
            checkPause();
            nextTurn(worldMap);
            round++;
        }
    }

    private static void nextTurn(WorldMap worldMap) {
        for (Action action : turnAction) {
            action.perform(worldMap);
        }
    }

    public static void pauseSimulation() {

    }

//    public static HashMap<Coordinates, Entity> nextTurn(HashMap<Coordinates, Entity> map) {
//        HashMap<Coordinates, Entity> newMap = new HashMap<>();
//        for (Map.Entry<Coordinates, Entity> entry : map.entrySet()) {
//            Coordinates coordinates = entry.getValue().doTheNextAction(map, newMap, entry.getKey());
//            if (coordinates != null) newMap.put(coordinates, entry.getValue());
//        }
//        for (int column = 0; column < GenerateField.getColumn(); column++) {
//            //Генерация новой травы
//            for (int row = 0; row < GenerateField.getRow(); row++) {
//                //Генерация новой травы
//                if (!newMap.containsKey(new Coordinates(column, row))) {
//                    if (Math.random() < 0.01) {
//                        newMap.put(new Coordinates(column, row), new Grass(1));
//                    }
//                }
//                //Удаление съеденной травы
//                if (newMap.get((new Coordinates(column, row))) instanceof Grass) {
//                    if (((Grass) newMap.get((new Coordinates(column, row)))).getGrowthProgress() < 1) {
//                        newMap.remove(new Coordinates(column, row));
//                    }
//                }
//                //Ждем новых существ на крайние непустые клетки поля
//                if ((column == 0 || column == GenerateField.getColumn() || row == 0 || row == GenerateField.getRow()) &&
//                        !newMap.containsKey(new Coordinates(column, row))) {
//                    if (Math.random() < 0.01) {
//                        newMap.put(new Coordinates(column, row), new Predator(7));
//                    } else if (Math.random() < 0.01) {
//                        newMap.put(new Coordinates(column, row), new Herbivore(7));
//                    }
//                }
//            }
//        }
//        return newMap;
//    }

//    public static Coordinates rndCoordinates(HashSet<Coordinates> setCoordinates) {
//        int randomCoordinate = new Random().nextInt(setCoordinates.size());
//        int nextStep = 0;
//        for (Coordinates coordinate : setCoordinates) {
//            if (nextStep == randomCoordinate) {
//                return coordinate;
//            }
//            nextStep++;
//        }
//        return null;
//    }

    @Override
    public void run() {

        initialConfiguration();

        startSimulation();

    }

    public static boolean isPause() {
        return pause;
    }

    public static void setPause(boolean pause) {
        Simulation.pause = pause;
    }

    public static void stopSimulation() {
        Simulation.simulationAtWork = false;
        Simulation.pause = false;
    }
}

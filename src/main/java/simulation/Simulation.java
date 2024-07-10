package main.java.simulation;

import main.java.simulation.actions.*;
import main.java.simulation.world.WorldMap;

import java.util.*;


public class Simulation implements Runnable {

    private static boolean pause = false;
    private static boolean simulationAtWork = true;
    private static int round = 1;
    private static WorldMap worldMap;
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

        initActions.add(new PopulateTree());
        initActions.add(new PopulateRock());
        initActions.add(new PopulateGrass());
        initActions.add(new PopulateCreatures());

        turnAction.add(new PrepareCreaturesToMove());
        turnAction.add(new MoveMaker());
        turnAction.add(new GrassGrower());
        turnAction.add(new MapCleaner());
        turnAction.add(new MigrateCreators());

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

    public static void pauseSimulation(boolean pause) {
        Simulation.pause = pause;
    }

    @Override
    public void run() {

        initialConfiguration();
        startSimulation();
    }

    public static boolean isPause() {
        return pause;
    }

    public static void stopSimulation() {
        Simulation.simulationAtWork = false;
        Simulation.pause = false;
    }
}

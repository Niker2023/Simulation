package main.java.simulation;

import main.java.simulation.world.WorldMap;

public class Main {
    public static void main(String[] args) {
        Thread simulation = new Thread(new Simulation());
        Thread consoleInterface = new Thread(new ConsoleInterface());
        consoleInterface.start();

        while (!WorldMap.isReadyToPlacement()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        simulation.start();
    }
}
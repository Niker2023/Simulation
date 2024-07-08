package main.java.simulation;

public class Main {
    public static void main(String[] args) {
        Thread simulation = new Thread(new Simulation());
        Thread workWithConsole = new Thread(new ConsoleInterface());
        workWithConsole.start();

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
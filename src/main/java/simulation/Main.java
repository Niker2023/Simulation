package main.java.simulation;

import main.java.simulation.board.GenerateField;

public class Main {
    public static void main(String[] args) {
        Thread simulation = new Thread(new Simulation());
        Thread workWithConsole = new Thread(new WorkWithConsole());
        workWithConsole.start();

        while (!GenerateField.isWorldIsGenerated()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        simulation.start();
    }
}
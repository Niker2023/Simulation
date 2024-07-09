package main.java.simulation;

import main.java.simulation.board.Coordinates;
import main.java.simulation.board.MapConsoleRender;
import main.java.simulation.entities.Entity;
import main.java.simulation.entities.Grass;
import main.java.simulation.entities.Herbivore;
import main.java.simulation.entities.Predator;

public class ConsolePrinter {

    private static final String RED_COLOR = "\u001B[31m";
    private static final String RESET_COLOR = "\u001B[0m";
    public static final String GREEN_COLOR = "\u001B[32m";
    public static final String YELLOW_COLOR = "\u001B[33m";
    public static final String BLUE_COLOR = "\u001B[34m";
    public static final String PURPLE_COLOR = "\u001B[35m";
    public static final String CYAN_COLOR = "\u001B[36m";

    public static void displayRoundCount(int round) {
        System.out.println();
        System.out.println("День " + round + ".");
    }

    public static void displayWorld(WorldMap worldMap) {
        System.out.print(MapConsoleRender.BOTTOM_BORDER);
        for (int i = 0; i < worldMap.getColumn(); i++) {
            System.out.print(MapConsoleRender.BOTTOM_BORDER + MapConsoleRender.BOTTOM_BORDER);
        }
        System.out.println();
        for (int row = 0; row < worldMap.getColumn(); row++) {
            for (int column = 0; column < worldMap.getRow(); column++) {
                if (column == 0) {
                    System.out.print(MapConsoleRender.HORIZONTAL_BORDER);
                }
                if (worldMap.isContainsEntity(new Coordinates(column, row))) {
                    System.out.print(entitiesRender(worldMap.getEntity((new Coordinates(column, row)))));
                } else {
                    System.out.print(MapConsoleRender.EMPTY_AREA);
                }
                if (column == worldMap.getColumn() - 1) {
                    System.out.print(MapConsoleRender.HORIZONTAL_BORDER);
                    System.out.println();
                }
            }
        }
        System.out.print(MapConsoleRender.UPPER_BORDER);
        for (int i = 0; i < worldMap.getColumn(); i++) {
            System.out.print(MapConsoleRender.UPPER_BORDER + MapConsoleRender.UPPER_BORDER);
        }
    }

    private static String entitiesRender(Entity entity) {
        switch (entity.getClass().getSimpleName()) {
            case "Grass":
                if (((Grass) entity).getGrowthProgress() < 3) {
                    return GREEN_COLOR + MapConsoleRender.GROWING_GRASS_RENDER + RESET_COLOR;
                } else {
                    return GREEN_COLOR + MapConsoleRender.GROWN_GRASS_RENDER + RESET_COLOR;
                }
            case "Rock":
                return YELLOW_COLOR + MapConsoleRender.ROCK_RENDER + RESET_COLOR;
            case "Tree":
                return GREEN_COLOR + MapConsoleRender.TREE_RENDER + RESET_COLOR;
            case "Herbivore":
                return CYAN_COLOR + MapConsoleRender.HERBIVORE_RENDER + ((Herbivore) entity).getHp() + RESET_COLOR;
            case "Predator":
                return PURPLE_COLOR + MapConsoleRender.PREDATOR_RENDER + ((Predator) entity).getHp() + RESET_COLOR;
            default:
                throw new IllegalStateException("Unexpected value: " + entity.getClass().getSimpleName());
        }
    }
}

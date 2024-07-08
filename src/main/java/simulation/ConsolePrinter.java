package main.java.simulation;

import main.java.simulation.board.Coordinates;
import main.java.simulation.board.MapConsoleRender;
import main.java.simulation.entities.Entity;
import main.java.simulation.entities.Grass;
import main.java.simulation.entities.Herbivore;
import main.java.simulation.entities.Predator;

public class ConsolePrinter {

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
                if (worldMap.isContainsCoordinates(new Coordinates(column, row))) {
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
                    return MapConsoleRender.GROWING_GRASS_RENDER;
                } else {
                    return MapConsoleRender.GROWN_GRASS_RENDER;
                }
            case "Rock":
                return MapConsoleRender.ROCK_RENDER;
            case "Tree":
                return MapConsoleRender.TREE_RENDER;
            case "Herbivore":
                return MapConsoleRender.HERBIVORE_RENDER + ((Herbivore) entity).getHp();
            case "Predator":
                return MapConsoleRender.PREDATOR_RENDER + ((Predator) entity).getHp();
            default:
                throw new IllegalStateException("Unexpected value: " + entity.getClass().getSimpleName());
        }
    }
}

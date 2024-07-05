package main.java.simulation;

import main.java.simulation.board.Coordinates;
import main.java.simulation.entities.Entity;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {

    private Map<Coordinates, Entity> worldMap = new HashMap<>();
    private static int row;
    private static int column;


    public Map<Coordinates, Entity> get() {
        return worldMap;
    }

    public void placeEntity(Coordinates coordinates, Entity entity) {
        worldMap.put(coordinates, entity);
    }

    public void remoteEntity(Coordinates coordinates) {
        worldMap.remove(coordinates);
    }

    public static int getRow() {
        return row;
    }

    public static void setRow(int row) {
        WorldMap.row = row;
    }

    public static int getColumn() {
        return column;
    }

    public static void setColumn(int column) {
        WorldMap.column = column;
    }
}
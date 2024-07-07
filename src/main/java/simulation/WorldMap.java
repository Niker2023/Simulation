package main.java.simulation;

import main.java.simulation.board.Coordinates;
import main.java.simulation.board.GenerateField;
import main.java.simulation.entities.*;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {

    private static Map<Coordinates, Entity> worldMap;
    private static int rows = 0;
    private static int columns = 0;
    private static boolean isReadyToPlacement = false;

    public static void setRow(int numberRows) {
        rows = numberRows;
        if (columns != 0) {
            isReadyToPlacement = true;
        }
    }

    public static void setColumn(int numberColumns) {
        columns = numberColumns;
        if (rows != 0) {
            isReadyToPlacement = true;
        }
    }

    public boolean isReadyToPlacement() {
        return isReadyToPlacement;
    }

    public WorldMap get() {
        if (worldMap == null) {
            worldMap = new HashMap<>();
        }
        return (WorldMap) worldMap;
    }

    public void placeEntity(Coordinates coordinates, Entity entity) {
        worldMap.put(coordinates, entity);
    }

    public void remoteEntity(Coordinates coordinates) {
        worldMap.remove(coordinates);
    }

    public Entity getEntity(Coordinates coordinates) {
        return worldMap.get(coordinates);
    }

    public void moveCreates(Coordinates from, Coordinates to) {
        Entity entity = worldMap.get(from);
        worldMap.put(to, entity);
    }

    public boolean isEmptyCoordinates(Coordinates coordinates) {
        return worldMap.containsKey(coordinates);
    }

    public int getRow() {
        return rows;
    }

    public int getColumn() {
        return columns;
    }

}

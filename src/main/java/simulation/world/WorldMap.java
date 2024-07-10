package main.java.simulation.world;

import main.java.simulation.entities.*;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {

    private static Map<Coordinates, Entity> worldMap;
    private static int rows = 0;
    private static int columns = 0;
    private static boolean isReadyToPlacement = false;

    private static WorldMap instance;

    private WorldMap() {
        worldMap = new HashMap<>();
    }

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

    public static boolean isReadyToPlacement() {
        return isReadyToPlacement;
    }

    public static WorldMap get() {
        if (instance == null) {
            instance = new WorldMap();
        }
        return instance;
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
        worldMap.remove(from);
    }

    public boolean isContainsEntity(Coordinates coordinates) {
        return worldMap.containsKey(coordinates);
    }

    public int getRow() {
        return rows;
    }

    public int getColumn() {
        return columns;
    }
}

package main.java.simulation.actions;

import main.java.simulation.world.WorldMap;
import main.java.simulation.world.Coordinates;
import main.java.simulation.entities.Grass;

public class GrassGrower extends Action{

    @Override
    public void perform(WorldMap worldMap) {
        for (int column = 0; column < worldMap.getColumn(); column++) {
            for (int row = 0; row < worldMap.getRow(); row++) {
                Coordinates currentCoordinates = new Coordinates(column, row);
                if (worldMap.isContainsEntity(currentCoordinates)) {
                    if ((worldMap.getEntity(currentCoordinates)).getClass().getSimpleName().equals("Grass")) {
                        ((Grass) worldMap.getEntity(currentCoordinates)).growUp();
                    }
                } else if (Math.random() < 0.02) {
                    worldMap.placeEntity(currentCoordinates, new Grass(true));
                }
            }
        }
    }
}

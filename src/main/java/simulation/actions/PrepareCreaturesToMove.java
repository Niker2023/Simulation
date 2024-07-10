package main.java.simulation.actions;

import main.java.simulation.world.WorldMap;
import main.java.simulation.world.Coordinates;
import main.java.simulation.entities.Creature;

public class PrepareCreaturesToMove extends Action {

    @Override
    public void perform(WorldMap worldMap) {
        for (int column = 0; column < worldMap.getColumn(); column++) {
            for (int row = 0; row < worldMap.getRow(); row++) {
                Coordinates currentCoordinates = new Coordinates(column, row);
                if (worldMap.isContainsEntity(currentCoordinates) && worldMap.getEntity(currentCoordinates) instanceof Creature) {
                    ((Creature) worldMap.getEntity(currentCoordinates)).setReadyToMove(true);
                }
            }
        }
    }
}

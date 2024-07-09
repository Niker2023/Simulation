package main.java.simulation.actions;

import main.java.simulation.WorldMap;
import main.java.simulation.board.Coordinates;
import main.java.simulation.entities.Creature;
import main.java.simulation.entities.Grass;

public class MapCleaner extends Action{

    @Override
    public void perform(WorldMap worldMap) {
        for (int column = 0; column < worldMap.getColumn(); column++) {
            for (int row = 0; row < worldMap.getRow(); row++) {
                Coordinates currentCoordinates = new Coordinates(column, row);
                if (worldMap.isContainsEntity(currentCoordinates)) {
                    if (worldMap.getEntity(currentCoordinates).getClass().getSimpleName().equals("Grass")) {
                        if (((Grass) worldMap.getEntity(currentCoordinates)).getGrowthProgress() <= 0) {
                            worldMap.remoteEntity(currentCoordinates);
                        }
                    } else if (worldMap.getEntity(currentCoordinates).getClass().getSimpleName().equals("Creature")) {
                        if (((Creature) worldMap.getEntity(currentCoordinates)).getHp() <= 0) {
                            worldMap.remoteEntity(currentCoordinates);
                        }
                    }
                }
            }
        }
    }
}

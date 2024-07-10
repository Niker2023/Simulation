package main.java.simulation.actions;

import main.java.simulation.world.WorldMap;
import main.java.simulation.world.Coordinates;
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
                    } else if (worldMap.getEntity(currentCoordinates).getClass().getSimpleName().equals("Herbivore") ||
                            worldMap.getEntity(currentCoordinates).getClass().getSimpleName().equals("Predator")) {
                        if (((Creature) worldMap.getEntity(currentCoordinates)).getHitPoints() <= 0) {
                            worldMap.remoteEntity(currentCoordinates);
                        }
                    }
                }
            }
        }
    }
}

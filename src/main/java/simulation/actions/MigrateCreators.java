package main.java.simulation.actions;

import main.java.simulation.world.WorldMap;
import main.java.simulation.world.Coordinates;
import main.java.simulation.entities.Herbivore;
import main.java.simulation.entities.Predator;

public class MigrateCreators extends Action{
    @Override
    public void perform(WorldMap worldMap) {
        for (int column = 0; column < worldMap.getColumn(); column++) {
            for (int row = 0; row < worldMap.getRow(); row++) {
                Coordinates currentCoordinates = new Coordinates(column, row);
                if ((column == 0 || column == worldMap.getColumn() || row == 0 || row == worldMap.getRow()) &&
                        !worldMap.isContainsEntity(currentCoordinates)) {
                    if (Math.random() < 0.01) {
                        worldMap.placeEntity(currentCoordinates, new Predator());
                    } else if (Math.random() < 0.01) {
                        worldMap.placeEntity(currentCoordinates, new Herbivore());
                    }
                }
            }
        }
    }
}

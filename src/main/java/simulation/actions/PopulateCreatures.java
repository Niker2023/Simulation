package main.java.simulation.actions;

import main.java.simulation.world.WorldMap;
import main.java.simulation.world.Coordinates;
import main.java.simulation.entities.Herbivore;
import main.java.simulation.entities.Predator;

public class PopulateCreatures extends Action{

    @Override
    public void perform(WorldMap worldMap) {
        boolean isHerbivore = false;
        while (!isHerbivore) {
            for (int column = 0; column < worldMap.getColumn(); column++) {
                for (int row = 0; row < worldMap.getRow(); row++) {
                    Coordinates coordinates = new Coordinates(column, row);
                    if (Math.random() < 0.05) {
                        worldMap.placeEntity(coordinates, new Herbivore());
                        isHerbivore = true;
                    } else if (Math.random() < 0.005) {
                        worldMap.placeEntity(coordinates, new Predator());
                    }
                }
            }
        }
    }
}

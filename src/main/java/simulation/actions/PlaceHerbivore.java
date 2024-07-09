package main.java.simulation.actions;

import main.java.simulation.WorldMap;
import main.java.simulation.board.Coordinates;
import main.java.simulation.entities.Herbivore;

public class PlaceHerbivore extends Action{

    @Override
    public void perform(WorldMap worldMap) {
        boolean isHerbivore = false;
        while (!isHerbivore) {
            for (int column = 0; column < worldMap.getColumn(); column++) {
                for (int row = 0; row < worldMap.getRow(); row++) {
                    if (Math.random() < 0.05) {
                        worldMap.placeEntity(new Coordinates(column, row), new Herbivore());
                        isHerbivore = true;
                    }
                }
            }
        }
    }
}

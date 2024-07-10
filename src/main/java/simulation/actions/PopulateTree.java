package main.java.simulation.actions;

import main.java.simulation.world.WorldMap;
import main.java.simulation.world.Coordinates;
import main.java.simulation.entities.Tree;

public class PopulateTree extends Action {

    @Override
    public void perform(WorldMap worldMap) {
        for (int column = 0; column < worldMap.getColumn(); column++) {
            for (int row = 0; row < worldMap.getRow(); row++) {
                if (Math.random() < 0.1) {
                    worldMap.placeEntity(new Coordinates(column, row), new Tree());
                }
            }
        }
    }
}
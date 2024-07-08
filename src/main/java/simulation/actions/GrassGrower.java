package main.java.simulation.actions;

import main.java.simulation.WorldMap;
import main.java.simulation.board.Coordinates;
import main.java.simulation.entities.Grass;

public class GrassGrower extends Action{

    @Override
    public void perform(WorldMap worldMap) {
        for (int column = 0; column < worldMap.getColumn(); column++) {
            for (int row = 0; row < worldMap.getRow(); row++) {
                if (worldMap.isContainsCoordinates(new Coordinates(column, row))) {
                    if ((worldMap.getEntity(new Coordinates(column, row))).getClass().getSimpleName().equals("Grass")) {
                        ((Grass) worldMap.getEntity(new Coordinates(column, row))).growUp();
                    }
                } else if (Math.random() < 0.15) {
                    worldMap.placeEntity(new Coordinates(column, row), new Grass(true));
                }
            }
        }
    }
}

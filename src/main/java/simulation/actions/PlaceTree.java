package main.java.simulation.actions;

import main.java.simulation.WorldMap;
import main.java.simulation.board.Coordinates;
import main.java.simulation.entities.Grass;
import main.java.simulation.entities.Herbivore;
import main.java.simulation.entities.Rock;
import main.java.simulation.entities.Tree;

public class PlaceTree extends Action {

        public void perform(WorldMap worldMap) {

        boolean isHerbivore = false;
        while (!isHerbivore) {
            for (int column = 0; column < worldMap.getColumn(); column++) {
                for (int row = 0; row < worldMap.getRow(); row++) {
                    if (Math.random() < 0.1) {
                        worldMap.placeEntity(new Coordinates(column, row), new Tree());
                    } else if (Math.random() < 0.05) {
                        worldMap.placeEntity(new Coordinates(column, row), new Rock());
                    } else if (Math.random() < 0.05) {
                        worldMap.placeEntity(new Coordinates(column, row), new Herbivore());
                        isHerbivore = true;
                    } else if (Math.random() < 0.15) {
                        worldMap.placeEntity(new Coordinates(column, row), new Grass(false));
                    }
                }
            }
        }
    }
}

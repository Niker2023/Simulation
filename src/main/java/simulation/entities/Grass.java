package main.java.simulation.entities;

import main.java.simulation.board.Coordinates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Grass extends Entity {
    private int growthProgress;

    public Grass() {
        this.growthProgress = 3;
        changeDisplayProgress();
    }

    public void growUp() {
        if (growthProgress < 3) {
            growthProgress++;
        }
    }

    public Coordinates getTheNearestGrass(Coordinates coordinates) {
        Coordinates nearest = null;
        double minDistanse = Double.MAX_VALUE;
        for (Coordinates nextGrass : populationGrass) {
            double currentDistanse = Math.sqrt(Math.pow((nextGrass.getColumn() - coordinates.getColumn()), 2) +
                    Math.pow((nextGrass.getRow() - coordinates.getRow()), 2));
            if (currentDistanse < minDistanse) {
                minDistanse = currentDistanse;
                nearest = nextGrass;
            }
        }
        return nearest;
    }

    public int getGrowthProgress() {
        return growthProgress;
    }

    @Override
    public Coordinates doTheNextAction(HashMap<Coordinates, Entity> oldMap, HashMap<Coordinates, Entity> newMap,
                                       Coordinates currentCoordinate) {
        if (this.getGrowthProgress() > 0) {
            this.growUp();
        } else {
            populationGrass.remove(currentCoordinate);
        }
        return currentCoordinate;
    }

    private void changeDisplayProgress() {
        if (growthProgress >= 1 & growthProgress < 3) {
            setDisplay(" .. ");
        }
        if (growthProgress >= 3) {
            setDisplay("....");
        }
    }

    public void eatIt() {
        growthProgress = growthProgress - 2;
        changeDisplayProgress();
    }
}


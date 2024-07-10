package main.java.simulation.entities;

public class Grass extends Entity {
    private int growthProgress;

    public Grass(boolean young) {
        if (young) {
            this.growthProgress = 1;
        } else {
            this.growthProgress = 3;
        }
    }

    public void growUp() {
        if (growthProgress < 3 && growthProgress != 0) {
            growthProgress++;
        }
    }

//    public Coordinates getTheNearestGrass(Coordinates coordinates) {
//        Coordinates nearest = null;
//        double minDistanse = Double.MAX_VALUE;
//        for (Coordinates nextGrass : populationGrass) {
//            double currentDistanse = Math.sqrt(Math.pow((nextGrass.getColumn() - coordinates.getColumn()), 2) +
//                    Math.pow((nextGrass.getRow() - coordinates.getRow()), 2));
//            if (currentDistanse < minDistanse) {
//                minDistanse = currentDistanse;
//                nearest = nextGrass;
//            }
//        }
//        return nearest;
//    }

    public int getGrowthProgress() {
        return growthProgress;
    }

//    @Override
//    public Coordinates doTheNextAction(HashMap<Coordinates, Entity> oldMap, HashMap<Coordinates, Entity> newMap,
//                                       Coordinates currentCoordinate) {
//        if (this.getGrowthProgress() > 0) {
//            this.growUp();
//        } else {
//            populationGrass.remove(currentCoordinate);
//        }
//        return currentCoordinate;
//    }

    public void eatIt(int eatPower) {
        growthProgress = growthProgress - eatPower;
    }
}


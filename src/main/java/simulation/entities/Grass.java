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

    public int getGrowthProgress() {
        return growthProgress;
    }

    public void eatIt(int eatPower) {
        growthProgress = growthProgress - eatPower;
    }
}


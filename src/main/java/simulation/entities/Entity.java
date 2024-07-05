package main.java.simulation.entities;

import main.java.simulation.board.Coordinates;

public abstract class Entity {

    private Coordinates coordinate;

    public Entity(Coordinates coordinate) {
        this.coordinate = coordinate;
    }


    public Coordinates getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinates coordinate) {
        this.coordinate = coordinate;
    }
}




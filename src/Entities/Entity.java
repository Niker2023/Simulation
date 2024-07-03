package Entities;

import Board.Coordinates;

import java.util.HashMap;

public abstract class Entity {

    private String display;

    public Entity(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return this.display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public Coordinates doTheNextAction(HashMap<Coordinates, Entity> oldMap,
                                                HashMap<Coordinates, Entity> newMap,
                                                Coordinates currentCoordinate){
        return currentCoordinate;
    }

}




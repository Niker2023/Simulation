package main.java.simulation.entities;

public class Predator extends Creature{

//    private void attack(Coordinates coordinates, HashMap<Coordinates, Entity> map) {
//        ((Herbivore) map.get(coordinates)).changeHP(-attackPower);
//        changeHP(attackPower);
//    }

    public Predator() {
        super("Herbivore", 5);
    }
}

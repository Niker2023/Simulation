package main.java.simulation.entities;

import java.util.List;

public abstract class Creature extends Entity {

    static final int ATTACK_POWER = 3;
    static final int HUNGER_DAMAGE = -1;
    private boolean readyToMove;
    private final String prefersFood;
    private int hp;

    public boolean isReadyToMove() {
        return readyToMove;
    }

    public void setReadyToMove(boolean readyToMove) {
        this.readyToMove = readyToMove;
    }
    public Creature(String prefersFood) {
        this.prefersFood = prefersFood;
        this.hp = 8;
        this.readyToMove = true;
    }

    public int getHp() {
        return hp;
    }

    public String getPrefersFood() {
        return prefersFood;
    }

    public void changeHP(int hp){
        this.hp = this.hp + hp;
        if (this.hp > 9) this.hp = 9;
    }

//    abstract HashSet<Coordinates> getValidNearbyCoordinates(HashMap<Coordinates, Entity> oldMap,
//                                                            HashMap<Coordinates, Entity> newMap,
//                                                            Coordinates currentCoordinate);
//
//    List<Coordinates> backtrace(Map<Coordinates, Coordinates> parent, Coordinates start, Coordinates end) {
//        List<Coordinates> path = new ArrayList<>();
//        path.add(end);
//        Coordinates current = end;
//        while (!current.equals(start)) {
//            current = parent.get(current);
//            path.add(current);
//        }
//        Collections.reverse(path);
//        return path;
//    }
//
//    HashSet<Coordinates> getEmptyValidNearbyCoordinates(HashMap<Coordinates, Entity> oldMap,
//                                                        HashMap<Coordinates, Entity> newMap,
//                                                        Coordinates currentCoordinate) {
//        HashSet<Coordinates> setEmptyValidNearbyCoordinates = new HashSet<>();
//        if ((currentCoordinate.getColumn() - 1 >= 0) //не выходим за пределы поля
//                && !(oldMap.containsKey(new Coordinates(currentCoordinate.getColumn() - 1,
//                currentCoordinate.getRow()))) //клетка не занята в старой коллекции
//                && !(newMap.containsKey(new Coordinates(currentCoordinate.getColumn() - 1,
//                currentCoordinate.getRow()))))  //клетка не занята в создаваемой коллекции
//        {
//            setEmptyValidNearbyCoordinates.add(new Coordinates(currentCoordinate.getColumn() - 1,
//                    currentCoordinate.getRow()));
//        }
//        if ((currentCoordinate.getColumn() + 1 < GenerateField.getColumn())
//                && !(oldMap.containsKey(new Coordinates(currentCoordinate.getColumn() + 1,
//                currentCoordinate.getRow())))
//                && !(newMap.containsKey(new Coordinates(currentCoordinate.getColumn() + 1,
//                currentCoordinate.getRow())))) {
//            setEmptyValidNearbyCoordinates.add(new Coordinates(currentCoordinate.getColumn() + 1,
//                    currentCoordinate.getRow()));
//        }
//        if ((currentCoordinate.getRow() - 1 >= 0)
//                && !(oldMap.containsKey(new Coordinates(currentCoordinate.getColumn(),
//                currentCoordinate.getRow() - 1)))
//                && !(newMap.containsKey(new Coordinates(currentCoordinate.getColumn(),
//                currentCoordinate.getRow() - 1)))) {
//            setEmptyValidNearbyCoordinates.add(new Coordinates(currentCoordinate.getColumn(),
//                    currentCoordinate.getRow() - 1));
//        }
//        if ((currentCoordinate.getRow() + 1 < GenerateField.getRow())
//                && !(oldMap.containsKey(new Coordinates(currentCoordinate.getColumn(),
//                currentCoordinate.getRow() + 1)))
//                && !(newMap.containsKey(new Coordinates(currentCoordinate.getColumn(),
//                currentCoordinate.getRow() + 1)))) {
//            setEmptyValidNearbyCoordinates.add(new Coordinates(currentCoordinate.getColumn(),
//                    currentCoordinate.getRow() + 1));
//        }
//        return setEmptyValidNearbyCoordinates;
//    }
}

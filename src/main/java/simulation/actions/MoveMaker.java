package main.java.simulation.actions;

import main.java.simulation.WorldMap;
import main.java.simulation.board.Coordinates;

import java.util.HashSet;

public class MoveMaker extends Action {

    @Override
    public void perform(WorldMap worldMap) {

    }

//    private HashSet<Coordinates> getValidNearbyCoordinates(WorldMap worldMap,
//                                                           Coordinates currentCoordinate) {
//        HashSet<Coordinates> setValidNearbyCoordinates = getEmptyValidNearbyCoordinates(
//                oldMap, newMap, currentCoordinate);
//        if ((currentCoordinate.getColumn() - 1 >= 0) //не выходим за пределы поля
//                && (oldMap.get(new Coordinates(currentCoordinate.getColumn() - 1,
//                currentCoordinate.getRow())) instanceof Grass)) //клетка является травой
//        {
//            setValidNearbyCoordinates.add(new Coordinates(currentCoordinate.getColumn() - 1,
//                    currentCoordinate.getRow()));
//        }
//        if ((currentCoordinate.getColumn() + 1 < GenerateField.getColumn())
//                && (oldMap.get(new Coordinates(currentCoordinate.getColumn() + 1,
//                currentCoordinate.getRow()))) instanceof Grass) {
//            setValidNearbyCoordinates.add(new Coordinates(currentCoordinate.getColumn() + 1,
//                    currentCoordinate.getRow()));
//        }
//        if ((currentCoordinate.getRow() - 1 >= 0)
//                && (oldMap.get(new Coordinates(currentCoordinate.getColumn(),
//                currentCoordinate.getRow() - 1))) instanceof Grass) {
//            setValidNearbyCoordinates.add(new Coordinates(currentCoordinate.getColumn(),
//                    currentCoordinate.getRow() - 1));
//        }
//        if ((currentCoordinate.getRow() + 1 < GenerateField.getRow())
//                && (oldMap.get(new Coordinates(currentCoordinate.getColumn(),
//                currentCoordinate.getRow() + 1))) instanceof Grass) {
//            setValidNearbyCoordinates.add(new Coordinates(currentCoordinate.getColumn(),
//                    currentCoordinate.getRow() + 1));
//        }
//        return setValidNearbyCoordinates;
//    }


}

package main.java.simulation.actions;

import main.java.simulation.world.WorldMap;
import main.java.simulation.world.Coordinates;
import main.java.simulation.entities.Creature;
import main.java.simulation.entities.Entity;
import main.java.simulation.entities.Grass;

import java.util.*;

public class MoveMaker extends Action {

    @Override
    public void perform(WorldMap worldMap) {
        for (int column = 0; column < worldMap.getColumn(); column++) {
            for (int row = 0; row < worldMap.getRow(); row++) {
                Coordinates currentCoordinates = new Coordinates(column, row);
                if (worldMap.isContainsEntity(currentCoordinates)
                        && (worldMap.getEntity(currentCoordinates).getClass().getSimpleName().equals("Herbivore")
                        || worldMap.getEntity(currentCoordinates).getClass().getSimpleName().equals("Predator"))
                        && ((Creature) worldMap.getEntity(currentCoordinates)).isReadyToMove()) {
                    Creature creature = (Creature) worldMap.getEntity(currentCoordinates);
                    Coordinates coordinatesForAction = getCoordinatesForAction(worldMap, currentCoordinates, creature.getPrefersFood());

                    if (coordinatesForAction.equals(currentCoordinates)) {
                        hungryDamage(creature);
                    } else if (!worldMap.isContainsEntity(coordinatesForAction)) {
                        worldMap.moveCreates(currentCoordinates, coordinatesForAction);
                        if (hungryDamage(creature)){
                            worldMap.remoteEntity(coordinatesForAction);
                        }
                    } else {
                        if (eatFood(creature, worldMap.getEntity(coordinatesForAction))) {
                            worldMap.remoteEntity(coordinatesForAction);
                        }
                    }
                    creature.setReadyToMove(false);
                    }
                }
            }
        }

    private boolean eatFood(Creature creature, Entity entity) {
        creature.increaseHitPoints(creature.getAttackPower());
        if (entity.getClass().getSimpleName().equals("Grass")) {
            ((Grass) entity).eatIt(creature.getAttackPower());
            return ((Grass) entity).getGrowthProgress() < 1;
        } else {
            ((Creature) entity).reduceHitPoints(creature.getAttackPower());
            return ((Creature) entity).getHitPoints() < 1;
        }
    }

    private HashSet<Coordinates> getAvailableNearbyCoordinates(WorldMap worldMap, Coordinates currentCoordinates, String targetMove) {
        HashSet<Coordinates> seAvailableNearbyCoordinates = new HashSet<>();
        Set<Coordinates> nearbyCoordinates = new HashSet<>();
        nearbyCoordinates.add(new Coordinates(currentCoordinates.getColumn() + 1, currentCoordinates.getRow())); //upperCoordinates
        nearbyCoordinates.add(new Coordinates(currentCoordinates.getColumn() -1, currentCoordinates.getRow())); //lowerCoordinates
        nearbyCoordinates.add(new Coordinates(currentCoordinates.getColumn(), currentCoordinates.getRow() - 1)); //leftCoordinates
        nearbyCoordinates.add(new Coordinates(currentCoordinates.getColumn(), currentCoordinates.getRow() + 1)); // rightCoordinates

        for (Coordinates c : nearbyCoordinates) {
            if ((isValidCoordinate(c) && ((!worldMap.isContainsEntity(c)) || worldMap.getEntity(c).getClass().getSimpleName().equals(targetMove)))) {
                seAvailableNearbyCoordinates.add(c);
            }
        }

        return seAvailableNearbyCoordinates;
    }

    private boolean isValidCoordinate(Coordinates coordinates) {
        return coordinates.getColumn() >= 0 && coordinates.getColumn() < WorldMap.get().getColumn()
                && coordinates.getRow() >= 0 && coordinates.getRow() < WorldMap.get().getRow();
    }

    private boolean hungryDamage(Creature creature) {
        creature.getHungerDamage();
        return creature.getHitPoints() < 1;
    }

    private Coordinates getCoordinatesForAction(WorldMap worldMap, Coordinates currentCoordinate, String targetMove) {

        Queue<Coordinates> queue = new LinkedList<>();

        Map<Coordinates, Coordinates> parent = new HashMap<>();
        //Добавляем в очередь начальные координаты
        queue.add(currentCoordinate);
        //До тех пор, пока не пройдены все ячейки
        while (!queue.isEmpty()) {

            Coordinates coordinate = queue.poll(); //Работает со следующей ячейкой

            if (worldMap.isContainsEntity(coordinate)
                    && worldMap.getEntity(coordinate).getClass().getSimpleName().equals(targetMove)) {  // Если цель достигнута
                return backtrace(parent, currentCoordinate, coordinate).get(1);  //Возвращаем следующий шаг для достижения цели
            }

            for (Coordinates adjacent : getAvailableNearbyCoordinates(worldMap, coordinate, targetMove)) {  //Смотрим соседей
                if (!parent.containsKey(adjacent)) {
                    parent.put(adjacent, coordinate);  //Если для текущей координаты нет родителя, добавляем
                    queue.add(adjacent);  //Добавляем текущую в очередь
                }
            }
        }
        return currentCoordinate;
    }

    private List<Coordinates> backtrace(Map<Coordinates, Coordinates> parent, Coordinates start, Coordinates end) {
        List<Coordinates> path = new ArrayList<>();
        path.add(end);
        Coordinates current = end;
        while (!current.equals(start)) {
            current = parent.get(current);
            path.add(current);
        }
        Collections.reverse(path);
        return path;
    }
}

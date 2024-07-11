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
                        hungryDamage(creature);
                    } else {
                        eatFood(creature, worldMap.getEntity(coordinatesForAction));
                    }
                    creature.setReadyToMove(false);
                    }
                }
            }
        }

    private void eatFood(Creature creature, Entity entity) {
        creature.increaseHitPoints(creature.getAttackPower());
        if (entity.getClass().getSimpleName().equals("Grass")) {
            ((Grass) entity).eatIt(creature.getAttackPower());
        } else {
            ((Creature) entity).reduceHitPoints(creature.getAttackPower());
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

    private void hungryDamage(Creature creature) {
        creature.getHungerDamage();
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

    public Coordinates getTheNearestTarget(Coordinates coordinates, WorldMap worldMap, String target) {
        Coordinates nearest = coordinates;
        double minDistanse = Double.MAX_VALUE;
        List<Coordinates> targetList;
        if (target.equals("Grass")) {
            targetList = worldMap.getGrassPopulation();
        } else {
            targetList = worldMap.getHerbivoresPopulation();
        }
        for (Coordinates nextGrass : targetList) {
            double currentDistance = Math.abs((nextGrass.getColumn() - coordinates.getColumn())) +
                    Math.abs(nextGrass.getRow() - coordinates.getRow());
            if (currentDistance < minDistanse) {
                minDistanse = currentDistance;
                nearest = nextGrass;
            }
        }
        return nearest;
    }

    class PathFinder {

        class Node implements Comparable<Node>{

            private final Coordinates coordinates;
            private int pathFromStart;
            private int pathToTarget;
            private Node parent;

            public Node(Coordinates coordinates) {
                this.coordinates = coordinates;
            }

            public Coordinates getCoordinates() {
                return coordinates;
            }

            public int getPathFromStart() {
                return pathFromStart;
            }

            public void setPathFromStart(int pathFromStart) {
                this.pathFromStart = pathFromStart;
            }

            public int getPathToTarget() {
                return pathToTarget;
            }

            public void setPathToTarget(int pathToTarget) {
                this.pathToTarget = pathToTarget;
            }

            public Node getParent() {
                return parent;
            }

            public void setParent(Node parent) {
                this.parent = parent;
            }

            @Override
            public int compareTo(Node node) {
                return Double.compare(pathToTarget, node.pathToTarget);
            }
        }

        private List<Node> backtrace(Node current) {
            List<Node> path = new ArrayList<>();
            while (current != null) {
                path.add(0, current);
                current = current.getParent();
            }
            return path;
        }

        private int heuristic(Node current, Node target) {
            return Math.abs(current.getCoordinates().getColumn() - target.getCoordinates().getColumn())
                    + Math.abs(current.getCoordinates().getRow() - target.getCoordinates().getRow());
        }

        private Coordinates getCoordinatesForAction(WorldMap worldMap, Coordinates currentCoordinate, String targetMove) {

            Map<Coordinates, Node> graph = new HashMap<>();

            Queue<Node> queue = new PriorityQueue<>();
            Node start = new Node(currentCoordinate);
            queue.add(start);  //Добавляем в очередь начальные координаты
            graph.put(currentCoordinate, start); //Список посещенных координат
            //До тех пор, пока не пройдены все ячейки
            while (!queue.isEmpty()) {

                Node node = queue.poll(); //Работает со следующей ячейкой


                if (worldMap.isContainsEntity(node.getCoordinates())
                        && worldMap.getEntity(node.getCoordinates()).getClass().getSimpleName().equals(targetMove)) {  // Если цель достигнута
                    return backtrace(node).get(1).getCoordinates();  //Возвращаем следующий шаг для достижения цели
                }

                for (Coordinates adjacent : getAvailableNearbyCoordinates(worldMap, node.getCoordinates(), targetMove)) {  //Смотрим соседей
                    Node currentNode = new Node(adjacent);
                    int pathCost = node.getPathFromStart() + heuristic(node, currentNode);
                    if (!graph.containsKey(adjacent) || pathCost < currentNode.getPathFromStart()) {
                        currentNode.setPathFromStart(pathCost);
                        currentNode.setPathToTarget(pathCost + heuristic(currentNode, getTheNearestTarget(targetMove)));

                        parent.put(adjacent, coordinate);  //Если для текущей координаты нет родителя, добавляем
                        queue.add(adjacent);  //Добавляем текущую в очередь
                    }
                }
            }
            return currentCoordinate;
        }



    }
}

package conductor;

import main.java.simulation.world.Coordinates;
import main.java.simulation.world.WorldMap;

import java.util.*;

public class PathFinder {

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

    public Coordinates getCoordinatesForAction(WorldMap worldMap, Coordinates currentCoordinate, String targetMove) {

        Coordinates targetCoordinates = getTheNearestTarget(currentCoordinate, worldMap, targetMove);
        System.out.println(" Координаты цели rc " + targetCoordinates.getRow() + targetCoordinates.getColumn());

        Set<Coordinates> visitedNodes = new HashSet<>();

        Queue<Node> nodesQueue = new PriorityQueue<>();
        Node start = new Node(currentCoordinate);
        Node finish = new Node(targetCoordinates);
        nodesQueue.add(start);  //Добавляем в очередь начальные координаты
        visitedNodes.add(start.getCoordinates()); //Список посещенных координат

        System.out.print("Маршрут rc ");

        while (!nodesQueue.isEmpty()) {   //До тех пор, пока не пройдены все ячейки



            Node currentNode = nodesQueue.peek(); //Работает со следующей ячейкой

            System.out.println("рассматриваемая нода:" + currentNode.getCoordinates().getRow()+ "_" + currentNode.getCoordinates().getColumn());

            if (currentNode.getCoordinates().equals(targetCoordinates)) {  // Если цель достигнута
                try {
                    return backtrace(currentNode).get(1).getCoordinates();  //Возвращаем следующий шаг для достижения цели
                } catch (IndexOutOfBoundsException e) {
                    return currentCoordinate;
                }
            }
            visitedNodes.add(currentNode.getCoordinates());

            for (Coordinates adjacent : getAvailableNearbyCoordinates(worldMap, currentNode.getCoordinates(), targetMove)) {  //Смотрим соседей
                Node adjacentNode = new Node(adjacent);
                System.out.print(adjacent.getRow()+ "_" +adjacent.getColumn() + " -> " );

                int pathCost = currentNode.getPathFromStart() + 1;

                if (!visitedNodes.contains(adjacentNode.getCoordinates()) || pathCost < adjacentNode.getPathFromStart()) {
                    adjacentNode.setPathFromStart(pathCost);
                    adjacentNode.setPathToTarget(heuristic(adjacentNode, finish));
                    adjacentNode.setPathFromStartToTarget(pathCost + adjacentNode.getPathToTarget());
                    adjacentNode.setParent(currentNode);
                    nodesQueue.add(adjacentNode);  //Добавляем текущую в очередь
                }
            }
            nodesQueue.remove(currentNode);
        }
        System.out.println();
        return currentCoordinate;
    }

    private Coordinates getTheNearestTarget(Coordinates coordinates, WorldMap worldMap, String target) {
        Coordinates nearest = coordinates;
        double minDistanse = Double.MAX_VALUE;
        List<Coordinates> targetList;
        if (target.equals("Grass")) {
            targetList = worldMap.getGrassPopulation();
        } else {
            targetList = worldMap.getHerbivoresPopulation();
        }
        for (Coordinates nextGrass : targetList) {
//            double currentDistance = Math.abs((nextGrass.getColumn() - coordinates.getColumn())) +
//                    Math.abs(nextGrass.getRow() - coordinates.getRow());
            double currentDistance = Math.sqrt(Math.pow((nextGrass.getColumn() - coordinates.getColumn()), 2) +
                    Math.pow((nextGrass.getRow() - coordinates.getRow()), 2));
            if (currentDistance < minDistanse) {
                minDistanse = currentDistance;
                nearest = nextGrass;
            }
        }
        return nearest;
    }

    private boolean isValidCoordinate(Coordinates coordinates) {
        return coordinates.getColumn() >= 0 && coordinates.getColumn() < WorldMap.get().getColumn()
                && coordinates.getRow() >= 0 && coordinates.getRow() < WorldMap.get().getRow();
    }

    private HashSet<Coordinates> getAvailableNearbyCoordinates(WorldMap worldMap, Coordinates currentCoordinates, String targetMove) {
        HashSet<Coordinates> setAvailableNearbyCoordinates = new HashSet<>();
        Set<Coordinates> nearbyCoordinates = new HashSet<>();
        nearbyCoordinates.add(new Coordinates(currentCoordinates.getColumn() + 1, currentCoordinates.getRow())); //upperCoordinates
        nearbyCoordinates.add(new Coordinates(currentCoordinates.getColumn() -1, currentCoordinates.getRow())); //lowerCoordinates
        nearbyCoordinates.add(new Coordinates(currentCoordinates.getColumn(), currentCoordinates.getRow() - 1)); //leftCoordinates
        nearbyCoordinates.add(new Coordinates(currentCoordinates.getColumn(), currentCoordinates.getRow() + 1)); // rightCoordinates

        for (Coordinates c : nearbyCoordinates) {
            if ((isValidCoordinate(c) && ((!worldMap.isContainsEntity(c)) || worldMap.getEntity(c).getClass().getSimpleName().equals(targetMove)))) {
                setAvailableNearbyCoordinates.add(c);
            }
        }

        return setAvailableNearbyCoordinates;
    }
}

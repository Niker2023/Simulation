package conductor;

import main.java.simulation.actions.MoveMaker;
import main.java.simulation.world.Coordinates;

public class Node implements Comparable<Node>{

    private final Coordinates coordinates;
    private int pathFromStart;
    private int pathToTarget;
    private int pathFromStartToTarget;
    private Node parent;

    public Node(Coordinates coordinates) {
        this.coordinates = coordinates;
        this.pathFromStart = 100000;
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

    public int getPathFromStartToTarget() {
        return pathFromStartToTarget;
    }

    public void setPathFromStartToTarget(int pathFromStartToTarget) {
        this.pathFromStartToTarget = pathFromStartToTarget;
    }

    @Override
    public int compareTo(Node node) {
        return Integer.compare(pathToTarget, node.pathToTarget);
    }
}

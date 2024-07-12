package conductor;

import main.java.simulation.world.Coordinates;

public class Node implements Comparable<Node>{

    private final Coordinates coordinates;
    private int pathFromStart;

    private int pathToTarget;
    private int pathFromStartToTarget;
    private Node parent;

    public Node(Coordinates coordinates) {
        this.coordinates = coordinates;
        this.pathFromStart = 0;
        this.pathToTarget = 0;
        this.pathFromStartToTarget = 0;
    }

    public int getPathToTarget() {
        return pathToTarget;
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

    public void setPathToTarget(int pathToTarget) {
        this.pathToTarget = pathToTarget;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setPathFromStartToTarget(int pathFromStartToTarget) {
        this.pathFromStartToTarget = pathFromStartToTarget;
    }

    @Override
    public int compareTo(Node node) {
        return Integer.compare(pathFromStartToTarget, node.pathFromStartToTarget);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return node.getCoordinates().getColumn() == coordinates.getColumn() && node.getCoordinates().getRow() == coordinates.getRow();
    }
}

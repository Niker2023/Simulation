package main.java.simulation.board;

import main.java.simulation.entities.*;

import java.util.HashMap;

//public class GenerateField {
//
//    private static HashMap<Coordinates, Entity> worldMap = null;
//    private static int row;
//    private static int column;
//    private static boolean worldIsGenerated = false;
//    private static HashMap<Coordinates, Entity> generateField(int inRow, int inColumn) {
//        row = inRow;
//        column = inColumn;
//        HashMap<Coordinates, Entity> map = new HashMap<>();
//        boolean isHerbivore = false;
//        while (!isHerbivore) {
//            for (int column = 0; column < GenerateField.column; column++) {
//                for (int row = 0; row < GenerateField.row; row++) {
//                    if (Math.random() < 0.1) {
//                        map.put(new Coordinates(column, row), new Tree());
//                    } else if (Math.random() < 0.05) {
//                        map.put(new Coordinates(column, row), new Rock());
//                    } else if (Math.random() < 0.05) {
//                        map.put(new Coordinates(column, row), new Herbivore(8));
//                        isHerbivore = true;
//                    } else if (Math.random() < 0.15) {
//                        map.put(new Coordinates(column, row), new Grass(3, new Coordinates(column, row)));
//                    }
//                }
//            }
//        }
//        worldIsGenerated = true;
//        worldMap = map;
//        return worldMap;
//    }
//
//    public static boolean isWorldIsGenerated() {
//        return worldIsGenerated;
//    }
//
//    public static HashMap<Coordinates, Entity> getMap() {
//        if (worldMap == null) {
//            return generateField(row, column);
//        } else {
//            return worldMap;
//        }
//    }
//
//    public static int getColumn() {
//        return column;
//    }
//
//    public static void setColumn(int column) {
//        GenerateField.column = column;
//    }
//
//    public static int getRow() {
//        return row;
//    }
//
//    public static void setRow(int row) {
//        GenerateField.row = row;
//    }
//}


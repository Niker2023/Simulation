package main.java.simulation.board;

import main.java.simulation.entities.Entity;
import java.util.HashMap;

//public class DisplayField {
//    public static void displayField(HashMap<Coordinates, Entity> map) {
//        System.out.print("__");
//        for (int i = 0; i < GenerateField.getColumn(); i++) {
//            System.out.print("____");
//        }
//        System.out.println();
//        for (int row = 0; row < GenerateField.getColumn(); row++) {
//            for (int column = 0; column < GenerateField.getRow(); column++) {
//                if (column == 0) {
//                    System.out.print("|");
//                }
//                if (map.containsKey(new Coordinates(column, row))) {
//                    System.out.print(map.get(new Coordinates(column, row)).getDisplay());
//                } else {
//                    System.out.print("    ");
//                }
//                if (column == GenerateField.getColumn() - 1) {
//                    System.out.print("|");
//                    System.out.println();
//                }
//            }
//        }
//        System.out.print("‾‾");
//        for (int i = 0; i < GenerateField.getColumn(); i++) {
//            System.out.print("‾‾‾‾");
//        }
//    }
//}
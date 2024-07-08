package main.java.simulation.entities;

public class Herbivore extends Creature {


    public Herbivore() {
        super();
    }

//    @Override
//    HashSet<Coordinates> getValidNearbyCoordinates(HashMap<Coordinates, Entity> oldMap,
//                                                          HashMap<Coordinates, Entity> newMap,
//                                                          Coordinates currentCoordinate) {
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

//    @Override
//    public Coordinates doTheNextAction(HashMap<Coordinates, Entity> oldMap, HashMap<Coordinates, Entity> newMap,
//                                       Coordinates currentCoordinate) {
//        //Если существо не погибло
//        if (getHp() > 0) {
//            Coordinates coordinates = nextStep(oldMap, newMap, currentCoordinate);
//            // Если есть куда ходить
//            if (coordinates != null) {
//                //Если смежная клетка является травой
//                if (oldMap.get(coordinates) instanceof Grass) {
//                    ((Grass) oldMap.get(coordinates)).eatIt();
//                    changeHP(+2);
//                    return currentCoordinate;
//                } else {
//                    changeHP(hungerDamage);
//                    //Или делаем ход в сторону травы
//                    return coordinates;
//                }
//                // Если ход заблокирован
//            } else {
//                changeHP(hungerDamage);
//                return currentCoordinate;
//            }
//        }
//        return null;
//    }

//    Coordinates nextStep(HashMap<Coordinates, Entity> oldMap, HashMap<Coordinates, Entity> newMap,
//                                Coordinates currentCoordinate) {
//
//        Queue<Coordinates> queue = new LinkedList<>();
//
//        Map<Coordinates, Coordinates> parent = new HashMap<>();
//        //Добавляем в очередь начальные координаты
//        queue.add(currentCoordinate);
//        //До тех пор пока не пройдены все ячейки
//        while (!queue.isEmpty()) {
//            //Работает со следующей ячейкой
//            Coordinates coordinate = queue.poll();
//            // Если цель достигнута
//            if (oldMap.get(coordinate) instanceof Grass) {
//                //Возвращаем следующий шаг для достижения цели
//                return backtrace(parent, currentCoordinate, coordinate).get(1);
//            }
//            //Смотрим соседей
//            for (Coordinates adjacent : ((Creature) oldMap.get(currentCoordinate)).getValidNearbyCoordinates(
//                    oldMap, newMap, coordinate)) {
//                if (!parent.containsKey(adjacent)) {
//                    //Если для текущей координаты нет родителя, добавляем
//                    parent.put(adjacent, coordinate);
//                    //Добавляем текущую в очередь
//                    queue.add(adjacent);
//                }
//            }
//        }
//        return null;
//    }

//    public void changeHP(int hp) {
//        super.changeHP(hp);
//        if (getHp() < 1) {
//            setDisplay("    ");
//        } else {
//            setDisplay("┌♞┐" + getHp());
//        }
//    }
}

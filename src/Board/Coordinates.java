package Board;

public class Coordinates {
    private final int column;
    private final int row;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinates coordinates = (Coordinates) o;
        return coordinates.column == column && coordinates.row == row;
    }


    @Override
    public int hashCode() {
        return column * 100 + row;
    }

    public Coordinates(int column, int row) {
            this.column = column;
            this.row = row;
        }

    public int getColumn() {
            return column;
        }

    public int getRow() {
            return row;
        }

    }





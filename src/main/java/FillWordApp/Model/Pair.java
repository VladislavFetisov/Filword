package FillWordApp.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public  class Pair {

    public final int row;
    public final int column;
    public static final ArrayList<Pair> directions= new ArrayList<>(Arrays.asList(new Pair(-1, 0),
            new Pair(0, 1), new Pair(1, 0), new Pair(0, -1)));

    public Pair(int row, int column) {
        this.row = row;
        this.column = column;

    }

    @Override
    public String toString() {
        return "Pair{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return row == pair.row &&
                column == pair.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}

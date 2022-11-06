import java.util.Arrays;

public class RubikSide {
    private final int size;
    private int[][] values;
    private int[][] columns;
    private int[][] rows;

    public RubikSide(int size, int value) {
        this.size = size;
        this.values = new int[size][size];
        fill(value);
    }

    private void fill(int value) {
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                this.values[i][j] = value;
            }
        }
    }

    public int getSize() {
        return this.size;
    }

    public int[] getRow(int row) {
        return this.values[row];
    }

    public int[] getColumn(int column) {
        int[] result = new int[getSize()];
        for (int i = 0; i < getSize(); i++) {
            result[i] = this.values[i][column];
        }
        return result;
    }

    public void setRow(int row, int[] newValues) {
        this.values[row] = newValues;
    }

    public void setColumn(int column, int[] newValues) {
        for (int i = 0; i < getSize(); i++) {
            this.values[i][column] = newValues[i];
        }
    }

    public void rotateAnticlockwise() {
    }

    public void rotateClockwise() {
        int[][] columnaReversed = new int[getSize()][getSize()];
        for (int i = 0; i < getSize(); i++) {
            setColumn(getSize() - (i + 1), getRow(i));
        }
    }
}

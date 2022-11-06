public class RubikSide {
    private final int size;
    private int[][] rows;
    private int[][] columns;

    public RubikSide(int size, int value) {
        this.size = size;
        this.rows = new int[size][size];
        this.columns = new int[size][size];
        fillSameValue(value);
    }

    private void fillSameValue(int value) {
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                this.rows[i][j] = value;
                this.columns[j][i] = value;
            }
        }
    }

    public int getSize() {
        return this.size;
    }

    public int[] getRow(int row) {
        return this.rows[row];
    }

    public int[] getColumn(int column) {
        return this.columns[column];
    }

    public void setRow(int row, int[] newValues) {
        this.rows[row] = newValues;
    }

    public void setColumn(int column, int[] newValues) {
        this.columns[column] = newValues;
    }

    public void rotateAnticlockwise() {
        for (int i = 0; i < getSize(); i++) {
            int[] rowCopy = getRow(i);
            setRow(i, getColumn(getSize() - (1 + i)));
            setColumn(i, reversed(rowCopy));
        }
    }

    public void rotateClockwise() {
        for (int i = 0; i < getSize(); i++) {
            int[] columnCopy = getColumn(i);
            setColumn(i, getRow(getSize() - (1 + i)));
            setRow(i, reversed(columnCopy));
        }
    }

    public int[] reversed(int[] array) {
        int[] reversed = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            reversed[i] = array[array.length - (i + 1)];
        }
        return reversed;
    }


    public void printData() {
        StringBuilder dataRows = new StringBuilder();
        StringBuilder dataColumns = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                dataRows.append(rows[i][j]).append("\t");
                dataColumns.append(columns[j][i]).append("\t");
            }
            dataRows.append("\n");
            dataColumns.append("\n");
        }
        System.out.println("Rows: \n" + dataRows);
        System.out.println("Columns: \n" + dataColumns);

    }

    // Fill with different values, useful for testing.
    public void fill() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < getSize(); j++) {
                this.rows[i][j] = (i * getSize()) + (j + 1);
                this.columns[j][i] = (i * getSize()) + (j + 1);
            }
        }
    }

}
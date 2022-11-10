public class RubikSide {
    private final int size;
    private final int[][] rows;
    private final int[][] columns;

    public RubikSide(int size, int value) {
        this.size = size;
        this.rows = new int[size][size];
        this.columns = new int[size][size];
        fillSameValue(value);
    }

    // Change view of the side.
    public RubikSide(RubikSide side, boolean isTop) {
        this.size = side.getSize();
        this.rows = new int[size][size];
        this.columns = new int[size][size];
        for(int i = 0; i < size; i++){
            if(isTop){
                // Top side.
                this.rows[i] = side.getColumn(side.getSize() - (i + 1));
                this.columns[i] = reversed(side.getRow(i));
            }
            else{
                // Bottom side.
                this.rows[i] = reversed(side.getColumn(i));
                this.columns[i] = side.getRow(side.getSize() - (i + 1));
            }
        }
    }

    private void fillSameValue(int value) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
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
        int [][] columnsCopy = this.columns.clone();
        int [][] rowsCopy = this.rows.clone();
        for (int i = 0; i < size; i++) {
            setRow(i, columnsCopy[size - (1 + i)]);
            setColumn(i, reversed(rowsCopy[i]));
        }
    }

    public void rotateClockwise() {
        int [][] columnsCopy = this.columns.clone();
        int [][] rowsCopy = this.rows.clone();
        for (int i = 0; i < size; i++) {
            setColumn(i, rowsCopy[size - (1 + i)]);
            setRow(i, reversed(columnsCopy[i]));
        }
    }

    public int[] reversed(int[] array) {
        int[] reversedArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            reversedArray[i] = array[array.length - (i + 1)];
        }
        return reversedArray;
    }

    public void updateRow(int col){
        for (int i = 0; i < size; i++) {
            this.rows[i][col] = this.columns[col][i];
        }
    }

    public void updateColumn(int row){
        for (int i = 0; i < size; i++) {
            this.columns[i][row] = this.rows[row][i];
        }
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

    // Fill with different values, useful for testing a single side.
    public void fill() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < getSize(); j++) {
                this.rows[i][j] = (i * getSize()) + (j + 1);
                this.columns[j][i] = (i * getSize()) + (j + 1);
            }
        }
    }

}
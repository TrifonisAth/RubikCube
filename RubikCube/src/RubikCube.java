import java.util.Arrays;

public class RubikCube {
    private final RubikSide front;
    private final RubikSide back;
    private final RubikSide left;
    private final RubikSide right;
    private final RubikSide top;
    private final RubikSide bottom;
    private final int size;

    public RubikCube(int size) {
        this.size = size;
        this.front = new RubikSide(size, 1);
        this.right = new RubikSide(size, 2);
        this.back = new RubikSide(size, 3);
        this.left = new RubikSide(size, 4);
        this.top = new RubikSide(size, 5);
        this.bottom = new RubikSide(size, 6);
    }

    public RubikSide getFront() {
        return this.front;
    }

    public RubikSide getBack() {
        return this.back;
    }

    public RubikSide getLeft() {
        return this.left;
    }

    public RubikSide getRight() {
        return this.right;
    }

    public RubikSide getTop() {
        return this.top;
    }

    public RubikSide getBottom() {
        return this.bottom;
    }

    public int getSize() {
        return this.size;
    }

    // Turn selected row to the right if direction is 1, to the left if direction is -1.
    public void turnRow(int row, int direction) {
        int[] rowCopy = getFront().getRow(row);
        // Right turn.
        if (direction == 1) {
            getFront().setRow(row, getLeft().getRow(row));
            getLeft().setRow(row, getBack().getRow(row));
            getBack().setRow(row, getRight().getRow(row));
            getRight().setRow(row, rowCopy);
            if (row == 0) {
                getTop().rotateAnticlockwise();
            } else if(row == getSize() - 1) {
                getBottom().rotateClockwise();
            }
            // Left turn.
        } else if (direction == -1) {
            getFront().setRow(row, getRight().getRow(row));
            getRight().setRow(row, getBack().getRow(row));
            getBack().setRow(row, getLeft().getRow(row));
            getLeft().setRow(row, rowCopy);
            if (row == 0) {
                getTop().rotateClockwise();
            } else if(row == getSize() - 1) {
                getBottom().rotateAnticlockwise();
            }
        }
        updateColumns(row);
    }

    public void updateRows(int col){
        getFront().updateRow(col);
        getBack().updateRow(size - (1 + col));
        getTop().updateRow(col);
        getBottom().updateRow(col);
    }

    public void updateColumns(int row){
        getFront().updateColumn(row);
        getBack().updateColumn(row);
        getLeft().updateColumn(row);
        getRight().updateColumn(row);
    }

    // Print cube sides.
    public void printCube() {
        StringBuilder str = new StringBuilder();
        str.append("      -------- TOP --------\n");
        for (int i = 0; i < size; i++) {
            str.append("\t".repeat(size/2 +1).concat("\t").concat(Arrays.toString(getTop().getRow(i))));
            str.append("\n");
        }
        str.append("\n");
        for (int i = 0; i < size; i++){
            str.append(Arrays.toString(getLeft().getRow(i))).append("\t");
            str.append(Arrays.toString(getFront().getRow(i))).append("\t");
            str.append(Arrays.toString(getRight().getRow(i))).append("\t");
            str.append(Arrays.toString(getBack().getRow(i)));
            str.append("\n");
        }
        str.append("\n");
        for (int i = 0; i < size; i++) {
            str.append("\t".repeat(size/2 + 1).concat("\t").concat(Arrays.toString(getBottom().getRow(i))));
            str.append("\n");
        }
        str.append("      ------ BOTTOM -------");
        System.out.println(str);
    }


    public void turnCol(int col, int direction) {
        int[] colCopy = getFront().getColumn(col);
        // Turn up.
        if (direction == 1){
            getFront().setColumn(col, getBottom().getColumn(col));
            getBottom().setColumn(col, reversed(getBack().getColumn(size - (col + 1))));
            getBack().setColumn(size - (col + 1), reversed(getTop().getColumn(col)));
            getTop().setColumn(col, colCopy);
            if (col == 0) {
                getLeft().rotateAnticlockwise();
            } else if (col == getSize() - 1) {
                getRight().rotateClockwise();
            }
            // Turn down.
        } else if (direction == -1){
            getFront().setColumn(col, getTop().getColumn(col));
            getTop().setColumn(col, reversed(getBack().getColumn(size - (col + 1))));
            getBack().setColumn(size - (col + 1),  reversed(getBottom().getColumn(col)));
            getBottom().setColumn(col, colCopy);

           if (col == 0) {
               getLeft().rotateClockwise();
           } else if (col == getSize() - 1) {
               getRight().rotateAnticlockwise();
           }
        }
        updateRows(col);
    }

    public int[] reversed(int[] array) {
        int[] reversedArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            reversedArray[i] = array[array.length - (i + 1)];
        }
        return reversedArray;
    }

    public void rotateBackSide(int direction){
        RubikSide backCopy = new RubikSide(getBack());
        if (direction == 1){
            // Rotate back side clockwise.

        } else if (direction == -1){
            // Rotate back side anticlockwise.

        }
    }
}

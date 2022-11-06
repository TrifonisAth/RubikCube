import java.util.Arrays;

public class RubikCube {
    private RubikSide front;
    private RubikSide back;
    private RubikSide left;
    private RubikSide right;
    private RubikSide top;
    private RubikSide bottom;
    private int size;

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
    }

    // Print cube sides.
    public void printCube() {
        String[][] cube = new String[getSize() * 2 + 1][getSize() * 2 + 1];
        StringBuilder str = new StringBuilder();
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
        System.out.println(str);
    }


}

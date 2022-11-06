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
        this.back = new RubikSide(size, 2);
        this.left = new RubikSide(size, 3);
        this.right = new RubikSide(size, 4);
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


}

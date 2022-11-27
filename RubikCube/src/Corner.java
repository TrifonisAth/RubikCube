import java.util.Arrays;

public class Corner {
    private char[] tiles;
    private int orientation;
    private final int correctPosition;

    public Corner(final char[] tiles, final int correctPosition) {
        this.tiles = tiles;
        this.orientation = 0;
        this.correctPosition = correctPosition;
    }

    public Corner(final Corner corner) {
        this.tiles = corner.tiles.clone();
        this.orientation = corner.orientation;
        this.correctPosition = corner.correctPosition;
    }

    public char getLeftTile() {
        if (this.orientation == 0) {
            return this.tiles[0];
        } else if (this.orientation == 1) {
            return this.tiles[2];
        } else {
            return this.tiles[1];
        }
    }

    public char getRightTile() {
        if (this.orientation == 0) {
            return this.tiles[2];
        } else if (this.orientation == 1) {
            return this.tiles[1];
        } else {
            return this.tiles[0];
        }
    }

    public char getTopTile() {
        if (this.orientation == 0) {
            return this.tiles[1];
        } else if (this.orientation == 1) {
            return this.tiles[0];
        } else {
            return this.tiles[2];
        }
    }

    public char[] getTiles() {
        return this.tiles;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public void rotateClockwise() {
        this.orientation = (this.orientation + 1) % 3;
    }

    public void rotateCounterClockwise() {
        this.orientation = (this.orientation + 2) % 3;
    }

}

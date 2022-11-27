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

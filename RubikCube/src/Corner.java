public class Corner {
    private char[] tiles;
    private int orientation;

    public Corner(final char[] tiles, final int orientation) {
        this.tiles = tiles;
        this.orientation = orientation;
    }

    public  Corner(final char[] tiles) {
        this(tiles, 0);
    }

    public Corner(final Corner corner) {
        this.tiles = corner.tiles.clone();
        this.orientation = corner.orientation;
    }

    public char[] getTiles() {
        return this.tiles;
    }

    public int getOrientation() {
        return this.orientation;
    }

}

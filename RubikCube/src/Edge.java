public class Edge {
    private char[] tiles;
    private int orientation;

    public Edge(final char[] tiles, final int orientation) {
        this.tiles = tiles;
        this.orientation = orientation;
    }

    public  Edge(final char[] tiles) {
        this(tiles, 0);
    }

    public Edge(final Edge edge) {
        this.tiles = edge.tiles.clone();
        this.orientation = edge.orientation;
    }

    public char[] getTiles() {
        return this.tiles;
    }

    public int getOrientation() {
        return this.orientation;
    }



}

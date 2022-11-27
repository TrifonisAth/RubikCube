public class Edge {
    private final char[] tiles;
    private boolean orientation;
    private final int correctPosition;

    public Edge(final char[] tiles, final int correctPosition) {
        this.tiles = tiles;
        this.orientation = true;
        this.correctPosition = correctPosition;
    }

    public Edge(final Edge edge) {
        this.tiles = edge.tiles.clone();
        this.orientation = edge.orientation;
        this.correctPosition = edge.correctPosition;
    }

    public char getTopTile() {
        if (this.orientation) {
            return this.tiles[0];
        } else {
            return this.tiles[1];
        }
    }

    public char getBottomTile() {
        if (this.orientation) {
            return this.tiles[1];
        } else {
            return this.tiles[0];
        }
    }

    public void flipOrientation(){
        this.orientation = !this.orientation;
    }

    public char[] getTiles() {
        return this.tiles;
    }

    public boolean getOrientation() {
        return this.orientation;
    }



}

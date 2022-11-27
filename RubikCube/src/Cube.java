public class Cube implements RubikCube{

    // The cube is represented as a 3x3x3 array of integers.
    // Each integer represents a color.
    private int[][][] tiles;

    public Cube() {
        // Initialize the cube to a solved state.
        tiles = new int[6][3][3];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    tiles[i][j][k] = i+1;
                }
            }
        }
    }

    // Rotate the front face clockwise.
    private void rotateFrontClockwise(){
        // Rotate the front face.
        // Corner tiles.
        int temp = tiles[0][0][0];
        tiles[0][0][0] = tiles[0][2][0];
        tiles[0][2][0] = tiles[0][2][2];
        tiles[0][2][2] = tiles[0][0][2];
        tiles[0][0][2] = temp;
        // Edge tiles.
        temp = tiles[0][0][1];
        tiles[0][0][1] = tiles[0][1][0];
        tiles[0][1][0] = tiles[0][2][1];
        tiles[0][2][1] = tiles[0][1][2];
        tiles[0][1][2] = temp;
        // Rotate the adjacent faces.
        // Top face.
        temp = tiles[4][0][0];

    }

    @Override
    public void F_l() {

    }

    @Override
    public void F_r() {

    }

    @Override
    public void B_l() {

    }

    @Override
    public void B_r() {

    }

    @Override
    public void U_l() {

    }

    @Override
    public void U_r() {

    }

    @Override
    public void D_l() {

    }

    @Override
    public void D_r() {

    }

    @Override
    public void L_l() {

    }

    @Override
    public void L_r() {

    }

    @Override
    public void R_l() {

    }

    @Override
    public void R_r() {

    }

    @Override
    public void printCube() {

    }
}

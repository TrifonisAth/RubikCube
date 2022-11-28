import java.util.HashMap;
import java.util.Map;

public class Cube implements RubikCube {

    // The cube is represented as a 3x3x3 array of integers.
    // Each integer represents a color.
    private final int[][][] tiles;
    // Map with the faces of the cube from 1 to 6.
    private static final Map<Integer, String> faces = new HashMap<>();

    static {
        faces.put(1, "Front");
        faces.put(2, "Right");
        faces.put(3, "Back");
        faces.put(4, "Left");
        faces.put(5, "Top");
        faces.put(6, "Bottom");
    }


    public Cube() {
        // Initialize the cube to a solved state.
        tiles = new int[6][3][3];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    tiles[i][j][k] = i + 1;
                }
            }
        }
    }

    // Copy constructor.
    public Cube(Cube cube) {
        tiles = new int[6][3][3];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                System.arraycopy(cube.tiles[i][j], 0, tiles[i][j], 0, 3);
            }
        }
    }


    // Cube rotation methods.

    // Rotate the front face clockwise.
    private void rotateFrontClockwise() {
        // Rotate the front face.
        // Front face corner tiles.
        int temp = tiles[0][0][0];
        tiles[0][0][0] = tiles[0][2][0];
        tiles[0][2][0] = tiles[0][2][2];
        tiles[0][2][2] = tiles[0][0][2];
        tiles[0][0][2] = temp;
        // Front face edge tiles.
        temp = tiles[0][0][1];
        tiles[0][0][1] = tiles[0][1][0];
        tiles[0][1][0] = tiles[0][2][1];
        tiles[0][2][1] = tiles[0][1][2];
        tiles[0][1][2] = temp;
        // Rotate the adjacent faces.
        // Corners.
        temp = tiles[4][2][0];
        tiles[4][2][0] = tiles[3][2][2];
        tiles[3][2][2] = tiles[5][0][2];
        tiles[5][0][2] = tiles[1][0][0];
        tiles[1][0][0] = temp;
        temp = tiles[4][2][2];
        tiles[4][2][2] = tiles[3][0][2];
        tiles[3][0][2] = tiles[5][0][0];
        tiles[5][0][0] = tiles[1][2][0];
        tiles[1][2][0] = temp;
        // Edges.
        temp = tiles[4][2][1];
        tiles[4][2][1] = tiles[3][1][2];
        tiles[3][1][2] = tiles[5][0][1];
        tiles[5][0][1] = tiles[1][1][0];
        tiles[1][1][0] = temp;
    }

    // Rotate the front face anti-clockwise.
    private void rotateFrontAntiClockwise() {
        // Rotate the front face.
        // Front face corner tiles.
        int temp = tiles[0][0][0];
        tiles[0][0][0] = tiles[0][0][2];
        tiles[0][0][2] = tiles[0][2][2];
        tiles[0][2][2] = tiles[0][2][0];
        tiles[0][2][0] = temp;
        // Front face edge tiles.
        temp = tiles[0][0][1];
        tiles[0][0][1] = tiles[0][1][2];
        tiles[0][1][2] = tiles[0][2][1];
        tiles[0][2][1] = tiles[0][1][0];
        tiles[0][1][0] = temp;
        // Rotate the adjacent faces.
        // Corners.
        temp = tiles[4][2][0];
        tiles[4][2][0] = tiles[1][0][0];
        tiles[1][0][0] = tiles[5][0][2];
        tiles[5][0][2] = tiles[3][2][2];
        tiles[3][2][2] = temp;
        temp = tiles[4][2][2];
        tiles[4][2][2] = tiles[1][2][0];
        tiles[1][2][0] = tiles[5][0][0];
        tiles[5][0][0] = tiles[3][0][2];
        tiles[3][0][2] = temp;
        // Edges.
        temp = tiles[4][2][1];
        tiles[4][2][1] = tiles[1][1][0];
        tiles[1][1][0] = tiles[5][0][1];
        tiles[5][0][1] = tiles[3][1][2];
        tiles[3][1][2] = temp;
    }

    // Rotate the back face clockwise.
    private void rotateBackClockwise() {
        // Rotate the back face.
        // Back face corner tiles.
        int temp = tiles[2][0][0];
        tiles[2][0][0] = tiles[2][2][0];
        tiles[2][2][0] = tiles[2][2][2];
        tiles[2][2][2] = tiles[2][0][2];
        tiles[2][0][2] = temp;
        // Back face edge tiles.
        temp = tiles[2][0][1];
        tiles[2][0][1] = tiles[2][1][0];
        tiles[2][1][0] = tiles[2][2][1];
        tiles[2][2][1] = tiles[2][1][2];
        tiles[2][1][2] = temp;
        // Rotate the adjacent faces.
        // Corners.
        temp = tiles[4][0][2];
        tiles[4][0][2] = tiles[1][2][2];
        tiles[1][2][2] = tiles[5][2][0];
        tiles[5][2][0] = tiles[3][0][0];
        tiles[3][0][0] = temp;
        temp = tiles[4][0][0];
        tiles[4][0][0] = tiles[1][0][2];
        tiles[1][0][2] = tiles[5][2][2];
        tiles[5][2][2] = tiles[3][2][0];
        tiles[3][2][0] = temp;
        // Edges.
        temp = tiles[4][0][1];
        tiles[4][0][1] = tiles[1][1][2];
        tiles[1][1][2] = tiles[5][2][1];
        tiles[5][2][1] = tiles[3][1][0];
        tiles[3][1][0] = temp;
    }

    // Rotate the back face anti-clockwise.
    private void rotateBackAntiClockwise() {
        // Rotate the back face.
        // Back face corner tiles.
        int temp = tiles[2][0][0];
        tiles[2][0][0] = tiles[2][0][2];
        tiles[2][0][2] = tiles[2][2][2];
        tiles[2][2][2] = tiles[2][2][0];
        tiles[2][2][0] = temp;
        // Back face edge tiles.
        temp = tiles[2][0][1];
        tiles[2][0][1] = tiles[2][1][2];
        tiles[2][1][2] = tiles[2][2][1];
        tiles[2][2][1] = tiles[2][1][0];
        tiles[2][1][0] = temp;
        // Rotate the adjacent faces.
        // Corners.
        temp = tiles[4][0][2];
        tiles[4][0][2] = tiles[3][0][0];
        tiles[3][0][0] = tiles[5][2][0];
        tiles[5][2][0] = tiles[1][2][2];
        tiles[1][2][2] = temp;
        temp = tiles[4][0][0];
        tiles[4][0][0] = tiles[3][2][0];
        tiles[3][2][0] = tiles[5][2][2];
        tiles[5][2][2] = tiles[1][0][2];
        tiles[1][0][2] = temp;
        // Edges.
        temp = tiles[4][0][1];
        tiles[4][0][1] = tiles[3][1][0];
        tiles[3][1][0] = tiles[5][2][1];
        tiles[5][2][1] = tiles[1][1][2];
        tiles[1][1][2] = temp;
    }

    // Rotate the left face clockwise.
    private void rotateLeftClockwise() {
        // Rotate the left face.
        // Left face corner tiles.
        int temp = tiles[3][0][0];
        tiles[3][0][0] = tiles[3][2][0];
        tiles[3][2][0] = tiles[3][2][2];
        tiles[3][2][2] = tiles[3][0][2];
        tiles[3][0][2] = temp;
        // Left face edge tiles.
        temp = tiles[3][0][1];
        tiles[3][0][1] = tiles[3][1][0];
        tiles[3][1][0] = tiles[3][2][1];
        tiles[3][2][1] = tiles[3][1][2];
        tiles[3][1][2] = temp;
        // Rotate the adjacent faces.
        // Corners.
        temp = tiles[4][0][0];
        tiles[4][0][0] = tiles[2][2][2];
        tiles[2][2][2] = tiles[5][0][0];
        tiles[5][0][0] = tiles[0][0][0];
        tiles[0][0][0] = temp;
        temp = tiles[4][2][0];
        tiles[4][2][0] = tiles[2][0][2];
        tiles[2][0][2] = tiles[5][2][0];
        tiles[5][2][0] = tiles[0][2][0];
        tiles[0][2][0] = temp;
        // Edges.
        temp = tiles[4][1][0];
        tiles[4][1][0] = tiles[2][1][2];
        tiles[2][1][2] = tiles[5][1][0];
        tiles[5][1][0] = tiles[0][1][0];
        tiles[0][1][0] = temp;

    }

    // Rotate the left face anti-clockwise.
    private void rotateLeftAntiClockwise() {
        // Rotate the left face.
        // Left face corner tiles.
        int temp = tiles[3][0][0];
        tiles[3][0][0] = tiles[3][0][2];
        tiles[3][0][2] = tiles[3][2][2];
        tiles[3][2][2] = tiles[3][2][0];
        tiles[3][2][0] = temp;
        // Left face edge tiles.
        temp = tiles[3][0][1];
        tiles[3][0][1] = tiles[3][1][2];
        tiles[3][1][2] = tiles[3][2][1];
        tiles[3][2][1] = tiles[3][1][0];
        tiles[3][1][0] = temp;
        // Rotate the adjacent faces.
        // Corners.
        temp = tiles[4][0][0];
        tiles[4][0][0] = tiles[0][0][0];
        tiles[0][0][0] = tiles[5][0][0];
        tiles[5][0][0] = tiles[2][2][2];
        tiles[2][2][2] = temp;
        temp = tiles[4][2][0];
        tiles[4][2][0] = tiles[0][2][0];
        tiles[0][2][0] = tiles[5][2][0];
        tiles[5][2][0] = tiles[2][0][2];
        tiles[2][0][2] = temp;
        // Edges.
        temp = tiles[4][1][0];
        tiles[4][1][0] = tiles[0][1][0];
        tiles[0][1][0] = tiles[5][1][0];
        tiles[5][1][0] = tiles[2][1][2];
        tiles[2][1][2] = temp;
    }

    // Rotate the right face clockwise.
    private void rotateRightClockwise() {
        // Rotate the right face.
        // Right face corner tiles.
        int temp = tiles[1][0][0];
        tiles[1][0][0] = tiles[1][2][0];
        tiles[1][2][0] = tiles[1][2][2];
        tiles[1][2][2] = tiles[1][0][2];
        tiles[1][0][2] = temp;
        // Right face edge tiles.
        temp = tiles[1][0][1];
        tiles[1][0][1] = tiles[1][1][0];
        tiles[1][1][0] = tiles[1][2][1];
        tiles[1][2][1] = tiles[1][1][2];
        tiles[1][1][2] = temp;
        // Rotate the adjacent faces.
        // Corners.
        temp = tiles[4][2][2];
        tiles[4][2][2] = tiles[0][2][2];
        tiles[0][2][2] = tiles[5][2][2];
        tiles[5][2][2] = tiles[2][0][0];
        tiles[2][0][0] = temp;
        temp = tiles[4][0][2];
        tiles[4][0][2] = tiles[0][0][2];
        tiles[0][0][2] = tiles[5][0][2];
        tiles[5][0][2] = tiles[2][2][0];
        tiles[2][2][0] = temp;
        // Edges.
        temp = tiles[4][1][2];
        tiles[4][1][2] = tiles[0][1][2];
        tiles[0][1][2] = tiles[5][1][2];
        tiles[5][1][2] = tiles[2][1][0];
        tiles[2][1][0] = temp;
    }

    // Rotate the right face anti-clockwise.
    private void rotateRightAntiClockwise() {
        // Rotate the right face.
        // Right face corner tiles.
        int temp = tiles[1][0][0];
        tiles[1][0][0] = tiles[1][0][2];
        tiles[1][0][2] = tiles[1][2][2];
        tiles[1][2][2] = tiles[1][2][0];
        tiles[1][2][0] = temp;
        // Right face edge tiles.
        temp = tiles[1][0][1];
        tiles[1][0][1] = tiles[1][1][2];
        tiles[1][1][2] = tiles[1][2][1];
        tiles[1][2][1] = tiles[1][1][0];
        tiles[1][1][0] = temp;
        // Rotate the adjacent faces.
        // Corners.
        temp = tiles[4][2][2];
        tiles[4][2][2] = tiles[2][0][0];
        tiles[2][0][0] = tiles[5][2][2];
        tiles[5][2][2] = tiles[0][2][2];
        tiles[0][2][2] = temp;
        temp = tiles[4][0][2];
        tiles[4][0][2] = tiles[2][2][0];
        tiles[2][2][0] = tiles[5][0][2];
        tiles[5][0][2] = tiles[0][0][2];
        tiles[0][0][2] = temp;
        // Edges.
        temp = tiles[4][1][2];
        tiles[4][1][2] = tiles[2][1][0];
        tiles[2][1][0] = tiles[5][1][2];
        tiles[5][1][2] = tiles[0][1][2];
        tiles[0][1][2] = temp;
    }

    // Rotate the top face clockwise.
    private void rotateUpClockwise() {
        // Rotate the top face.
        // Top face corner tiles.
        int temp = tiles[4][0][0];
        tiles[4][0][0] = tiles[4][2][0];
        tiles[4][2][0] = tiles[4][2][2];
        tiles[4][2][2] = tiles[4][0][2];
        tiles[4][0][2] = temp;
        // Top face edge tiles.
        temp = tiles[4][0][1];
        tiles[4][0][1] = tiles[4][1][0];
        tiles[4][1][0] = tiles[4][2][1];
        tiles[4][2][1] = tiles[4][1][2];
        tiles[4][1][2] = temp;
        // Rotate the adjacent faces.
        // Corners.
        temp = tiles[2][0][2];
        tiles[2][0][2] = tiles[3][0][2];
        tiles[3][0][2] = tiles[0][0][2];
        tiles[0][0][2] = tiles[1][0][2];
        tiles[1][0][2] = temp;
        temp = tiles[2][0][0];
        tiles[2][0][0] = tiles[3][0][0];
        tiles[3][0][0] = tiles[0][0][0];
        tiles[0][0][0] = tiles[1][0][0];
        tiles[1][0][0] = temp;
        // Edges.
        temp = tiles[2][0][1];
        tiles[2][0][1] = tiles[3][0][1];
        tiles[3][0][1] = tiles[0][0][1];
        tiles[0][0][1] = tiles[1][0][1];
        tiles[1][0][1] = temp;
    }

    // Rotate the top face anti-clockwise.
    private void rotateUpAntiClockwise() {
        // Rotate the top face.
        // Top face corner tiles.
        int temp = tiles[4][0][0];
        tiles[4][0][0] = tiles[4][0][2];
        tiles[4][0][2] = tiles[4][2][2];
        tiles[4][2][2] = tiles[4][2][0];
        tiles[4][2][0] = temp;
        // Top face edge tiles.
        temp = tiles[4][0][1];
        tiles[4][0][1] = tiles[4][1][2];
        tiles[4][1][2] = tiles[4][2][1];
        tiles[4][2][1] = tiles[4][1][0];
        tiles[4][1][0] = temp;
        // Rotate the adjacent faces.
        // Corners.
        temp = tiles[2][0][2];
        tiles[2][0][2] = tiles[1][0][2];
        tiles[1][0][2] = tiles[0][0][2];
        tiles[0][0][2] = tiles[3][0][2];
        tiles[3][0][2] = temp;
        temp = tiles[2][0][0];
        tiles[2][0][0] = tiles[1][0][0];
        tiles[1][0][0] = tiles[0][0][0];
        tiles[0][0][0] = tiles[3][0][0];
        tiles[3][0][0] = temp;
        // Edges.
        temp = tiles[2][0][1];
        tiles[2][0][1] = tiles[1][0][1];
        tiles[1][0][1] = tiles[0][0][1];
        tiles[0][0][1] = tiles[3][0][1];
        tiles[3][0][1] = temp;
    }

    // Rotate the bottom face clockwise.
    private void rotateDownClockwise() {
        // Rotate the bottom face.
        // Bottom face corner tiles.
        int temp = tiles[5][0][0];
        tiles[5][0][0] = tiles[5][2][0];
        tiles[5][2][0] = tiles[5][2][2];
        tiles[5][2][2] = tiles[5][0][2];
        tiles[5][0][2] = temp;
        // Bottom face edge tiles.
        temp = tiles[5][0][1];
        tiles[5][0][1] = tiles[5][1][0];
        tiles[5][1][0] = tiles[5][2][1];
        tiles[5][2][1] = tiles[5][1][2];
        tiles[5][1][2] = temp;
        // Rotate the adjacent faces.
        // Corners.
        temp = tiles[0][2][0];
        tiles[0][2][0] = tiles[3][2][0];
        tiles[3][2][0] = tiles[2][2][0];
        tiles[2][2][0] = tiles[1][2][0];
        tiles[1][2][0] = temp;
        temp = tiles[0][2][2];
        tiles[0][2][2] = tiles[3][2][2];
        tiles[3][2][2] = tiles[2][2][2];
        tiles[2][2][2] = tiles[1][2][2];
        tiles[1][2][2] = temp;
        // Edges.
        temp = tiles[0][2][1];
        tiles[0][2][1] = tiles[3][2][1];
        tiles[3][2][1] = tiles[2][2][1];
        tiles[2][2][1] = tiles[1][2][1];
        tiles[1][2][1] = temp;
    }

    // Rotate the bottom face anti-clockwise.
    private void rotateDownAntiClockwise() {
        // Rotate the bottom face.
        // Bottom face corner tiles.
        int temp = tiles[5][0][0];
        tiles[5][0][0] = tiles[5][0][2];
        tiles[5][0][2] = tiles[5][2][2];
        tiles[5][2][2] = tiles[5][2][0];
        tiles[5][2][0] = temp;
        // Bottom face edge tiles.
        temp = tiles[5][0][1];
        tiles[5][0][1] = tiles[5][1][2];
        tiles[5][1][2] = tiles[5][2][1];
        tiles[5][2][1] = tiles[5][1][0];
        tiles[5][1][0] = temp;
        // Rotate the adjacent faces.
        // Corners.
        temp = tiles[0][2][0];
        tiles[0][2][0] = tiles[1][2][0];
        tiles[1][2][0] = tiles[2][2][0];
        tiles[2][2][0] = tiles[3][2][0];
        tiles[3][2][0] = temp;
        temp = tiles[0][2][2];
        tiles[0][2][2] = tiles[1][2][2];
        tiles[1][2][2] = tiles[2][2][2];
        tiles[2][2][2] = tiles[3][2][2];
        tiles[3][2][2] = temp;
        // Edges.
        temp = tiles[0][2][1];
        tiles[0][2][1] = tiles[1][2][1];
        tiles[1][2][1] = tiles[2][2][1];
        tiles[2][2][1] = tiles[3][2][1];
        tiles[3][2][1] = temp;
    }

    @Override
    public void F_l() {
        rotateFrontAntiClockwise();
    }

    @Override
    public void F_r() {
        rotateFrontClockwise();
    }

    @Override
    public void B_l() {
        rotateBackAntiClockwise();
    }

    @Override
    public void B_r() {
        rotateBackClockwise();
    }

    @Override
    public void U_l() {
        rotateUpAntiClockwise();
    }

    @Override
    public void U_r() {
        rotateUpClockwise();
    }

    @Override
    public void D_l() {
        rotateDownAntiClockwise();
    }

    @Override
    public void D_r() {
        rotateDownClockwise();
    }

    @Override
    public void L_l() {
        rotateLeftAntiClockwise();
    }

    @Override
    public void L_r() {
        rotateLeftClockwise();
    }

    @Override
    public void R_l() {
        rotateRightAntiClockwise();
    }

    @Override
    public void R_r() {
        rotateRightClockwise();
    }

    @Override
    // Print the cube in a human-readable format, with every face in relative position to each other.
    public void printCube() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------\n");
        sb.append("\t\t Top\n");
       sb.append("\t\t");
       sb.append(tiles[4][0][0]).append(" ").append(tiles[4][0][1]).append(" ").append(tiles[4][0][2]).append("\n");
         sb.append("\t\t");
        sb.append(tiles[4][1][0]).append(" ").append(tiles[4][1][1]).append(" ").append(tiles[4][1][2]).append("\n");
        sb.append("Left\t").append(tiles[4][2][0]).append(" ").append(tiles[4][2][1]).append(" ").append(tiles[4][2][2]).append("\t").append("Right\tBack\n");
        sb.append(tiles[3][0][0]).append(" ").append(tiles[3][0][1]).append(" ").append(tiles[3][0][2]).append("\t");
        sb.append(tiles[0][0][0]).append(" ").append(tiles[0][0][1]).append(" ").append(tiles[0][0][2]).append("\t");
        sb.append(tiles[1][0][0]).append(" ").append(tiles[1][0][1]).append(" ").append(tiles[1][0][2]).append("\t");
        sb.append(tiles[2][0][0]).append(" ").append(tiles[2][0][1]).append(" ").append(tiles[2][0][2]).append("\n");
        sb.append(tiles[3][1][0]).append(" ").append(tiles[3][1][1]).append(" ").append(tiles[3][1][2]).append("\t");
        sb.append(tiles[0][1][0]).append(" ").append(tiles[0][1][1]).append(" ").append(tiles[0][1][2]).append("\t");
        sb.append(tiles[1][1][0]).append(" ").append(tiles[1][1][1]).append(" ").append(tiles[1][1][2]).append("\t");
        sb.append(tiles[2][1][0]).append(" ").append(tiles[2][1][1]).append(" ").append(tiles[2][1][2]).append("\n");
        sb.append(tiles[3][2][0]).append(" ").append(tiles[3][2][1]).append(" ").append(tiles[3][2][2]).append("\t");
        sb.append(tiles[0][2][0]).append(" ").append(tiles[0][2][1]).append(" ").append(tiles[0][2][2]).append("\t");
        sb.append(tiles[1][2][0]).append(" ").append(tiles[1][2][1]).append(" ").append(tiles[1][2][2]).append("\t");
        sb.append(tiles[2][2][0]).append(" ").append(tiles[2][2][1]).append(" ").append(tiles[2][2][2]).append("\n");
        sb.append("\t\t");
        sb.append(tiles[5][0][0]).append(" ").append(tiles[5][0][1]).append(" ").append(tiles[5][0][2]).append("\n");
        sb.append("\t\t");
        sb.append(tiles[5][1][0]).append(" ").append(tiles[5][1][1]).append(" ").append(tiles[5][1][2]).append("\n");
        sb.append("\t\t");
        sb.append(tiles[5][2][0]).append(" ").append(tiles[5][2][1]).append(" ").append(tiles[5][2][2]).append("\n");
        sb.append("\t\tBottom\n");
        sb.append("---------------------\n");


        System.out.println(sb);

    }
}
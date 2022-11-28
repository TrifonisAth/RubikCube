package cube;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Cube implements RubikCube, Comparable<Cube> {

    // The cube is represented as a 3x3x3 array of integers.
    // Each integer represents a color.
    private int[][][] tiles;
    // Heuristic score.
    private int score;
    // Number of correct faces needed to solve the cube.
    private int K = 6;
    // The parent state.
    private Cube parent = null;
    // Map with the faces of the cube from 1 to 6.
    private static final Map<Integer, String> faces = new HashMap<>();
    // Solved cube.
    private static final int[][][] solved = {
            {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}},
            {{2, 2, 2}, {2, 2, 2}, {2, 2, 2}},
            {{3, 3, 3}, {3, 3, 3}, {3, 3, 3}},
            {{4, 4, 4}, {4, 4, 4}, {4, 4, 4}},
            {{5, 5, 5}, {5, 5, 5}, {5, 5, 5}},
            {{6, 6, 6}, {6, 6, 6}, {6, 6, 6}}
    };

    static {
        faces.put(1, "Front");
        faces.put(2, "Right");
        faces.put(3, "Back");
        faces.put(4, "Left");
        faces.put(5, "Top");
        faces.put(6, "Bottom");
    }


    public Cube(int num) {
        // Initialize the cube to a solved state.
        tiles = new int[6][3][3];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    tiles[i][j][k] = i + 1;
                }
            }
        }
        // Set the number of correct faces needed to solve the cube.
        this.K = num;
    }

    public Cube(){
        this(6);
    }

    // Copy constructor.
    public Cube(int[][][] tiles) {
        this.setTiles(tiles);
    }

    @Override
    public int compareTo(Cube s) {
        return Double.compare(this.score, s.score); // compare based on the heuristic score.
    }

    // Check if the cube is solved.
    @Override
    public boolean isSolved() {
        if (this.K < 6) return isKSolved();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (tiles[i][j][k] != solved[i][j][k]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Check if K faces are correct.
    @Override
    public boolean isKSolved() {
        boolean[] checked = new boolean[6];
        Arrays.fill(checked, true);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                if (checked[i]) {
                    for (int k = 0; k < 3; k++) {
                        if (checked[i] && tiles[i][j][k] != solved[i][j][k]) {
                            checked[i] = false;
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < 6; i++) {
            if (checked[i]) {
                count++;
            }
        }
        return count >= K;
    }

    public Cube getParent() {
        return parent;
    }

    void setParent(Cube parent) {
        this.parent = parent;
    }

    int getScore() {
        return score;
    }

    void setScore(int score) {
        this.score = score;
    }

    int[][][] getTiles() {
        return this.tiles;
    }

    void setTiles(int[][][] tiles) {
        this.tiles = tiles;
    }

    public ArrayList<Cube> getChildren() {
        ArrayList<Cube> children = new ArrayList<>();
        // Create a copy of the current cube.
        Cube child = new Cube(this.getTiles());
        // Rotate the faces of the cube evaluate the distance, set the parent and add the child to the list.
        child.F_r();
        child.countDistance();
        child.setParent(this);
        children.add(child);
        child = new Cube(this.getTiles());
        child.F_l();
        child.countDistance();
        child.setParent(this);
        children.add(child);
        child = new Cube(this.getTiles());
        child.R_r();
        child.countDistance();
        child.setParent(this);
        children.add(child);
        child = new Cube(this.getTiles());
        child.R_l();
        child.countDistance();
        child.setParent(this);
        children.add(child);
        child = new Cube(this.getTiles());
        child.B_r();
        child.countDistance();
        child.setParent(this);
        children.add(child);
        child = new Cube(this.getTiles());
        child.B_l();
        child.countDistance();
        child.setParent(this);
        children.add(child);
        child = new Cube(this.getTiles());
        child.L_r();
        child.countDistance();
        child.setParent(this);
        children.add(child);
        child = new Cube(this.getTiles());
        child.L_l();
        child.countDistance();
        child.setParent(this);
        children.add(child);
        child = new Cube(this.getTiles());
        child.U_r();
        child.countDistance();
        child.setParent(this);
        children.add(child);
        child = new Cube(this.getTiles());
        child.U_l();
        child.countDistance();
        child.setParent(this);
        children.add(child);
        child = new Cube(this.getTiles());
        child.D_r();
        child.countDistance();
        child.setParent(this);
        children.add(child);
        child = new Cube(this.getTiles());
        child.D_l();
        child.countDistance();
        child.setParent(this);
        children.add(child);

        return children;
    }

    // Calculate distance for every move that the tile needs to get to the correct place.
    private void countDistance() {
        // TODO: maybe divide the distance by 4...
        int distance = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    // Skip the center tile.
                    if (j==1 && k==1) continue;
                    int tileA = tiles[i][j][k];
                    int tileB = solved[i][j][k];
                    if (tileA != tileB) {
                        if (tileA == 1 && tileB == 3 || tileA == 3 && tileB == 1 || tileA == 2 && tileB == 4 || tileA == 4 && tileB == 2
                                || tileA == 5 && tileB == 6 || tileA == 6 && tileB == 5) {
                            distance += 2;
                        } else ++distance;
                    }
                }
            }
        }
        this.setScore(distance);
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

    // Print the cube in a human-readable format, with every face in relative position to each other.
    @Override
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
package cube;

import java.util.*;

public class Cube implements RubikCube, Comparable<Cube> {

    // The cube is represented as a 3x3x3 array of integers.
    // Each integer represents a color.
    private int[][][] tiles;
    // Heuristic score.
    private double score;
    // Number of correct faces needed to solve the cube.
    private int K;
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
        this.tiles = new int[6][3][3];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    this.tiles[i][j][k] = i + 1;
                }
            }
        }
        // Set the number of correct faces needed to solve the cube.
        this.K = num;
    }

    public Cube() {
        this(6);
    }

    // Copy constructor.
    public Cube(int[][][] tiles) {
        this.setTiles(tiles);
    }

    public Cube(int[][][] tiles, int counter, int k) {
        this.setTiles(tiles);
        this.score = counter;
        this.K = k;
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
                        if (checked[i] && this.tiles[i][j][k] != solved[i][j][k]) {
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
        return this.parent;
    }

    void setParent(Cube parent) {
        this.parent = parent;
    }

    double getScore() {
        return score;
    }

    void setScore(double score) {
        this.score = score;
    }

    int[][][] getTiles() {
        return this.tiles;
    }

    void setTiles(int[][][] tiles) {
        this.tiles = new int[6][3][3];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                System.arraycopy(tiles[i][j], 0, this.tiles[i][j], 0, 3);
            }
        }
    }

    public ArrayList<Cube> getChildren(int counter) {
        ArrayList<Cube> children = new ArrayList<>();
        // Create a copy of the current cube.
        Cube child = new Cube(this.getTiles(), counter, this.K);
        // Rotate the faces of the cube evaluate the distance, set the parent and add the child to the list.
        child.F_r();
        child.countTotalDistance();
        child.setParent(this);
        children.add(child);
        child = new Cube(this.getTiles(), counter, this.K);
        child.F_l();
        child.countTotalDistance();
        child.setParent(this);
        children.add(child);
        child = new Cube(this.getTiles(), counter, this.K);
        child.R_r();
        child.countTotalDistance();
        child.setParent(this);
        children.add(child);
        child = new Cube(this.getTiles(), counter, this.K);
        child.R_l();
        child.countTotalDistance();
        child.setParent(this);
        children.add(child);
        child = new Cube(this.getTiles(), counter, this.K);
        child.B_r();
        child.countTotalDistance();
        child.setParent(this);
        children.add(child);
        child = new Cube(this.getTiles(), counter, this.K);
        child.B_l();
        child.countTotalDistance();
        child.setParent(this);
        children.add(child);
        child = new Cube(this.getTiles(), counter, this.K);
        child.L_r();
        child.countTotalDistance();
        child.setParent(this);
        children.add(child);
        child = new Cube(this.getTiles(), counter, this.K);
        child.L_l();
        child.countTotalDistance();
        child.setParent(this);
        children.add(child);
        child = new Cube(this.getTiles(), counter, this.K);
        child.U_r();
        child.countTotalDistance();
        child.setParent(this);
        children.add(child);
        child = new Cube(this.getTiles(), counter, this.K);
        child.U_l();
        child.countTotalDistance();
        child.setParent(this);
        children.add(child);
        child = new Cube(this.getTiles(), counter, this.K);
        child.D_r();
        child.countTotalDistance();
        child.setParent(this);
        children.add(child);
        child = new Cube(this.getTiles(), counter, this.K);
        child.D_l();
        child.countTotalDistance();
        child.setParent(this);
        children.add(child);

        return children;
    }

    // Calculate distance for every move that the tile needs to get to the correct place.
    public void countTotalDistance() {
        double distance = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    // Skip the center tile.
                    if (j == 1 && k == 1) continue;
                    int tileA = this.tiles[i][j][k];
                    int tileB = solved[i][j][k];
                    distance += getDistance(tileA, tileB);
                }
            }
        }
        this.updateScore(distance);
    }

    // Calculate the sum of the distances for corners and edges.
    public void countTotalDistanceCornersEdges() {
        double distanceCorners = 0;
        double distanceEdges = 0;
        for (int i = 0; i < 6; i++) {
            // Corners.
            int tileA = this.tiles[i][0][0];
            int tileB = solved[i][0][0];
            distanceCorners += getDistance(tileA, tileB);
            tileA = this.tiles[i][0][2];
            tileB = solved[i][0][2];
            distanceCorners += getDistance(tileA, tileB);
            tileA = this.tiles[i][2][0];
            tileB = solved[i][2][0];
            distanceCorners += getDistance(tileA, tileB);
            tileA = this.tiles[i][2][2];
            tileB = solved[i][2][2];
            distanceCorners += getDistance(tileA, tileB);
            // Edges.
            tileA = this.tiles[i][0][1];
            tileB = solved[i][0][1];
            distanceEdges += getDistance(tileA, tileB);
            tileA = this.tiles[i][1][0];
            tileB = solved[i][1][0];
            distanceEdges += getDistance(tileA, tileB);
            tileA = this.tiles[i][1][2];
            tileB = solved[i][1][2];
            distanceEdges += getDistance(tileA, tileB);
            tileA = this.tiles[i][2][1];
            tileB = solved[i][2][1];
            distanceEdges += getDistance(tileA, tileB);
            this.updateScore((distanceCorners + distanceEdges)/ 24);
        }
    }

    // Calculate the distance between two tiles. The distance is the number of moves that the tile needs to get to the correct place.
    private int getDistance(int a, int b) {
        if (a != b) {
            if ((a == 1 && b == 3) || (a == 3 && b == 1) || (a == 2 && b == 4) || (a == 4 && b == 2)
                    || (a == 5 && b == 6) || (a == 6 && b == 5)) {
                return 2;
            }
            return 1;
        }
        return 0;
    }


    private void updateScore(double number) {
        this.score += number;
    }

    // Generate a random cube.
    public void randomize() {
        Random random = new Random();
        int n = random.nextInt(10, 15);
        for (int i = 0; i < n; i++) {
            int m = random.nextInt(12);
            switch (m) {
                case 0 -> F_r();
                case 1 -> F_l();
                case 2 -> R_r();
                case 3 -> R_l();
                case 4 -> B_r();
                case 5 -> B_l();
                case 6 -> L_r();
                case 7 -> L_l();
                case 8 -> U_r();
                case 9 -> U_l();
                case 10 -> D_r();
                case 11 -> D_l();
            }
        }
    }

    // Cube rotation methods.

    // Rotate the front face clockwise.
    private void rotateFrontClockwise() {
        // Rotate the front face.
        // Front face corner tiles.
        int temp = this.tiles[0][0][0];
        this.tiles[0][0][0] = this.tiles[0][2][0];
        this.tiles[0][2][0] = this.tiles[0][2][2];
        this.tiles[0][2][2] = this.tiles[0][0][2];
        this.tiles[0][0][2] = temp;
        // Front face edge this.tiles.
        temp = this.tiles[0][0][1];
        this.tiles[0][0][1] = this.tiles[0][1][0];
        this.tiles[0][1][0] = this.tiles[0][2][1];
        this.tiles[0][2][1] = this.tiles[0][1][2];
        this.tiles[0][1][2] = temp;
        // Rotate the adjacent faces.
        // Corners.
        temp = this.tiles[4][2][0];
        this.tiles[4][2][0] = this.tiles[3][2][2];
        this.tiles[3][2][2] = this.tiles[5][0][2];
        this.tiles[5][0][2] = this.tiles[1][0][0];
        this.tiles[1][0][0] = temp;
        temp = this.tiles[4][2][2];
        this.tiles[4][2][2] = this.tiles[3][0][2];
        this.tiles[3][0][2] = this.tiles[5][0][0];
        this.tiles[5][0][0] = this.tiles[1][2][0];
        this.tiles[1][2][0] = temp;
        // Edges.
        temp = this.tiles[4][2][1];
        this.tiles[4][2][1] = this.tiles[3][1][2];
        this.tiles[3][1][2] = this.tiles[5][0][1];
        this.tiles[5][0][1] = this.tiles[1][1][0];
        this.tiles[1][1][0] = temp;
    }

    // Rotate the front face anti-clockwise.
    private void rotateFrontAntiClockwise() {
        // Rotate the front face.
        // Front face corner tiles.
        int temp = this.tiles[0][0][0];
        this.tiles[0][0][0] = this.tiles[0][0][2];
        this.tiles[0][0][2] = this.tiles[0][2][2];
        this.tiles[0][2][2] = this.tiles[0][2][0];
        this.tiles[0][2][0] = temp;
        // Front face edge this.tiles.
        temp = this.tiles[0][0][1];
        this.tiles[0][0][1] = this.tiles[0][1][2];
        this.tiles[0][1][2] = this.tiles[0][2][1];
        this.tiles[0][2][1] = this.tiles[0][1][0];
        this.tiles[0][1][0] = temp;
        // Rotate the adjacent faces.
        // Corners.
        temp = this.tiles[4][2][0];
        this.tiles[4][2][0] = this.tiles[1][0][0];
        this.tiles[1][0][0] = this.tiles[5][0][2];
        this.tiles[5][0][2] = this.tiles[3][2][2];
        this.tiles[3][2][2] = temp;
        temp = this.tiles[4][2][2];
        this.tiles[4][2][2] = this.tiles[1][2][0];
        this.tiles[1][2][0] = this.tiles[5][0][0];
        this.tiles[5][0][0] = this.tiles[3][0][2];
        this.tiles[3][0][2] = temp;
        // Edges.
        temp = this.tiles[4][2][1];
        this.tiles[4][2][1] = this.tiles[1][1][0];
        this.tiles[1][1][0] = this.tiles[5][0][1];
        this.tiles[5][0][1] = this.tiles[3][1][2];
        this.tiles[3][1][2] = temp;
    }

    // Rotate the back face clockwise.
    private void rotateBackClockwise() {
        // Rotate the back face.
        // Back face corner tiles.
        int temp = this.tiles[2][0][0];
        this.tiles[2][0][0] = this.tiles[2][2][0];
        this.tiles[2][2][0] = this.tiles[2][2][2];
        this.tiles[2][2][2] = this.tiles[2][0][2];
        this.tiles[2][0][2] = temp;
        // Back face edge tiles.
        temp = this.tiles[2][0][1];
        this.tiles[2][0][1] = this.tiles[2][1][0];
        this.tiles[2][1][0] = this.tiles[2][2][1];
        this.tiles[2][2][1] = this.tiles[2][1][2];
        this.tiles[2][1][2] = temp;
        // Rotate the adjacent faces.
        // Corners.
        temp = this.tiles[4][0][2];
        this.tiles[4][0][2] = this.tiles[1][2][2];
        this.tiles[1][2][2] = this.tiles[5][2][0];
        this.tiles[5][2][0] = this.tiles[3][0][0];
        this.tiles[3][0][0] = temp;
        temp = this.tiles[4][0][0];
        this.tiles[4][0][0] = this.tiles[1][0][2];
        this.tiles[1][0][2] = this.tiles[5][2][2];
        this.tiles[5][2][2] = this.tiles[3][2][0];
        this.tiles[3][2][0] = temp;
        // Edges.
        temp = this.tiles[4][0][1];
        this.tiles[4][0][1] = this.tiles[1][1][2];
        this.tiles[1][1][2] = this.tiles[5][2][1];
        this.tiles[5][2][1] = this.tiles[3][1][0];
        this.tiles[3][1][0] = temp;
    }

    // Rotate the back face anti-clockwise.
    private void rotateBackAntiClockwise() {
        // Rotate the back face.
        // Back face corner tiles.
        int temp = this.tiles[2][0][0];
        this.tiles[2][0][0] = this.tiles[2][0][2];
        this.tiles[2][0][2] = this.tiles[2][2][2];
        this.tiles[2][2][2] = this.tiles[2][2][0];
        this.tiles[2][2][0] = temp;
        // Back face edge tiles.
        temp = this.tiles[2][0][1];
        this.tiles[2][0][1] = this.tiles[2][1][2];
        this.tiles[2][1][2] = this.tiles[2][2][1];
        this.tiles[2][2][1] = this.tiles[2][1][0];
        this.tiles[2][1][0] = temp;
        // Rotate the adjacent faces.
        // Corners.
        temp = this.tiles[4][0][2];
        this.tiles[4][0][2] = this.tiles[3][0][0];
        this.tiles[3][0][0] = this.tiles[5][2][0];
        this.tiles[5][2][0] = this.tiles[1][2][2];
        this.tiles[1][2][2] = temp;
        temp = this.tiles[4][0][0];
        this.tiles[4][0][0] = this.tiles[3][2][0];
        this.tiles[3][2][0] = this.tiles[5][2][2];
        this.tiles[5][2][2] = this.tiles[1][0][2];
        this.tiles[1][0][2] = temp;
        // Edges.
        temp = this.tiles[4][0][1];
        this.tiles[4][0][1] = this.tiles[3][1][0];
        this.tiles[3][1][0] = this.tiles[5][2][1];
        this.tiles[5][2][1] = this.tiles[1][1][2];
        this.tiles[1][1][2] = temp;
    }

    // Rotate the left face clockwise.
    private void rotateLeftClockwise() {
        // Rotate the left face.
        // Left face corner tiles.
        int temp = this.tiles[3][0][0];
        this.tiles[3][0][0] = this.tiles[3][2][0];
        this.tiles[3][2][0] = this.tiles[3][2][2];
        this.tiles[3][2][2] = this.tiles[3][0][2];
        this.tiles[3][0][2] = temp;
        // Left face edge tiles.
        temp = this.tiles[3][0][1];
        this.tiles[3][0][1] = this.tiles[3][1][0];
        this.tiles[3][1][0] = this.tiles[3][2][1];
        this.tiles[3][2][1] = this.tiles[3][1][2];
        this.tiles[3][1][2] = temp;
        // Rotate the adjacent faces.
        // Corners.
        temp = this.tiles[4][0][0];
        this.tiles[4][0][0] = this.tiles[2][2][2];
        this.tiles[2][2][2] = this.tiles[5][0][0];
        this.tiles[5][0][0] = this.tiles[0][0][0];
        this.tiles[0][0][0] = temp;
        temp = this.tiles[4][2][0];
        this.tiles[4][2][0] = this.tiles[2][0][2];
        this.tiles[2][0][2] = this.tiles[5][2][0];
        this.tiles[5][2][0] = this.tiles[0][2][0];
        this.tiles[0][2][0] = temp;
        // Edges.
        temp = this.tiles[4][1][0];
        this.tiles[4][1][0] = this.tiles[2][1][2];
        this.tiles[2][1][2] = this.tiles[5][1][0];
        this.tiles[5][1][0] = this.tiles[0][1][0];
        this.tiles[0][1][0] = temp;

    }

    // Rotate the left face anti-clockwise.
    private void rotateLeftAntiClockwise() {
        // Rotate the left face.
        // Left face corner tiles.
        int temp = this.tiles[3][0][0];
        this.tiles[3][0][0] = this.tiles[3][0][2];
        this.tiles[3][0][2] = this.tiles[3][2][2];
        this.tiles[3][2][2] = this.tiles[3][2][0];
        this.tiles[3][2][0] = temp;
        // Left face edge tiles.
        temp = this.tiles[3][0][1];
        this.tiles[3][0][1] = this.tiles[3][1][2];
        this.tiles[3][1][2] = this.tiles[3][2][1];
        this.tiles[3][2][1] = this.tiles[3][1][0];
        this.tiles[3][1][0] = temp;
        // Rotate the adjacent faces.
        // Corners.
        temp = this.tiles[4][0][0];
        this.tiles[4][0][0] = this.tiles[0][0][0];
        this.tiles[0][0][0] = this.tiles[5][0][0];
        this.tiles[5][0][0] = this.tiles[2][2][2];
        this.tiles[2][2][2] = temp;
        temp = this.tiles[4][2][0];
        this.tiles[4][2][0] = this.tiles[0][2][0];
        this.tiles[0][2][0] = this.tiles[5][2][0];
        this.tiles[5][2][0] = this.tiles[2][0][2];
        this.tiles[2][0][2] = temp;
        // Edges.
        temp = this.tiles[4][1][0];
        this.tiles[4][1][0] = this.tiles[0][1][0];
        this.tiles[0][1][0] = this.tiles[5][1][0];
        this.tiles[5][1][0] = this.tiles[2][1][2];
        this.tiles[2][1][2] = temp;
    }

    // Rotate the right face clockwise.
    private void rotateRightClockwise() {
        // Rotate the right face.
        // Right face corner tiles.
        int temp = this.tiles[1][0][0];
        this.tiles[1][0][0] = this.tiles[1][2][0];
        this.tiles[1][2][0] = this.tiles[1][2][2];
        this.tiles[1][2][2] = this.tiles[1][0][2];
        this.tiles[1][0][2] = temp;
        // Right face edge tiles.
        temp = this.tiles[1][0][1];
        this.tiles[1][0][1] = this.tiles[1][1][0];
        this.tiles[1][1][0] = this.tiles[1][2][1];
        this.tiles[1][2][1] = this.tiles[1][1][2];
        this.tiles[1][1][2] = temp;
        // Rotate the adjacent faces.
        // Corners.
        temp = this.tiles[4][2][2];
        this.tiles[4][2][2] = this.tiles[0][2][2];
        this.tiles[0][2][2] = this.tiles[5][2][2];
        this.tiles[5][2][2] = this.tiles[2][0][0];
        this.tiles[2][0][0] = temp;
        temp = this.tiles[4][0][2];
        this.tiles[4][0][2] = this.tiles[0][0][2];
        this.tiles[0][0][2] = this.tiles[5][0][2];
        this.tiles[5][0][2] = this.tiles[2][2][0];
        this.tiles[2][2][0] = temp;
        // Edges.
        temp = this.tiles[4][1][2];
        this.tiles[4][1][2] = this.tiles[0][1][2];
        this.tiles[0][1][2] = this.tiles[5][1][2];
        this.tiles[5][1][2] = this.tiles[2][1][0];
        this.tiles[2][1][0] = temp;
    }

    // Rotate the right face anti-clockwise.
    private void rotateRightAntiClockwise() {
        // Rotate the right face.
        // Right face corner tiles.
        int temp = this.tiles[1][0][0];
        this.tiles[1][0][0] = this.tiles[1][0][2];
        this.tiles[1][0][2] = this.tiles[1][2][2];
        this.tiles[1][2][2] = this.tiles[1][2][0];
        this.tiles[1][2][0] = temp;
        // Right face edge tiles.
        temp = this.tiles[1][0][1];
        this.tiles[1][0][1] = this.tiles[1][1][2];
        this.tiles[1][1][2] = this.tiles[1][2][1];
        this.tiles[1][2][1] = this.tiles[1][1][0];
        this.tiles[1][1][0] = temp;
        // Rotate the adjacent faces.
        // Corners.
        temp = this.tiles[4][2][2];
        this.tiles[4][2][2] = this.tiles[2][0][0];
        this.tiles[2][0][0] = this.tiles[5][2][2];
        this.tiles[5][2][2] = this.tiles[0][2][2];
        this.tiles[0][2][2] = temp;
        temp = this.tiles[4][0][2];
        this.tiles[4][0][2] = this.tiles[2][2][0];
        this.tiles[2][2][0] = this.tiles[5][0][2];
        this.tiles[5][0][2] = this.tiles[0][0][2];
        this.tiles[0][0][2] = temp;
        // Edges.
        temp = this.tiles[4][1][2];
        this.tiles[4][1][2] = this.tiles[2][1][0];
        this.tiles[2][1][0] = this.tiles[5][1][2];
        this.tiles[5][1][2] = this.tiles[0][1][2];
        this.tiles[0][1][2] = temp;
    }

    // Rotate the top face clockwise.
    private void rotateUpClockwise() {
        // Rotate the top face.
        // Top face corner tiles.
        int temp = this.tiles[4][0][0];
        this.tiles[4][0][0] = this.tiles[4][2][0];
        this.tiles[4][2][0] = this.tiles[4][2][2];
        this.tiles[4][2][2] = this.tiles[4][0][2];
        this.tiles[4][0][2] = temp;
        // Top face edge tiles.
        temp = this.tiles[4][0][1];
        this.tiles[4][0][1] = this.tiles[4][1][0];
        this.tiles[4][1][0] = this.tiles[4][2][1];
        this.tiles[4][2][1] = this.tiles[4][1][2];
        this.tiles[4][1][2] = temp;
        // Rotate the adjacent faces.
        // Corners.
        temp = this.tiles[2][0][2];
        this.tiles[2][0][2] = this.tiles[3][0][2];
        this.tiles[3][0][2] = this.tiles[0][0][2];
        this.tiles[0][0][2] = this.tiles[1][0][2];
        this.tiles[1][0][2] = temp;
        temp = this.tiles[2][0][0];
        this.tiles[2][0][0] = this.tiles[3][0][0];
        this.tiles[3][0][0] = this.tiles[0][0][0];
        this.tiles[0][0][0] = this.tiles[1][0][0];
        this.tiles[1][0][0] = temp;
        // Edges.
        temp = this.tiles[2][0][1];
        this.tiles[2][0][1] = this.tiles[3][0][1];
        this.tiles[3][0][1] = this.tiles[0][0][1];
        this.tiles[0][0][1] = this.tiles[1][0][1];
        this.tiles[1][0][1] = temp;
    }

    // Rotate the top face anti-clockwise.
    private void rotateUpAntiClockwise() {
        // Rotate the top face.
        // Top face corner tiles.
        int temp = this.tiles[4][0][0];
        this.tiles[4][0][0] = this.tiles[4][0][2];
        this.tiles[4][0][2] = this.tiles[4][2][2];
        this.tiles[4][2][2] = this.tiles[4][2][0];
        this.tiles[4][2][0] = temp;
        // Top face edge tiles.
        temp = this.tiles[4][0][1];
        this.tiles[4][0][1] = this.tiles[4][1][2];
        this.tiles[4][1][2] = this.tiles[4][2][1];
        this.tiles[4][2][1] = this.tiles[4][1][0];
        this.tiles[4][1][0] = temp;
        // Rotate the adjacent faces.
        // Corners.
        temp = this.tiles[2][0][2];
        this.tiles[2][0][2] = this.tiles[1][0][2];
        this.tiles[1][0][2] = this.tiles[0][0][2];
        this.tiles[0][0][2] = this.tiles[3][0][2];
        this.tiles[3][0][2] = temp;
        temp = this.tiles[2][0][0];
        this.tiles[2][0][0] = this.tiles[1][0][0];
        this.tiles[1][0][0] = this.tiles[0][0][0];
        this.tiles[0][0][0] = this.tiles[3][0][0];
        this.tiles[3][0][0] = temp;
        // Edges.
        temp = this.tiles[2][0][1];
        this.tiles[2][0][1] = this.tiles[1][0][1];
        this.tiles[1][0][1] = this.tiles[0][0][1];
        this.tiles[0][0][1] = this.tiles[3][0][1];
        this.tiles[3][0][1] = temp;
    }

    // Rotate the bottom face clockwise.
    private void rotateDownClockwise() {
        // Rotate the bottom face.
        // Bottom face corner tiles.
        int temp = this.tiles[5][0][0];
        this.tiles[5][0][0] = this.tiles[5][2][0];
        this.tiles[5][2][0] = this.tiles[5][2][2];
        this.tiles[5][2][2] = this.tiles[5][0][2];
        this.tiles[5][0][2] = temp;
        // Bottom face edge tiles.
        temp = this.tiles[5][0][1];
        this.tiles[5][0][1] = this.tiles[5][1][0];
        this.tiles[5][1][0] = this.tiles[5][2][1];
        this.tiles[5][2][1] = this.tiles[5][1][2];
        this.tiles[5][1][2] = temp;
        // Rotate the adjacent faces.
        // Corners.
        temp = this.tiles[0][2][0];
        this.tiles[0][2][0] = this.tiles[3][2][0];
        this.tiles[3][2][0] = this.tiles[2][2][0];
        this.tiles[2][2][0] = this.tiles[1][2][0];
        this.tiles[1][2][0] = temp;
        temp = this.tiles[0][2][2];
        this.tiles[0][2][2] = this.tiles[3][2][2];
        this.tiles[3][2][2] = this.tiles[2][2][2];
        this.tiles[2][2][2] = this.tiles[1][2][2];
        this.tiles[1][2][2] = temp;
        // Edges.
        temp = this.tiles[0][2][1];
        this.tiles[0][2][1] = this.tiles[3][2][1];
        this.tiles[3][2][1] = this.tiles[2][2][1];
        this.tiles[2][2][1] = this.tiles[1][2][1];
        this.tiles[1][2][1] = temp;
    }

    // Rotate the bottom face anti-clockwise.
    private void rotateDownAntiClockwise() {
        // Rotate the bottom face.
        // Bottom face corner tiles.
        int temp = this.tiles[5][0][0];
        this.tiles[5][0][0] = this.tiles[5][0][2];
        this.tiles[5][0][2] = this.tiles[5][2][2];
        this.tiles[5][2][2] = this.tiles[5][2][0];
        this.tiles[5][2][0] = temp;
        // Bottom face edge tiles.
        temp = this.tiles[5][0][1];
        this.tiles[5][0][1] = this.tiles[5][1][2];
        this.tiles[5][1][2] = this.tiles[5][2][1];
        this.tiles[5][2][1] = this.tiles[5][1][0];
        this.tiles[5][1][0] = temp;
        // Rotate the adjacent faces.
        // Corners.
        temp = this.tiles[0][2][0];
        this.tiles[0][2][0] = this.tiles[1][2][0];
        this.tiles[1][2][0] = this.tiles[2][2][0];
        this.tiles[2][2][0] = this.tiles[3][2][0];
        this.tiles[3][2][0] = temp;
        temp = this.tiles[0][2][2];
        this.tiles[0][2][2] = this.tiles[1][2][2];
        this.tiles[1][2][2] = this.tiles[2][2][2];
        this.tiles[2][2][2] = this.tiles[3][2][2];
        this.tiles[3][2][2] = temp;
        // Edges.
        temp = this.tiles[0][2][1];
        this.tiles[0][2][1] = this.tiles[1][2][1];
        this.tiles[1][2][1] = this.tiles[2][2][1];
        this.tiles[2][2][1] = this.tiles[3][2][1];
        this.tiles[3][2][1] = temp;
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
        sb.append(this.tiles[4][0][0]).append(" ").append(this.tiles[4][0][1]).append(" ").append(this.tiles[4][0][2]).append("\n");
        sb.append("\t\t");
        sb.append(this.tiles[4][1][0]).append(" ").append(this.tiles[4][1][1]).append(" ").append(this.tiles[4][1][2]).append("\n");
        sb.append("Left\t").append(this.tiles[4][2][0]).append(" ").append(this.tiles[4][2][1]).append(" ").append(this.tiles[4][2][2]).append("\t").append("Right\tBack\n");
        sb.append(this.tiles[3][0][0]).append(" ").append(this.tiles[3][0][1]).append(" ").append(this.tiles[3][0][2]).append("\t");
        sb.append(this.tiles[0][0][0]).append(" ").append(this.tiles[0][0][1]).append(" ").append(this.tiles[0][0][2]).append("\t");
        sb.append(this.tiles[1][0][0]).append(" ").append(this.tiles[1][0][1]).append(" ").append(this.tiles[1][0][2]).append("\t");
        sb.append(this.tiles[2][0][0]).append(" ").append(this.tiles[2][0][1]).append(" ").append(this.tiles[2][0][2]).append("\n");
        sb.append(this.tiles[3][1][0]).append(" ").append(this.tiles[3][1][1]).append(" ").append(this.tiles[3][1][2]).append("\t");
        sb.append(this.tiles[0][1][0]).append(" ").append(this.tiles[0][1][1]).append(" ").append(this.tiles[0][1][2]).append("\t");
        sb.append(this.tiles[1][1][0]).append(" ").append(this.tiles[1][1][1]).append(" ").append(this.tiles[1][1][2]).append("\t");
        sb.append(this.tiles[2][1][0]).append(" ").append(this.tiles[2][1][1]).append(" ").append(this.tiles[2][1][2]).append("\n");
        sb.append(this.tiles[3][2][0]).append(" ").append(this.tiles[3][2][1]).append(" ").append(this.tiles[3][2][2]).append("\t");
        sb.append(this.tiles[0][2][0]).append(" ").append(this.tiles[0][2][1]).append(" ").append(this.tiles[0][2][2]).append("\t");
        sb.append(this.tiles[1][2][0]).append(" ").append(this.tiles[1][2][1]).append(" ").append(this.tiles[1][2][2]).append("\t");
        sb.append(this.tiles[2][2][0]).append(" ").append(this.tiles[2][2][1]).append(" ").append(this.tiles[2][2][2]).append("\n");
        sb.append("\t\t");
        sb.append(this.tiles[5][0][0]).append(" ").append(this.tiles[5][0][1]).append(" ").append(this.tiles[5][0][2]).append("\n");
        sb.append("\t\t");
        sb.append(this.tiles[5][1][0]).append(" ").append(this.tiles[5][1][1]).append(" ").append(this.tiles[5][1][2]).append("\n");
        sb.append("\t\t");
        sb.append(this.tiles[5][2][0]).append(" ").append(this.tiles[5][2][1]).append(" ").append(this.tiles[5][2][2]).append("\n");
        sb.append("\t\tBottom\n");
        sb.append("---------------------\n");
        System.out.println(sb);
    }
}
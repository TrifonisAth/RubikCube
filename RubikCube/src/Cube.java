public class Cube implements RubikCube {

    private final Corner[] corners = new Corner[8];
    private final Edge[] edges = new Edge[12];
    private final char[] center = new char[6];

    public Cube() {
        this.initCube();
    }

    // Initialize the cube.
    private void initCube() {
        // Initialize corners.
        corners[0] = new Corner(new char[]{'B', 'U', 'L'}, 0);
        corners[1] = new Corner(new char[]{'R', 'U', 'B'}, 1);
        corners[2] = new Corner(new char[]{'L', 'U', 'F'}, 2);
        corners[3] = new Corner(new char[]{'F', 'U', 'R'}, 3);
        corners[4] = new Corner(new char[]{'B', 'L', 'D'}, 4);
        corners[5] = new Corner(new char[]{'D', 'R', 'B'}, 5);
        corners[6] = new Corner(new char[]{'L',  'F', 'D'}, 6);
        corners[7] = new Corner(new char[]{'D', 'F', 'R'}, 7);
        // Initialize edges.
        edges[0] = new Edge(new char[]{'U', 'L'}, 0);
        edges[1] = new Edge(new char[]{'U', 'R'}, 1);
        edges[2] = new Edge(new char[]{'U', 'B'}, 2);
        edges[3] = new Edge(new char[]{'U', 'F'}, 3);
        edges[4] = new Edge(new char[]{'B', 'L'}, 4);
        edges[5] = new Edge(new char[]{'R', 'B'}, 5);
        edges[6] = new Edge(new char[]{'L', 'F'}, 6);
        edges[7] = new Edge(new char[]{'R', 'F'}, 7);
        edges[8] = new Edge(new char[]{'D', 'L'}, 8);
        edges[9] = new Edge(new char[]{'D', 'R'}, 9);
        edges[10] = new Edge(new char[]{'D', 'B'}, 10);
        edges[11] = new Edge(new char[]{'D', 'F'}, 11);
        // Initialize center.
        center[0] = 'F';
        center[1] = 'R';
        center[2] = 'B';
        center[3] = 'L';
        center[4] = 'U';
        center[5] = 'D';
    }

    private String getTop(){
        StringBuilder top = new StringBuilder();
        char arr[] = new char[10];

        arr[9] = center[4];
    }

    public String getFront(){
        char arr[] = new char[10];
        for (int i = 0; i < 4; i++) {
            if (corners[i].getOrientation() == 0) arr[i] = corners[i].getTiles()[2];
            else if (corners[i].getOrientation() == 1) arr[i] = corners[i].getTiles()[1];
            else arr[i] = corners[i].getTiles()[0];
        }
    }

    private Cube(Cube cube) {
        for (int i = 0; i < 8; i++) {
            corners[i] = new Corner(cube.corners[i]);
        }
        for (int i = 0; i < 12; i++) {
            edges[i] = new Edge(cube.edges[i]);
        }
        System.arraycopy(cube.center, 0, center, 0, 6);
    }

    private void frontClockwise() {
        // Rotate corners.
        Corner temp = corners[2];
        corners[2] = corners[6];
        corners[6] = corners[7];
        corners[7] = corners[3];
        corners[3] = temp;
        // Rotate edges.
        Edge tempEdge = edges[6];
        edges[6] = edges[11];
        edges[11] = edges[7];
        edges[7] = edges[3];
        edges[3] = tempEdge;
        // Update corner orientation.
        corners[2].rotateClockwise();
        corners[3].rotateClockwise();
        corners[6].rotateClockwise();
        corners[7].rotateClockwise();
    }

    private void frontCounterClockwise() {
        // Rotate corners.
        Corner temp = corners[2];
        corners[2] = corners[3];
        corners[3] = corners[7];
        corners[7] = corners[6];
        corners[6] = temp;
        // Rotate edges.
        Edge tempEdge = edges[6];
        edges[6] = edges[3];
        edges[3] = edges[7];
        edges[7] = edges[11];
        edges[11] = tempEdge;
        // Update corner orientation.
        corners[2].rotateCounterClockwise();
        corners[3].rotateCounterClockwise();
        corners[6].rotateCounterClockwise();
        corners[7].rotateCounterClockwise();
    }

    private void backClockwise() {
        // Rotate corners.
        Corner temp = corners[0];
        corners[0] = corners[1];
        corners[1] = corners[5];
        corners[5] = corners[4];
        corners[4] = temp;
        // Rotate edges.
        Edge tempEdge = edges[5];
        edges[5] = edges[10];
        edges[10] = edges[4];
        edges[4] = edges[2];
        edges[2] = tempEdge;
        // Update corner orientation.
        corners[0].rotateClockwise();
        corners[1].rotateClockwise();
        corners[4].rotateClockwise();
        corners[5].rotateClockwise();
    }

    private void backCounterClockwise() {
        // Rotate corners.
        Corner temp = corners[0];
        corners[0] = corners[4];
        corners[4] = corners[5];
        corners[5] = corners[1];
        corners[1] = temp;
        // Rotate edges.
        Edge tempEdge = edges[5];
        edges[5] = edges[2];
        edges[2] = edges[4];
        edges[4] = edges[10];
        edges[10] = tempEdge;
        // Update corner orientation.
        corners[0].rotateCounterClockwise();
        corners[1].rotateCounterClockwise();
        corners[4].rotateCounterClockwise();
        corners[5].rotateCounterClockwise();
    }

    private void upClockwise() {
        // Rotate corners.
        Corner temp = corners[0];
        corners[0] = corners[2];
        corners[2] = corners[3];
        corners[3] = corners[1];
        corners[1] = temp;
        // Rotate edges.
        Edge tempEdge = edges[0];
        edges[0] = edges[3];
        edges[3] = edges[1];
        edges[1] = edges[2];
        edges[2] = tempEdge;
        // Flip edge orientation.
        edges[0].flipOrientation();
        edges[1].flipOrientation();
        edges[2].flipOrientation();
        edges[3].flipOrientation();
    }

    private void upCounterClockwise() {
        // Rotate corners.
        Corner temp = corners[0];
        corners[0] = corners[1];
        corners[1] = corners[3];
        corners[3] = corners[2];
        corners[2] = temp;
        // Rotate edges.
        Edge tempEdge = edges[0];
        edges[0] = edges[2];
        edges[2] = edges[1];
        edges[1] = edges[3];
        edges[3] = tempEdge;
        // Flip edge orientation.
        edges[0].flipOrientation();
        edges[1].flipOrientation();
        edges[2].flipOrientation();
        edges[3].flipOrientation();

    }

    private void downClockwise() {
        // Rotate corners.
        Corner temp = corners[4];
        corners[4] = corners[5];
        corners[5] = corners[7];
        corners[7] = corners[6];
        corners[6] = temp;
        // Rotate edges.
        Edge tempEdge = edges[8];
        edges[8] = edges[10];
        edges[10] = edges[9];
        edges[9] = edges[11];
        edges[11] = tempEdge;
        // Flip edge orientation.
        edges[8].flipOrientation();
        edges[9].flipOrientation();
        edges[10].flipOrientation();
        edges[11].flipOrientation();
    }

    private void downCounterClockwise() {
        // Rotate corners.
        Corner temp = corners[4];
        corners[4] = corners[6];
        corners[6] = corners[7];
        corners[7] = corners[5];
        corners[5] = temp;
        // Rotate edges.
        Edge tempEdge = edges[8];
        edges[8] = edges[11];
        edges[11] = edges[9];
        edges[9] = edges[10];
        edges[10] = tempEdge;
        // Flip edge orientation.
        edges[8].flipOrientation();
        edges[9].flipOrientation();
        edges[10].flipOrientation();
        edges[11].flipOrientation();
    }

    private void leftClockwise() {
        // Rotate corners.
        Corner temp = corners[0];
        corners[0] = corners[4];
        corners[4] = corners[6];
        corners[6] = corners[2];
        corners[2] = temp;
        // Rotate edges.
        Edge tempEdge = edges[0];
        edges[0] = edges[4];
        edges[4] = edges[8];
        edges[8] = edges[6];
        edges[6] = tempEdge;
        // Update corner orientation.
        corners[0].rotateClockwise();
        corners[2].rotateClockwise();
        corners[4].rotateClockwise();
        corners[6].rotateClockwise();
    }

    private void leftCounterClockwise() {
        // Rotate corners.
        Corner temp = corners[0];
        corners[0] = corners[2];
        corners[2] = corners[6];
        corners[6] = corners[4];
        corners[4] = temp;
        // Rotate edges.
        Edge tempEdge = edges[0];
        edges[0] = edges[6];
        edges[6] = edges[8];
        edges[8] = edges[4];
        edges[4] = tempEdge;
        // Update corner orientation.
        corners[0].rotateCounterClockwise();
        corners[2].rotateCounterClockwise();
        corners[4].rotateCounterClockwise();
        corners[6].rotateCounterClockwise();
    }

    private void rightClockwise() {
        // Rotate corners.
        Corner temp = corners[1];
        corners[1] = corners[3];
        corners[3] = corners[7];
        corners[7] = corners[5];
        corners[5] = temp;
        // Rotate edges.
        Edge tempEdge = edges[1];
        edges[1] = edges[7];
        edges[7] = edges[9];
        edges[9] = edges[5];
        edges[5] = tempEdge;
        // Update corner orientation.
        corners[1].rotateClockwise();
        corners[3].rotateClockwise();
        corners[5].rotateClockwise();
        corners[7].rotateClockwise();
    }

    private void rightCounterClockwise() {
        // Rotate corners.
        Corner temp = corners[1];
        corners[1] = corners[5];
        corners[5] = corners[7];
        corners[7] = corners[3];
        corners[3] = temp;
        // Rotate edges.
        Edge tempEdge = edges[1];
        edges[1] = edges[5];
        edges[5] = edges[9];
        edges[9] = edges[7];
        edges[7] = tempEdge;
        // Update corner orientation.
        corners[1].rotateCounterClockwise();
        corners[3].rotateCounterClockwise();
        corners[5].rotateCounterClockwise();
        corners[7].rotateCounterClockwise();
    }

    private String getUpFace() {
        String upFace = "";
        return upFace;
    }

    @Override
    public void printCube() {
        StringBuilder sb = new StringBuilder();
        sb.append("    ").append("-------- UP --------\n");


    }

//    // Print cube sides.
//    public void printCube() {
//        StringBuilder str = new StringBuilder();
//        str.append("      -------- TOP --------\n");
//        for (int i = 0; i < size; i++) {
//            str.append("\t".repeat(size / 2 + 1).concat("\t").concat(Arrays.toString(getTop().getRow(i))));
//            str.append("\n");
//        }
//        str.append("\n");
//        for (int i = 0; i < size; i++) {
//            str.append(Arrays.toString(getLeft().getRow(i))).append("\t");
//            str.append(Arrays.toString(getFront().getRow(i))).append("\t");
//            str.append(Arrays.toString(getRight().getRow(i))).append("\t");
//            str.append(Arrays.toString(getBack().getRow(i)));
//            str.append("\n");
//        }
//        str.append("\n");
//        for (int i = 0; i < size; i++) {
//            str.append("\t".repeat(size / 2 + 1).concat("\t").concat(Arrays.toString(getBottom().getRow(i))));
//            str.append("\n");
//        }
//        str.append("      ------ BOTTOM -------");
//        System.out.println(str);
//    }
//
//    public void printCube2() {
//        StringBuilder str = new StringBuilder();
//        str.append("      -------- TOP --------\n");
//        for (int i = 0; i < size; i++) {
//            str.append("\t".repeat(size / 2 + 1).concat("\t").concat(String.valueOf(squares[36 + i * 3])).concat(String.valueOf(squares[36 + i * 3 + 1])).concat(String.valueOf(squares[36 + i * 3 + 2])));
//            str.append("\n");
//        }
//        str.append("\n");
//        for (int i = 0; i < size; i++) {
//            str.append(squares[27 + i * 3]).append(squares[27 + i * 3 + 1]).append(squares[27 + i * 3 + 2]).append("\t");
//            str.append(squares[27 + i * 3]).append(squares[27 + i * 3 + 1]).append(squares[27 + i * 3 + 2]).append("\t");
//            str.append(squares[27 + i * 3]).append(squares[27 + i * 3 + 1]).append(squares[27 + i * 3 + 2]).append("\t");
//            str.append(Arrays.toString(getBack().getRow(i)));
//            str.append("\n");
//        }
//        str.append("\n");
//        for (int i = 0; i < size; i++) {
//            str.append("\t".repeat(size / 2 + 1).concat("\t").concat(Arrays.toString(getBottom().getRow(i))));
//            str.append("\n");
//        }
//        str.append("      ------ BOTTOM -------");
//        System.out.println(str);
//    }


    @Override
    public void F_l() {
        frontCounterClockwise();
    }

    @Override
    public void F_r() {
        frontClockwise();
    }

    @Override
    public void B_l() {
        backCounterClockwise();
    }

    @Override
    public void B_r() {
        backClockwise();
    }

    @Override
    public void U_l() {
        upCounterClockwise();
    }

    @Override
    public void U_r() {
        upClockwise();
    }

    @Override
    public void D_l() {
        downCounterClockwise();
    }

    @Override
    public void D_r() {
        downClockwise();
    }

    @Override
    public void L_l() {
        leftCounterClockwise();
    }

    @Override
    public void L_r() {
        leftClockwise();
    }

    @Override
    public void R_l() {
        rightCounterClockwise();
    }

    @Override
    public void R_r() {
        rightClockwise();
    }

}

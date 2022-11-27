public class RubikCube implements Cube {

    private final Corner[] corners = new Corner[8];
    private final Edge[] edges = new Edge[12];
    private final char[] center = new char[6];

    public RubikCube() {
        this.initCube();
    }

    private void initCube(){
        corners[0] = new Corner(new char[]{'U', 'L', 'B'});
        corners[1] = new Corner(new char[]{'U', 'R', 'B'});
        corners[2] = new Corner(new char[]{'U', 'L', 'F'});
        corners[3] = new Corner(new char[]{'U', 'R', 'F'});
        corners[4] = new Corner(new char[]{'D', 'L', 'B'});
        corners[5] = new Corner(new char[]{'D', 'R', 'B'});
        corners[6] = new Corner(new char[]{'D', 'L', 'F'});
        corners[7] = new Corner(new char[]{'D', 'R', 'F'});

        edges[0] = new Edge(new char[]{'U', 'L'});
        edges[1] = new Edge(new char[]{'U', 'R'});
        edges[2] = new Edge(new char[]{'U', 'B'});
        edges[3] = new Edge(new char[]{'U', 'F'});
        edges[4] = new Edge(new char[]{'L', 'B'});
        edges[5] = new Edge(new char[]{'R', 'B'});
        edges[6] = new Edge(new char[]{'L', 'F'});
        edges[7] = new Edge(new char[]{'R', 'F'});
        edges[8] = new Edge(new char[]{'D', 'L'});
        edges[9] = new Edge(new char[]{'D', 'R'});
        edges[10] = new Edge(new char[]{'D', 'B'});
        edges[11] = new Edge(new char[]{'D', 'F'});

        center[0] = 'F';
        center[1] = 'R';
        center[2] = 'B';
        center[3] = 'L';
        center[4] = 'U';
        center[5] = 'D';
    }

    private RubikCube(RubikCube cube) {
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
    }



//    public RubikSide getFront() {
//        return this.front;
//    }
//
//    public RubikSide getBack() {
//        return this.back;
//    }
//
//    public RubikSide getLeft() {
//        return this.left;
//    }
//
//    public RubikSide getRight() {
//        return this.right;
//    }
//
//    public RubikSide getTop() {
//        return this.top;
//    }
//
//    public RubikSide getBottom() {
//        return this.bottom;
//    }
//
//    public int getSize() {
//        return this.size;
//    }


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
       upCounterClockwise();
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

    @Override
    public void printCube() {

    }

}

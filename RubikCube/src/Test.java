public class Test {
    private int testCount;
    private int testPassed;
    private int testFailed;
    private Cube cube;

        public void init(int size) {
            this.cube = new Cube(size);

        }

        public void testRotation() {
            System.out.println("Testing front clockwise rotation");
            cube.printCube();
            cube.F_r();
            cube.F_r();
            cube.F_r();
            cube.B_r();
            cube.B_r();
            cube.B_r();
            cube.L_l();
            cube.L_l();
            cube.L_l();
            cube.R_l();
            cube.R_l();
            cube.R_l();
            cube.U_l();
            cube.U_r();
            cube.D_l();
            cube.D_l();
            cube.F_r();
            cube.printCube();
        }

        public void testBackRot(){
            System.out.println("Testing backside rotation");
            cube.printCube();
        }

        public void testMidRot() {
            System.out.println("Testing Mid Rotation");
        }

        public void testCompositeRot() {
            System.out.println("Testing back -> mid -> collumn rotations");
        }

    public void printData() {

    }
}

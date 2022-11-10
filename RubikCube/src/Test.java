public class Test {
    private int testCount;
    private int testPassed;
    private int testFailed;
    private RubikCube cube;

        public void init(int size) {
            this.cube = new RubikCube(size);

        }

        public void testRotation() {
            cube.printCube();
            System.out.println("Testing clockwise rotation");
            cube.turnRow(2, -1);
            cube.printCube();
        }

        public void testBackRot(){
            System.out.println("Testing backside rotation");
            cube.rotateBackSide(1);
            cube.printCube();
            cube.rotateBackSide(-1);
            cube.printCube();
        }

        public void testMidRot() {
            System.out.println("Testing Mid Rotation");
            cube.rotateMid(1);
            cube.printCube();
            cube.rotateMid(1);
            cube.printCube();
            cube.rotateMid(1);
        }

        public void testCompositeRot() {
            System.out.println("Testing back -> mid -> collumn rotations");
            cube.rotateMid(-1);
            cube.printCube();
            cube.rotateBackSide(-1);
            cube.printCube();
            cube.turnCol(2,-1);
        }
}

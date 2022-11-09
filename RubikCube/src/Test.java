public class Test {
    private int testCount;
    private int testPassed;
    private int testFailed;
    private RubikCube cube;

        public void init(int size) {
            this.cube = new RubikCube(size);

        }

        public void testRotation() {
            System.out.println("Testing clockwise rotation");
            cube.turnRow(2, -1);
            cube.printCube();
        }

        public void testRotationCol(){
            System.out.println("Testing column rotation");
            cube.turnRow(2, -1);
            cube.printCube();
            cube.turnCol(0, 1);
            cube.printCube();
            cube.getBottom().printData();
        }
}

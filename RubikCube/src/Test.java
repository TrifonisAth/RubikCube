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
            cube.turnRow(0, -1);
            cube.printCube();
        }
}

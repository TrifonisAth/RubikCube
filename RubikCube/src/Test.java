public class Test {
    private int testCount;
    private int testPassed;
    private int testFailed;
    private RubikCube cube;

        public void init(int size) {
            this.cube = new RubikCube(size);
            cube.getFront().fill();
            cube.getFront().printData();
        }

        public void testRotation() {
            System.out.println("Testing clockwise rotation");
            cube.getFront().rotateClockwise();
            cube.getFront().printData();
        }
}

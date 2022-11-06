public class Test {
    private int testCount;
    private int testPassed;
    private int testFailed;


        public void init(int size) {
            RubikCube cube = new RubikCube(size);
            cube.getFront().fill();
        }

        public void testSideTurnRow() {
        }
}

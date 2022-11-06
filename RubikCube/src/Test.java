public class Test {
    private int testCount;
    private int testPassed;
    private int testFailed;


        public void init(int size) {
            RubikCube cube = new RubikCube(size);
            for (int i = 0; i < size; i++) {
                for (int j = 1; j <= size; j+=){

                }
            }
            cube.getFront().setColumn(0, new int[]{1,4,7});
            cube.getFront().setColumn(1, new int[]{2,5,8});
            cube.getFront().setColumn(2, new int[]{3,6,9});
            cube.getFront().setRow(0, new int[]{1,2,3});
            cube.getFront().setRow(1, new int[]{4,5,6});
            cube.getFront().setRow(2, new int[]{7,8,9});
            cube.getFront().getData();
        }

        public void testSideTurnRow() {
        }
}

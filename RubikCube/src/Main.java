public class Main {
    public static void main(String[] args) {
        Test test = new Test();
        test.init(3);
        test.testRotation();
        RubikCube cube = new RubikCube(3);
        cube.printCube();
    }
}
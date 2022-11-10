public class Main {
    public static void main(String[] args) {
        Test test = new Test();

        test.init(3);
        test.testRotation();

        test.init(3);
        test.testMidRot();

        test.init(3);
        test.testBackRot();

        test.init(3);
        test.testCompositeRot();
    }
}
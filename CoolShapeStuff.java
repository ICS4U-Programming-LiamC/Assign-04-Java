public class CoolShapeStuff {
  public static void main(String[] args) {
    Triangle test = new Triangle();
    // test.printTrangle();

    // test = new Triangle(9, 12, 15);
    // test.printTrangle();

    // test = new Triangle(6, 8, 10);
    // test.printTrangle();

    System.out.println();
    test = new Triangle(7, 10, 4);
    test.printTrangle();
    System.out.println();

    System.out.println();
    test = new Triangle(3, 4, 5);
    test.printTrangle();
    System.out.println();
  }
}

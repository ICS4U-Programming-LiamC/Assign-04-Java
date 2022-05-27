public class CoolShapeStuff {
  public static void main(String[] args) {
    Triangle test = new Triangle();
    test.printTrangle();

    test = new Triangle(9, 12, 15);
    test.printTrangle();
  }
}

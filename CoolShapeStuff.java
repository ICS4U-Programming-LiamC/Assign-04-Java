public class CoolShapeStuff {
  public static void main(String[] args) {

    System.out.println("Disclaimers: all triangles are stretched vertically.");
    System.out.println("This is due to the difference in height and width of chars");
    System.out.println("Sides are just a guide, they are limited in accuracy by char size");
    System.out.println("Some of the side lines are also just off due to some bugs in the code");
    System.out.println("The dots down the middle of the triangle show these bugs, they also represent the height line");
    System.out.println("When triangles get too small the printer does not work properly");
    System.out.println("Same thing if you make two sides long and one really short, it wont print properly");
    System.out.println("Other disclaimers go here");

    Triangle test = new Triangle();

    System.out.println();
    test = new Triangle(40, 30, 20);
    test.printTrangle();
    System.out.println();

    System.out.println();
    test = new Triangle(20, 31, 15);
    test.printTrangle();
    System.out.println();

    System.out.println();
    test = new Triangle(24, 25, 23);
    test.printTrangle();
    System.out.println();
    
    System.out.println();
    test = new Triangle();
    test.printTrangle();
    System.out.println();
  }
}

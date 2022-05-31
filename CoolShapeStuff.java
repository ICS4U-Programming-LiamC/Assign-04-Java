import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class CoolShapeStuff {
  public static void main(String[] args) {

    System.out.println("Disclaimers: all triangles are stretched vertically.");
    System.out.println("This is due to the difference in height and width of chars");
    System.out.println("Sides are just a guide, they are limited in accuracy by char size");
    System.out.println("Some of the side lines are also just off due to some bugs in the code");
    System.out.println("The dots down the middle of the triangle show these bugs, they also represent the height line");
    System.out.println("When triangles get too small the printer does not work properly");
    System.out.println("Same thing if you make two sides long and one really short, it wont print properly");
    System.out.println("The code will always set the longest side to the bottom, side a will be on the left and b on the right");
    System.out.println("a will also always be the shortest. Except in a right angle triangle, then it will put the 90* angle at the bottom");
    System.out.println("Other disclaimers go here\n\n");

    Triangle test = new Triangle();
    Scanner scan = new Scanner(System.in);
    String userInput;
    List<Double> sideLengths = new ArrayList<Double>();
    List<Double> angles = new ArrayList<Double>();
    Double side;
    Double angle;

    System.out.println("Create a triangle");
    System.out.println("You must input at least 1 side");
    System.out.println("To stop inputing side lengths input _ or 0");
    while (sideLengths.size() < 3) {
      System.out.println("Side: ");
      userInput = scan.nextLine();
      if (userInput.equals("_")) {
        if (sideLengths.size() == 0) {
          System.out.println("You must input at least 1 side");
        } else {
          break;
        }
      } else {
        try {
          side = Double.parseDouble(userInput);
          if (side > 0) {
            sideLengths.add(side);
          } else if (side == 0) {
            if (sideLengths.size() == 0) {
              System.out.println("You must input at least 1 side");
            } else {
              break;
            }
          } else {
            System.out.println("Please input a positive number for the side length");
          }
        } catch (NumberFormatException e) {
          System.out.println("Please input a positive number for the side length");
        }
      }
    }

    System.out.println("You inputed " + sideLengths.size() + " sides");
    if (sideLengths.size() == 1) {
      System.out.println("The program will ask for 3 angles, A, B, and C");
      System.out.println("You must input at least 2, A and B are attached to the side you inputed");
      System.out.println("C is opposite the side you inputed. Type _ or 0 for no angle here");

      // getting angle A
      while(true) {
        System.out.print("Input angle A: ");
        userInput = scan.nextLine();
        if (userInput.equals("_")) {
          System.out.println("No angle for angle A, you must input angles for both B and C");
        } else {
          try {
            angle = Double.parseDouble(userInput);
            if (angle > 0) {
              angles.add(angle);
            } else if (angle == 0) {
              if (sideLengths.size() == 0) {
                System.out.println("No angle for angle A, you must input angles for both B and C");
                break;
              }
            } else {
              System.out.println("Please input a positive number for the angle");
            }
          } catch (NumberFormatException e) {
            System.out.println("Please input a positive number for the angle");
          }
        }
      }

      // getting angle B
      System.out.print("Input angle B: ");
      while(true) {
        userInput = scan.nextLine();
        if (userInput.equals("_") && angles.size() == 1) {
          System.out.println("No angle for angle B, you must input an angle for C");
        } else if (userInput.equals("_")) {
          System.out.println("You did not input an angle for A, you must input an angle for B");
        } else {
          try {
            angle = Double.parseDouble(userInput);
            if (angle > 0) {
              angles.add(angle);
            } else if (angle == 0) {
              if (angles.size() == 1) {
                System.out.println("No angle for angle B, you must input an angle for C");
                break;
              } else {
                System.out.println("You did not input an angle for A, you must input an angle for B");
              }
            } else {
              System.out.println("Please input a positive number for the angle");
            }
          } catch (NumberFormatException e) {
            System.out.println("Please input a positive number for the angle");
          }
        }
      }

      if (angles.size() == 2) {
        System.out.println("Your third angle is " + (180 - angles.get(0) - angles.get(1)));
        angles.add(180 - angles.get(0) - angles.get(1));
        test = new Triangle(sideLengths.get(0), angles.get(0), angles.get(1));
        test.printTriangle();
      } else {
        System.out.println("You must input an angle for C");
        while (true) {
          System.out.print("Input angle C: ");
          userInput = scan.nextLine();
          try {
            angle = Double.parseDouble(userInput);
            if (angle > 0) {
              angles.add(angle);
              test = new Triangle(sideLengths.get(0), angles.get(0), (180 - angles.get(0) - angles.get(1)));
              test.printTriangle();
            } else {
              System.out.println("Please input a positive number for the angle");
            }
          } catch (NumberFormatException e) {
            System.out.println("Please input a positive number for the angle");
          }
        }
      }
    } else if (sideLengths.size() == 2) {
      System.out.println("You will need to input 1 angle");
      System.out.println("Input a if you want the angle between the two sides");
      System.out.println("Input b if you want the angle attatched to the first input only");
      System.out.println("Input c if you want the angle attatched to the last input only");
      while (true) {
        userInput = scan.nextLine().toLowerCase();
        if (userInput.equals("a") || userInput.equals("b") || userInput.equals("c")) {
          break;
        } else {
          System.out.println("You must input a, b, or c");
        }
      }
      String inputedAngle;
      Double doubleOfAngle;
      while(true) {
        System.out.print("Please input the angle: ");
        inputedAngle = scan.nextLine();
        try {
          doubleOfAngle = Double.parseDouble(inputedAngle);
          if (doubleOfAngle <= 0) {
            System.out.println("You must input a positive non-zero number");
          } else {
            if (userInput.equals("a")) {
              test = new Triangle(sideLengths.get(0), sideLengths.get(1), doubleOfAngle, "a");
              test.printTriangle();
              break;
            } else if (userInput.equals("b")) {
              test = new Triangle(sideLengths.get(0), sideLengths.get(1), doubleOfAngle, "b");
              test.printTriangle();
              break;
            } else {
              test = new Triangle(sideLengths.get(0), sideLengths.get(1), doubleOfAngle, "c");
              test.printTriangle();
              break;
            }
          }
        } catch (NumberFormatException e) {
          System.out.println("You must input a positive non-zero number");
        }
      }
    } else if (sideLengths.size() == 3) {
      System.out.println("No need for any angles!");
      test = new Triangle(sideLengths.get(0), sideLengths.get(1), sideLengths.get(2));
      test.printTriangle();
    }
  }
}

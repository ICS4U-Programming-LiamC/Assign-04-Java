import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * CoolShapeStuff.java is a program which gets a triangle from the user
 * it then creates a triangle from the triangle class and prints
 * various information about the triangle
 *
 * @author Liam Csiffary
 * @version 1.0
 * @since 2022-06-01
 */


public class CoolShapeStuff {
  /**
   * Mosly just gets the information to create a triangle from the triangle
   * class, then it calls the print function from the triangle class.
   */
  public static void main(String[] args) {

    // disclaimers 
    System.out.println("Disclaimers: all triangles are stretched vertically.");
    System.out.println("This is due to the difference in height and width of chars");
    System.out.println("Sides are just a guide, they are limited in accuracy by char size");
    System.out.println("Some of the side lines are also just off due to some bugs in the code");
    System.out.println("The dots down the middle of the triangle show these bugs,"
        + "they also represent the height line");
    System.out.println("When triangles get too small the printer does not work properly");
    System.out.println("Same thing if you make two sides long and one really short,"
        + "it wont print properly");
    System.out.println("The code will always set the longest side to the bottom,"
        + "side a will be on the left and b on the right");
    System.out.println("a will also always be the shortest. Except in a right angle triangle,"
        + "then it will put the 90* angle at the bottom");
    System.out.println("Other disclaimers go here\n\n");

    // defines the base variables
    Triangle test = new Triangle();
    Scanner scan = new Scanner(System.in);
    String userInput;
    List<Double> sideLengths = new ArrayList<Double>();
    List<Double> angles = new ArrayList<Double>();
    Double side;
    Double angle;

    // explains how to use the program
    System.out.println("Create a triangle");
    System.out.println("You must input at least 1 side");
    System.out.println("To stop inputing side lengths input _ or 0");
    while (sideLengths.size() < 3) {

      // gets at least 1 side from the user
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

    // tells the user how many side they inputted and gets a a number of angles depending
    // on how many sides there are
    System.out.println("You inputed " + sideLengths.size() + " sides");
    if (sideLengths.size() == 1) {
      // explains the program, needs to input at least 2 angles to calculate the triangle
      System.out.println("The program will ask for 3 angles, A, B, and C");
      System.out.println("You must input at least 2, A and B are attached to the side you inputed");
      System.out.println("C is opposite the side you inputed. Type _ or 0 for no angle here");

      // getting angle A, makes sure that the angle is a number
      while (true) {
        System.out.print("Input angle A: ");
        userInput = scan.nextLine();
        if (userInput.equals("_")) {
          System.out.println("No angle for angle A, you must input angles for both B and C");
          break;
        } else {
          try {
            angle = Double.parseDouble(userInput);
            if (angle > 0) {
              angles.add(angle);
              break;
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

      // getting angle B, if no angle A they must input angleB and C
      System.out.print("Input angle B: ");
      while (true) {
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
              break;
            } else if (angle == 0) {
              if (angles.size() == 1) {
                System.out.println("No angle for angle B, you must input an angle for C");
                break;
              } else {
                System.out.println(
                    "You did not input an angle for A, you must input an angle for B");
              }
            } else {
              System.out.println("Please input a positive number for the angle");
            }
          } catch (NumberFormatException e) {
            System.out.println("Please input a positive number for the angle");
          }
        }
      }

      // calculates the third angle if they inputted 2 angles otherwise get angleC
      if (angles.size() == 2) {
        System.out.println("Your third angle is " + (180 - angles.get(0) - angles.get(1)));
        angles.add(180 - angles.get(0) - angles.get(1));
        test = new Triangle(sideLengths.get(0), angles.get(0), angles.get(1), 0);
        test.printTriangle();
        System.exit(0);
      } else {
        System.out.println("You must input an angle for C");
        while (true) {
          System.out.print("Input angle C: ");
          userInput = scan.nextLine();
          try {
            angle = Double.parseDouble(userInput);
            if (angle > 0) {
              angles.add(angle);

              // once we have both angles and the side length we can generate the triangle
              // and then print the information to the console
              test = new Triangle(
                  sideLengths.get(0), angles.get(0), (180 - angles.get(0) - angles.get(1)));
              test.printTriangle();
              System.exit(0);
            } else {
              System.out.println("Please input a positive number for the angle");
            }
          } catch (NumberFormatException e) {
            System.out.println("Please input a positive number for the angle");
          }
        }
      }

      // if the user inputed 2 side lengths we need at least 1 angle and we need to 
      // know where this angle is
    } else if (sideLengths.size() == 2) {
      System.out.println("You will need to input 1 angle");
      System.out.println("Input a if you want the angle between the two sides");
      System.out.println("Input b if you want the angle attatched to the first input only");
      System.out.println("Input c if you want the angle attatched to the last input only");

      // gets the location of the angle
      while (true) {
        userInput = scan.nextLine().toLowerCase();
        if (userInput.equals("a") || userInput.equals("b") || userInput.equals("c")) {
          break;
        } else {
          System.out.println("You must input a, b, or c");
        }
      }

      // gets the angle
      String inputedAngle;
      Double doubleOfAngle;
      while (true) {
        System.out.print("Please input the angle: ");
        inputedAngle = scan.nextLine();
        try {
          doubleOfAngle = Double.parseDouble(inputedAngle);
          if (doubleOfAngle <= 0) {
            System.out.println("You must input a positive non-zero number");
          } else {
            // depending on where the angle is send the arguments to the constructor
            test = new Triangle(sideLengths.get(0), sideLengths.get(1), doubleOfAngle, userInput);
            test.printTriangle();
            System.exit(0);
          }
        } catch (NumberFormatException e) {
          System.out.println("You must input a positive non-zero number");
        }
      }

      // if the user inputted 3 sides then send those sides to the constructor
    } else if (sideLengths.size() == 3) {
      System.out.println("No need for any angles!");
      test = new Triangle(sideLengths.get(0), sideLengths.get(1), sideLengths.get(2));
      test.printTriangle();
      System.exit(0);
    }
    scan.close();
  }
}

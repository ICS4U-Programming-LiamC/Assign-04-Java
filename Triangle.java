import java.lang.Math;

import javax.crypto.spec.IvParameterSpec;

public class Triangle {
  double rToD = 180 / Math.PI;
  double a, b, c, s;
  double angleA, angleB, angleC;
  String triangleType;
  double[] sides = new double[3];
  double[] angles;
  boolean valid;

  public Triangle() {
    sides[0] = 3;
    sides[1] = 4;
    sides[2] = 
    this.a = 3;
    this.b = 4;
    this.c = 5;
    this.s = (a + b + c) / 2;
    this.angles = getAngles(a, b, c);
    this.angleA = angles[0] * rToD;
    this.angleB = angles[1] * rToD;
    this.angleC = angles[2] * rToD;

    valid = isValid();
    this.triangleType = typeOfTriangle();
  }

  public Triangle(double a, double b, double c) {

    this.sides = longestSide(a, b, c);

    this.a = sides[0];
    this.b = sides[1];
    this.c = sides[2];
    this.s = (a + b + c) / 2;
    this.angles = getAngles(a, b, c);
    this.angleA = angles[0] * rToD;
    this.angleB = angles[1] * rToD;
    this.angleC = angles[2] * rToD;

    valid = isValid();
    this.triangleType = typeOfTriangle();
  }

  private boolean isValid() {
    if (a <= 0 && b <= 0 && c <= 0) {
      if (a + b > c) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  public double semiPerimeter() {
    return s;
  }

  public double area() {
    double temp = (s * (s - a) * (s - b) * (s - c));
    return Math.sqrt(temp);
  }

  public double perimeter() {
    return (a + b + c);
  }

  public double[] getAngles(double sideA, double sideB, double sideC) {
    double[] angles = new double[3];
    // returns angles from left to right, or I suppose bottom left, top, then right
    // assumes that bottom is c, left is a, and right is b
    angles[2] = Math.acos((sideB * sideB + sideA * sideA - sideC * sideC) / (2 * sideB * sideA));
    angles[1] = Math.acos((sideA * sideA + sideC * sideC - sideB * sideB) / (2 * sideA * sideC));
    angles[0] = Math.acos((sideB * sideB + sideC * sideC - sideA * sideA) / (2 * sideB * sideC));
    return angles;
  }

  public double[] longestSide() {
    double[] returns = new double[3];
    double biggest;
    double newA;
    double newB;
    if (a > b && a > c) {
      if (b < c) {
        returns[0] = b; returns[1] = c; returns[2] = a;
      } else {
        returns[0] = c; returns[1] = b; returns[2] = a;
      }

    } else if (b > a && b > c) {
      if (a < c) {
        returns[0] = a; returns[1] = c; returns[2] = b;
      } else {
        returns[0] = c; returns[1] = a; returns[2] = b;
      }

    } else {
      if (a < b) {
        returns[0] = a; returns[1] = b; returns[2] = c;
      } else {
        returns[0] = b; returns[1] = a; returns[2] = c;
      }
    }
    return returns;
  }

  public double[] longestSide(double sideA, double sideB, double sideC) {
    double[] returns = new double[3];
    double biggest;
    double newA;
    double newB;
    if (sideA > sideB && sideA > sideC) {
      if (sideB < sideC) {
        returns[0] = sideB; returns[1] = sideC; returns[2] = sideA;
      } else {
        returns[0] = sideC; returns[1] = sideB; returns[2] = sideA;
      }

    } else if (sideB > sideA && sideB > sideC) {
      if (sideA < sideC) {
        returns[0] = sideA; returns[1] = sideC; returns[2] = sideB;
      } else {
        returns[0] = sideC; returns[1] = sideA; returns[2] = sideB;
      }

    } else {
      if (sideA < sideB) {
        returns[0] = sideA; returns[1] = sideB; returns[2] = sideC;
      } else {
        returns[0] = sideB; returns[1] = sideA; returns[2] = sideC;
      }
    }
    return returns;
  }

  public String typeOfTriangle() {
    if (a == b && b == c) {
      return "equilateral";
    } else if (a == b || b == c || c == a) {
      return "isosceles";
    } else {
      double biggest;
      double newA;
      double newB;
      if (a > b && a > c) {
        biggest = a;
        newA = b;
        newB = c;
      } else if (b > a && b > c) {
        biggest = b;
        newA = a;
        newB = c;
      } else {
        biggest = c;
        newA = b;
        newB = a;
      }
      if (biggest == Math.sqrt(Math.pow(newA, 2) + Math.pow(newB, 2))) {
        return "right angle";
      } else {
        return "scalene";
      }
    }
  }

  // default returns the height of a as base
  public double height() {
    double temp = (s * (s - a) * (s - b) * (s - c));
    temp = Math.sqrt(temp);
    return (temp * 2 / a);
  }

  // returns the first input as base
  public double height(double sideA, double sideB, double sideC) {
    double temp = (s * (s - sideA) * (s - sideB) * (s - sideC));
    temp = Math.sqrt(temp);
    return (temp * 2 / sideA);
  }

  // default returns the median of a
  public double median() {
    return Math.sqrt((2 * b * b + 2 * c * c - a * a) / 4);
  }

  // returns the median of the first side you put in
  public double median(double sideA, double sideB, double sideC) {
    return Math.sqrt((2 * sideB * sideB + 2 * sideC * sideC - sideA * sideA) / 4);
  }

  public double inradius() {
    return area() / s;
  }

  public double circumradius() {
    return a / (2 * Math.sin(angleC / rToD));
  }

  public double[] innerCircleCenter(double bottom, double angle1, double angle2) {
    double[] returns = new double[2];
    double angleC = 180 - angle1 - angle2;
    // takes half the angles of A and B and the side c and finds the vertex of that triangle
    double leftSide = bottom * Math.sin(angle1 / rToD) / Math.sin(angleC / rToD);
    double rightSide = bottom * Math.sin(angle2 / rToD) / Math.sin(angleC / rToD);
  
    double area = (leftSide * rightSide * Math.sin(angleC / rToD)) / 2;
    double height = 2 * area / bottom;
  
    double dis = height / Math.tan(angle1 / rToD);
    returns[0] = dis;
    returns[1] = height;
    return returns;
  }

  // finds the middle of the circumstribed circle. Made super easy because the second line is flat
  // this gives us the x coord instantly so all we need is an equation for the other line to find y
  public double[] outerCircleCenter(double aX, double aY, double b2X) {
    double[] returns = new double[2];
    
    // gets the midpoint of line 1
    double midAX = (0 + aX) / 2;
    double midAY = (0 + aY) / 2;

    // line 2 is flat so the midpoint is just the length / 2
    double x = b2X / 2;

    // gets the perpendicular slope at the midpoint of line 1, line two is flat so the perp is infinite
    double mA = (aX) / (aY) * -1;

    // gets the b constant so that we have a full equation
    double bA = midAY - mA * midAX;

    // finds where those 2 lines intersect, this is the middle of the circle
    double y = mA * x + bA;

    returns[0] = x;
    returns[1] = y;

    return returns;
  
  }

  public double[] orthocenter(double x, double y, double hypo) {
    double[] returns = new double[2];
    double m = (x) / (y) * -1;
    double b = -1 * m * hypo;

    double coordX = x;
    double coordY = m * coordX + b;

    returns[0] = coordX;
    returns[1] = coordY;

    return returns;
  }

  public void printTrangle() {
    // takes points A as (0, 0)
    // and B as vertex (?, bases height)
    double aShort = a;
    double bShort = b;
    double hypo = c;
    double[] angles = getAngles(aShort, bShort, hypo);
    double angleA = angles[0] * rToD;
    double angleB = angles[1] * rToD;
    double angleC = angles[2] * rToD;
    double h = aShort;
    double x = 0;

    //////////////////////// RIGHT ANGLE PRINTER //////////////////////////
    if (triangleType.equals("right angle")) {

      // for each in height, which is side a, +2 to fit B and the &'s
      for (int i = 0; i < aShort + 2; i++) {
        // for the top
        if (i == 0) {
          System.out.println("  B\n  &");

          // for the bottom
        } else if (i == aShort + 1) {
          System.out.print("C &");

          // prints all of the underscores, length of b times
          for (int j = 0; j < bShort; j++) {
            if (j == Math.floor(bShort / 2 + 0.5)) {
              System.out.print("b_");
            }
            System.out.print("__");
          }
          // put A and the & on the end of the line
          System.out.println("& A");

        } else {
          // prints the left side of the triangle
          if (i == Math.floor(aShort / 2 + 0.5)) {
            System.out.print("  a");
          } else {
            System.out.print("  |");
          }
          
          // how much to indent each part of the c line
          double totalDelayAmount = b * 2;
          int howMuchDelayEachTime = (int)Math.floor(totalDelayAmount / a + 0.5);

          // prints the spaces based on the delay
          for (int delay = 0; delay < (howMuchDelayEachTime * i) - 2; delay++) {
            System.out.print(" ");
          }
          // when to print the letter c, otherwise print the backslash
          if (i == Math.floor(a / 2 + 0.5)) {
            System.out.println("c");
          } else {
            System.out.println("\\");
          }
        }
      }

      ////////////////////// EVERYTHING ELSE PRINTER ///////////////////////
    } else {
      // gets the height and the distance along line the hypotenuse to the base of the height
      h = height(hypo, aShort, bShort);
      x = Math.sqrt((aShort * aShort) - (h * h));
      int height = (int)h;
      // gets the angles of the triangle
      double timesIndented;
      double increment;

      // for each line in the height of the triangle
      for (int i = 0; i <= height; i++) {
        timesIndented = 0;
        if (i == 0) {
          for (int j = 0; j < x; j++) {
            System.out.print("  ");
          }
          System.out.println("  C");

          for (int j = 0; j < x; j++) {
            System.out.print("  ");
          }
          if (0 == height) {
            System.out.println("b & a");
          } else {
            System.out.println("  &");
          }

        } 
        if (i == height) {
          if (height == 0) {
            System.out.print("A &");
            for (int j = 0; j < hypo; j++) {
              if (j == Math.floor(x)) {
                System.out.print(" c ");
              } else {
                System.out.print("__");
              }
            }
            System.out.println("& A");
          } else {
            System.out.print("B &");
            for (int j = 0; j < hypo; j++) {
              if (j == Math.floor(x)) {
                System.out.print("_c");
              } else {
                System.out.print("__");
              }
            }
            System.out.println("& A");
          }

        } else {
          // first half of the trianlge sides printing
          // determines how much further each symbol must go
          increment = x / h;

          // prints spaces x (the distance to point C) - i * increment
          for (int j = 0; j < (x - i * increment) * 2; j++) {
            System.out.print(" ");
            timesIndented++;
          }

          // to be changed, will have different symbols depending on the angle
          // System.out.print("~");
          if (i == Math.floor(height / 2 + 0.5)) {
            System.out.print("a");
          } else if (angleB < 35) {
            System.out.print("~");
          } else if (angleB > 75) {
            System.out.print("|");
          } else {
            System.out.print("/");
          }
          timesIndented++;


          // prints a space i times increment old increment * 2 times this is to set the base indentation to x
          for (int j = 0; j < (i * increment) * 2; j++) {
            System.out.print(" ");
          }
          if (i == 1) {
            System.out.print(" "); // just to push the second slash out 1 more, lazy fix probably going to cause problems later.
          } else if (i == 0) {
            System.out.print("  ");
          }
          // for test purposes
          if (i == Math.floor(height / 2 + 0.5)) {
            System.out.print("h");
          } else {
            System.out.print("."); // shows where this is moving the indentation to
          }

          // gets new increment, this time its the distance from point C to B
          double increment2 = (hypo - x) / h;
          for (int j = 0; j < increment2 * i * 2; j++) {
            System.out.print(" ");
          }

          // will eventually print different symbols depending on the angle
          if (i == Math.floor(height / 2 + 0.5)) {
            System.out.println("b");
          } else if (angleA < 35) {
            System.out.println("~");
          } else if (angleA > 75) {
            System.out.println("|");
          } else {
            System.out.println("\\");
          }
        }

      }
    }
    // prints the information of the triangle
    double[] innerCircCoord = innerCircleCenter(hypo, angleB / 2, angleA / 2);
    double[] outerCircCoord = outerCircleCenter(x, h, hypo);
    double[] orthocenter = orthocenter(x, h, hypo);
    System.out.printf("Side a: %f, side b: %f, side c: %f\n", aShort, bShort, hypo);
    System.out.printf("Angle A: %f*, angle B: %f*, angle C: %f*\n", angleA, angleB, angleC);
    System.out.printf("Semiperimeter: %f, area: %f, perimeter: %f\n", s, area(), perimeter());
    System.out.printf("Height a: %f, height b: %f, height c: %f\n", height(), height(bShort, aShort, hypo), height(hypo, bShort, aShort));
    System.out.printf("Median a: %f, median b: %f, median c: %f\n", median(), median(bShort, aShort, hypo), median(hypo, bShort, aShort));
    System.out.printf("Inradius: %f, circumradius: %f\n", inradius(), circumradius());
    if (triangleType.equals("right angle")) {
      System.out.printf("Coords of C: (%d, %d), of B: (%f, %f), of A: (%f, %d)\n", 0, 0, x, h, hypo, 0);
    } else {
      System.out.printf("Coords of B: (%d, %d), of C: (%f, %f), of A: (%f, %d)\n", 0, 0, x, h, hypo, 0);
    }
    System.out.printf("Centroid: (%f, %f), inner circle center: (%f, %f), outer circle center: (%f, %f)\n", (x + hypo) / 3, h / 3, innerCircCoord[0], innerCircCoord[1], outerCircCoord[0], outerCircCoord[1]);
    System.out.printf("Orthocenter: (%f, %f)\n", orthocenter[0], orthocenter[1]);
  }

}
